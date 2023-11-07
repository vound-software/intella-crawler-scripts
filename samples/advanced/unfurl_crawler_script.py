from unfurl import core

from api.scripting import ScriptService
from api.scripting.ScriptService import (Action, CustomColumn,
                                         CustomColumnType, CustomColumnValue,
                                         FoundItemResult, ProcessedItemResult, ScriptTZonedDateTime)


def get_node_value(unfurl, data_type, key=None, hover=None):
    for node in unfurl.nodes.values():
        key_matched = key is None or node.key == key
        hover_matched = hover is None or (node.hover is not None and hover in node.hover)
        if node.data_type == data_type and key_matched and hover_matched:
            return node.value

    return None


def process_google_query(tags, custom_columns, instance):
    query = get_node_value(instance, "url.query.pair", "q")
    if query is not None:
        custom_columns.append(CustomColumn("Google Query", CustomColumnType.String, CustomColumnValue(value=query)))

    orig_query = get_node_value(instance, "url.query.pair", "oq")
    if orig_query is not None:
        custom_columns.append(CustomColumn("Google Original Query", CustomColumnType.String, CustomColumnValue(value=orig_query)))

    source = get_node_value(instance, "google.source")
    if source is not None:
        tags.add("Google Source/" + source.removeprefix('Source: '))

    ts_sxsrf = get_node_value(instance, "epoch-milliseconds", 2, "sxsrf")
    if ts_sxsrf is not None:
        dt = ScriptTZonedDateTime(epochMili=int(ts_sxsrf))
        custom_columns.append(CustomColumn("Google Previous Page Loaded", CustomColumnType.DateTime, CustomColumnValue(dateTimeValue=dt)))

    ts_ei = get_node_value(instance, "epoch-microseconds", None, "ei-0")
    if ts_ei is not None:
        dt = ScriptTZonedDateTime(epochMili=int(int(ts_ei)/1000))
        custom_columns.append(CustomColumn("Google Session Started", CustomColumnType.DateTime, CustomColumnValue(dateTimeValue=dt)))

    rlz_lang = get_node_value(instance, "google.rlz.language")
    if rlz_lang is not None:
        tags.add("Google Language/" + rlz_lang)

    install_cohort = get_node_value(instance, "google.rlz.install_cohort")
    if install_cohort is not None:
        install_cohort = install_cohort.removeprefix('Installed in ')
        custom_columns.append(CustomColumn("Google Install Cohort", CustomColumnType.String, CustomColumnValue(value=install_cohort)))

    search_cohort = get_node_value(instance, "google.rlz.search_cohort")
    if search_cohort is not None:
        search_cohort = search_cohort.removeprefix('First search was in ')
        custom_columns.append(CustomColumn("Google Search Cohort", CustomColumnType.String, CustomColumnValue(value=search_cohort)))


def process_linkedin(tags, custom_columns, instance):
    ts = get_node_value(instance, "epoch-milliseconds")
    if ts is not None:
        dt = ScriptTZonedDateTime(epochMili=int(ts))
        custom_columns.append(CustomColumn("LinkedIn Post Created", CustomColumnType.DateTime, CustomColumnValue(dateTimeValue=dt)))

    pid = get_node_value(instance, "description", "LinkedIn Profile ID")
    if pid is not None:
        custom_columns.append(CustomColumn("LinkedIn Profile ID", CustomColumnType.String, CustomColumnValue(value=str(pid))))


class ScriptHandler(ScriptService.Iface):

    def itemFound(self, item):
        return FoundItemResult(action=Action.Include)

    def itemProcessed(self, item):
        custom_columns = []
        tags = set()

        if item.subject is not None and item.mediaType is not None and "history-entry" in item.mediaType:
            instance = core.Unfurl(remote_lookups=False)
            instance.add_to_queue(data_type='url', key=None, value=item.subject)
            instance.parse_queue()

            domain = get_node_value(instance, "url.domain")
            if domain is not None:
                tags.add("Detected Domains/" + str(domain))
                if "google" in domain:
                    process_google_query(tags, custom_columns, instance)
                if 'linkedin.com' in domain:
                    process_linkedin(tags, custom_columns, instance)

        return ProcessedItemResult(action=Action.Include, customColumns=custom_columns, tags=tags)



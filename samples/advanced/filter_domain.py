from api.scripting import ScriptService
from api.scripting.ScriptService import Action, FoundItemResult, ProcessedItemResult, ProcessedItem


def has_email_domain(item: ProcessedItem, domain):
    return domain_in_set(item.messageFrom, domain) \
           or domain_in_set(item.messageTo, domain) \
           or domain_in_set(item.messageCc, domain) \
           or domain_in_set(item.messageBcc, domain)


def domain_in_set(persons, domain):
    if persons is None:
        return False

    for person in persons:
        if person_has_domain(person, domain):
            return True

    return False


def person_has_domain(person, domain):
    return person.contact is not None and person.contact.lower().endswith("@" + domain)


class ScriptHandler(ScriptService.Iface):

    def itemFound(self, item):
        return FoundItemResult(action=Action.Include)

    def itemProcessed(self, item):
        if item.mediaType == "message/rfc822":
            # filter emails by domain
            if has_email_domain(item, "rustcorp.com.au"):
                return ProcessedItemResult(action=Action.Include)
            else:
                return ProcessedItemResult(action=Action.Skip)

        # include all other items
        return ProcessedItemResult(action=Action.Include)


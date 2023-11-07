from unfurl import core

url = ('https://www.google.com/search?q=google&rlz=1C1YTUH_en-GBAU1048AU1048')

instance = core.Unfurl(remote_lookups=False)
instance.add_to_queue(data_type='url', key=None, value=url)
instance.parse_queue()

print(instance.generate_text_tree())

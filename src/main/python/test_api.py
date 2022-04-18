from woocommerce import API

wcapi = API(
    url="http://mystore.local",
    consumer_key="ck_70feb4c6dfa64f843fbd2sas4f268f447c3dcsa56f2c",
    consumer_secret="cs_3fd1747cb45875sasa9959cd6824sd5e001850696b416",
    wp_api=True,
    version="wc/v3"
)

r = wcapi.get("products")
print(r.json())
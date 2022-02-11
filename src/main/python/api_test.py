from woocommerce import API

wcapi = API(
    url="http://mystore.local",
    consumer_key="ck_70feb4c6dfa64f843fbd24f268f447c3dca56f2c",
    consumer_secret="cs_3fd1747cb458759959cd6824d5e001850696b416",
    wp_api=True,
    version="wc/v3"
)

r = wcapi.get("products")
print(r.json())
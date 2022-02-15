import json
import pytest
import requests


@pytest.fixture
def supply_url():
	return "https://stagingapi.demo.com"


@pytest.fixture
def get_adm_accessToken():
	email = "alex@yopmail.com"
	password = "1189"
	url = "https://stagingapi.demo.com/v1/auth"
	data = {'email':email,
			'password':password}
	resp = requests.post(url, data=data)
	respobj = json.loads(resp.text)
	token = respobj['accessToken']

	return token

@pytest.fixture
def get_supAdmin_accessToken():
	email = 'joelcjohnson123@gmail.com'
	password = '111111'
	url = "https://stagingapi.demo.com/v1/auth"
	data = {'email':email,
			'password':password}
	resp = requests.post(url, data=data)
	respobj = json.loads(resp.text)
	token = respobj['accessToken']

	return token

@pytest.fixture
def get_trans_accessToken():
	email = 'ag2@yopmail.com'
	password = '1189'
	url = "https://stagingapi.demo.com/v1/auth"
	data = {'email':email,
			'password':password}
	resp = requests.post(url, data=data)
	respobj = json.loads(resp.text)
	token = respobj['accessToken']

@pytest.mark.skip(reason="need to change the values")
def test_getversiondetailssup(supply_url,get_supAdmin_accessToken):
	url = supply_url + '/v1/versiondetails/2/3702'
	resp = requests.get(url,headers={'Authorization': 'bearer {}'.format(get_supAdmin_accessToken)})
	j = json.loads(resp.text)
	print(resp)
	assert resp.status_code == 200, resp.text
	


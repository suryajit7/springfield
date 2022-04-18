import json
import pytest
import requests


@pytest.fixture
def url():
	# POST API
	return "https://stagingapi.demo.com/v1/auth"

data = [
	("suryait7@gmail.com",'123123'),  # valid detatils
	("suryait7@gmail.com", '111111')  # wrong password
]


# -------------------- Check login page --------------------#
def test_pageload():
	resp = requests.get("https://staging.demo.com/signin")
	assert resp.status_code == 200


# -------------------- valid user --------------------#
@pytest.mark.parametrize("email, password", [data[0]])
def test_valid_user(url, email, password):
	resp = requests.post(url, {'email': email, 'password': password})
	out = json.loads(resp.text)
	assert resp.status_code == 200
	assert out.get('accessToken') != None


#  --------------- with incorrect password --------- #
@pytest.mark.parametrize("email, password", [data[1]])
def test_invalid_password(url, email, password):
	resp = requests.post(url, {'email': email, 'password': password})
	out = json.loads(resp.text)
	assert resp.status_code == 200
	assert out['success'] == False
	assert out['message'] == 'Incorrect Password'


#  --------------- with unregistered emailId ---------- #
@pytest.mark.parametrize("email, password", [data[2]])
def test_unreg_email(url, email, password):
	resp = requests.post(url, {'email': email, 'password': password})
	out = json.loads(resp.text)
	assert resp.status_code == 200
	assert out['success'] == False
	assert out['message'] == 'This email is not registered'


#  --------------- with unverified emailId ---------- #
@pytest.mark.parametrize("email, password", [data[3]])
def test_unver_email(url, email, password):
	resp = requests.post(url, {'email': email, 'password': password})
	out = json.loads(resp.text)
	assert resp.status_code == 200
	assert out['success'] == False
	assert out['message'] == 'Email is not Verified'
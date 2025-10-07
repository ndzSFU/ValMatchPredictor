import pytest
import requests

@pytest.fixture
def Predict_URL():
    return "http://localhost:8081/api/predict"

@pytest.fixture
def db_test_URL():
    return "http://localhost:8081/api/teamProfileTest"

# Default payload fixture
@pytest.fixture
def payload():
    return {"team1": "G2 Esports", "team2": "Sentinels"}

# Fixture that makes a POST request with the default payload
@pytest.fixture
def G2_Sen_response(Predict_URL, payload):
    response = requests.post(Predict_URL, json=payload)
    return response
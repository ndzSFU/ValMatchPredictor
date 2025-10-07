import requests

def test_predict_endpoint(G2_Sen_response):
    """Basic functional test: endpoint returns expected fields and valid probability."""
    assert G2_Sen_response.status_code == 200
    data = G2_Sen_response.json()
    assert "winner" in data
    assert "probability" in data
    assert 0.0 < data["probability"] < 100.0

def test_missing_team1(Predict_URL, payload):
    """Validation test: missing team1 should return an error."""
    payload.pop("team1")
    response = requests.post(Predict_URL, json=payload)
    assert response.status_code == 400

def test_invalid_method(payload):
    response = requests.get("http://localhost:8081/api/predict") #JSON not provided
    assert response.status_code == 405  # Method Not Allowed

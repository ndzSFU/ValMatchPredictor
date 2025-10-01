import requests

def test_predict_endpoint():
    payload = {"team1": "G2 Esports", "team2": "Sentinels"}
    response = requests.post("http://localhost:8081/api/predict", json=payload)
    assert response.status_code == 200
    assert "winner" in response.json()
    assert "probability" in response.json()
    assert response.json()["probability"] > 0.0
import requests
import psycopg2

def test_predict_updates_db(db_test_URL):
    # Send request

    response = requests.get(db_test_URL)

    assert response.status_code == 200

    # Connect to the test database
    conn = psycopg2.connect(
        dbname="vlr_stats",
        user="postgres",
        password="Sfu763690!",
        host="localhost",
        port=5433
    )
    cur = conn.cursor()

    # Check that the database has been updated
    cur.execute("SELECT COUNT(*) FROM team")
    count = cur.fetchone()[0]
    assert count > 0  # Ensure a record was inserted

    cur.close()
    conn.close()
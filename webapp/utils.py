import bcrypt
import mysql.connector
from db_config import DB_CONFIG

def valid_login(username, password):
    valid = False
    cnx = mysql.connector.connect(**DB_CONFIG)
    cursor = cnx.cursor()
    
    query = ("SELECT * FROM users WHERE username = %s")
    cursor.execute(query, [username])
    
    for (username, hashed_pw) in cursor:
        if bcrypt.hashpw(password, hashed_pw) == hashed_pw:
            valid = True
    
    cursor.close()
    cnx.close()
    return valid
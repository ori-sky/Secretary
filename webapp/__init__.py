from flask import Flask
from flask_sqlalchemy import SQLAlchemy
from flask_login import LoginManager

webapp = Flask(__name__)
webapp.config.from_pyfile("config.py")

from webapp import views
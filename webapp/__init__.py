from flask import Flask

webapp = Flask(__name__)
webapp.config.from_pyfile("config.py")

from webapp import views

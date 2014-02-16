from flask import render_template
from webapp import webapp

@webapp.route('/')
def login():
    return render_template('login.html')
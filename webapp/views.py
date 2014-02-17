from flask import render_template
from flask import request
from webapp import webapp

@webapp.route('/')
def login():
    return render_template('login.html')

@webapp.route('/postlogin', methods=['POST', 'GET'])
def postlogin():
    if request.method == 'POST':
        # TODO: login sessions, login validation, etc
        if request.form['username'] and request.form['password']:
            return render_template('postlogin.html', username=request.form['username'],
                                    password=request.form['password'])
        else:
            return render_template('login.html')
    else:
        return render_template('login.html')

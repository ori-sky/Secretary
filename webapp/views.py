from flask import render_template, request, session, redirect, url_for
from webapp import webapp
from utils import valid_login

@webapp.route('/logout/')
def logout():
    session.clear()
    return redirect(url_for("index"))

@webapp.route('/login/', methods = ["GET", "POST"])
def login():
    if 'username' in session:
        return redirect(url_for("index"))
    if request.method == "POST":
        if valid_login(request.form["username"],
                       request.form["password"]):
            session["username"] = request.form["username"]
            return redirect(url_for("index")) 
        else:
            return "Bad password!"
    return render_template("login.html")

@webapp.route('/')
def index():
    if 'username' in session:
        return "Logged in as %s" % session["username"]
    return login()
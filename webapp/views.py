from webapp import webapp

@webapp.route('/')
def home():
    return "Hello, World!"
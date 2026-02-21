bug-tracking-system/
│
├── app.py
├── config.py
├── requirements.txt
├── README.md
│
├── models/
│   ├── user.py
│   └── bug.py
│
├── routes/
│   ├── auth.py
│   ├── bug_routes.py
│   └── dashboard.py
│
├── templates/
│   ├── login.html
│   ├── dashboard.html
│   ├── create_bug.html
│   └── view_bug.html
│
├── static/
│   ├── css/
│   └── js/
│
└── database/
    └── schema.sql

1.requirements.txt:
Flask
Flask-Login
Flask-SQLAlchemy
Werkzeug

2.app.py:
from flask import Flask
from flask_sqlalchemy import SQLAlchemy

app = Flask(__name__)
app.config['SECRET_KEY'] = 'your_secret_key'
app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///bugs.db'

db = SQLAlchemy(app)

from routes import auth, bug_routes, dashboard

app.register_blueprint(auth.bp)
app.register_blueprint(bug_routes.bp)
app.register_blueprint(dashboard.bp)

if __name__ == "__main__":
    app.run(debug=True)

3.models/user.py

from app import db

class User(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    username = db.Column(db.String(100), unique=True)
    password = db.Column(db.String(200))
    role = db.Column(db.String(50))  # Admin / Developer / Tester

4.models/bug.py
from app import db

class Bug(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    title = db.Column(db.String(200))
    description = db.Column(db.Text)
    status = db.Column(db.String(50), default="Open")
    priority = db.Column(db.String(50))
    assigned_to = db.Column(db.Integer)

5.schema.sql
CREATE TABLE user (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    username TEXT UNIQUE,
    password TEXT,
    role TEXT
);

CREATE TABLE bug (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    title TEXT,
    description TEXT,
    status TEXT,
    priority TEXT,
    assigned_to INTEGER
);


# 🐞 Bug Tracking System

## 📌 Overview
The Bug Tracking System is a web-based application designed to help organizations track, manage, and resolve software bugs efficiently. It allows developers, testers, and administrators to report issues, assign tasks, update bug status, and monitor progress in real-time.

This system improves team collaboration and ensures structured bug resolution in software development projects.

---

## 🚀 Features

- 📝 User Registration & Login (Role-based access)
- 🐛 Create, Update, Delete Bugs
- 📊 Bug Status Tracking (Open, In Progress, Resolved, Closed)
- 👨‍💻 Assign Bugs to Developers
- 📅 Timestamp & Priority Management
- 📂 Dashboard for Monitoring Bugs
- 🔍 Search & Filter Bugs
- 📈 Progress Tracking

---

## 🏗️ System Architecture

The system follows a modular architecture:

- **Authentication Module** – Handles user login & role management
- **Bug Management Module** – Create and manage bugs
- **Assignment Module** – Assign bugs to developers
- **Status Tracking Module** – Monitor bug lifecycle
- **Dashboard Module** – View analytics and reports

---

## 🛠️ Technologies Used

- Frontend: HTML, CSS, JavaScript
- Backend: (Add your backend here – e.g., Java / Python / PHP / Node.js)
- Database: MySQL / SQLite / MongoDB
- Version Control: Git & GitHub

*(Update this section according to your actual tech stack)*

---

## 📊 Workflow

1. Tester/Admin reports a bug
2. Bug is assigned to a developer
3. Developer updates bug status
4. Admin verifies and closes the bug
5. Dashboard reflects real-time updates

---

## 🎯 Objectives

- Improve software quality
- Reduce communication gaps between teams
- Provide centralized issue management
- Ensure timely bug resolution

---

## 🔮 Future Enhancements

- Email Notifications
- Integration with CI/CD
- AI-based Bug Severity Prediction
- Graphical Analytics Dashboard

---

## 📌 Conclusion

The Bug Tracking System helps teams efficiently manage software issues by providing a structured workflow and real-time tracking. It enhances productivity and ensures better software maintenance.

---

## 👩‍💻 Author

Merugu Ruthvini  
B.Tech – Computer Science and Business Systems  

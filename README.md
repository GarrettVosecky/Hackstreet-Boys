Contractor Job-Matching App (John Deere Simulation)

This is a web application built to simulate real-world job coordination between contractors and employers, inspired by John Deere workflows. The app allows users to sign up, log in, and manage job listings with detailed field layouts using Google Maps integration.

---

Features

User Authentication
- Sign up and log in with Firebase Authentication
- User session is saved automatically
- Redirects users to login or jobs list based on status

Job Management
- View a list of all active jobs
- Add new jobs with key details like location, instructions, and job name
- Expand job cards to view full descriptions and field info

Field Layout Visualization
- Integrated Google Maps API to show job zones
- Contractors can see entry points and field layout directly from the job detail view

---

Tech Stack
- Frontend: HTML, CSS, JavaScript
- Backend: Firebase (Authentication & Firestore)
- APIs: Google Maps JavaScript API
- Tools: Git and Andoid Studios



Purpose & Impact

This project was developed as a capstone-style simulation to demonstrate real-world contractor workflows. Tested with three mock contractor profiles, the app improved job assignment clarity and reduced matching time by 20% in trial runs.


---
 Getting Started

1. Clone the repository:
   ```bash
   git clone https://github.com/arushkachru/contractor-job-app.git
   cd contractor-job-app

2. Set up Firebase:

- Create a Firebase project at firebase.google.com

- Enable Authentication and Firestore

- Add your Firebase config in firebase.js

3. Run the project:

Open with Live Server or use Firebase Hosting to deploy

Authors

- Arush Kachru  
- Dean McClimon
- Garrett Vosechy
- Jeremy Tarvin
- Noah Solis

---

Future Improvements

- Role-based user access (admin vs. contractor)

- Notifications and scheduling integrations

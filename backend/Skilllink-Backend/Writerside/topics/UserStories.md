# User Stories

## 🟪 User Story HU-01 – Register Account

| Field                   | Description                                                                        |
| ----------------------- | ---------------------------------------------------------------------------------- |
| **Story ID**            | HU-01                                                                              |
| **Story Name**          | Register Account                                                                   |
| **User Type**           | Learner                                                                            |
| **Value**               | Enables users to access the platform and create a personal learning space          |
| **Description**         | As a learner, I want to register with my email and password so I can use SkillLink |
| **Observation**         | Form should include basic validation (email format, password min 6 chars)          |
| **Acceptance Criteria** | ✅ Account is created in DB<br>✅ JWT token returned<br>✅ Redirect to dashboard      |
## 🟪 User Story HU-02 – Login

| Field                | Description                                                                 |
|----------------------|-----------------------------------------------------------------------------|
| **Story ID**         | HU-02                                                                       |
| **Story Name**       | Login                                                                       |
| **User Type**        | All Users (Learner, Mentor, Admin)                                          |
| **Value**            | Allows users to securely access their account and private content           |
| **Description**      | As a user, I want to log in with my credentials so I can access my dashboard and data |
| **Observation**      | Must return a valid JWT and store it in frontend local/session storage      |
| **Acceptance Criteria** | ✅ Valid credentials → JWT + redirect<br>✅ Invalid → error message<br>✅ Protected routes only accessible when logged in |
## 🟪 User Story HU-03 – Edit Profile

| Field                | Description                                                                 |
|----------------------|-----------------------------------------------------------------------------|
| **Story ID**         | HU-03                                                                       |
| **Story Name**       | Edit Profile                                                                |
| **User Type**        | Learner, Mentor                                                             |
| **Value**            | Lets users personalize their public information and showcase their skills   |
| **Description**      | As a user, I want to edit my profile (bio, skills, picture, socials) so others can find me and know who I am |
| **Observation**      | Some fields may be optional (e.g. profile picture), but email should be read-only |
| **Acceptance Criteria** | ✅ Authenticated user can access profile edit form<br>✅ Updates are saved in DB<br>✅ Changes reflected immediately on profile view |
## 🟪 User Story HU-04 – Search Mentors & Collaborators

| Field                | Description                                                                 |
|----------------------|-----------------------------------------------------------------------------|
| **Story ID**         | HU-04                                                                       |
| **Story Name**       | Search Mentors & Collaborators                                              |
| **User Type**        | Learner                                                                     |
| **Value**            | Helps learners find relevant people to guide or work with them              |
| **Description**      | As a learner, I want to search for mentors or collaborators by skill or topic so I can connect with the right people |
| **Observation**      | Filters may include: skill, experience, location, availability              |
| **Acceptance Criteria** | ✅ Authenticated user can access search<br>✅ Results update based on filters<br>✅ Clicking on a result shows the full profile |
## 🟪 User Story HU-05 – Request Mentorship

| Field                | Description                                                                 |
|----------------------|-----------------------------------------------------------------------------|
| **Story ID**         | HU-05                                                                       |
| **Story Name**       | Request Mentorship                                                          |
| **User Type**        | Learner                                                                     |
| **Value**            | Enables users to formally initiate a mentorship relationship                |
| **Description**      | As a learner, I want to request mentorship from a mentor so I can receive guidance and grow faster |
| **Observation**      | A mentor should only receive one pending request per learner at a time      |
| **Acceptance Criteria** | ✅ Authenticated learner can click “Request Mentorship”<br>✅ Request is saved in DB with status `PENDING`<br>✅ Learner receives confirmation or error |
## 🟪 User Story HU-06 – Accept/Reject Mentorship Requests

| Field                | Description                                                                 |
|----------------------|-----------------------------------------------------------------------------|
| **Story ID**         | HU-06                                                                       |
| **Story Name**       | Accept or Reject Mentorship Requests                                        |
| **User Type**        | Mentor                                                                      |
| **Value**            | Allows mentors to control who they support and manage their time            |
| **Description**      | As a mentor, I want to accept or reject mentorship requests so I can choose who I support based on availability and fit |
| **Observation**      | Once accepted, the learner should be notified immediately (e.g. notification or email) |
| **Acceptance Criteria** | ✅ Mentor can view pending requests<br>✅ Can click Accept or Reject<br>✅ DB updates request status (`ACCEPTED` or `REJECTED`)<br>✅ Learner gets real-time feedback |
## 🟪 User Story HU-07 – Create Challenge

| Field                | Description                                                                 |
|----------------------|-----------------------------------------------------------------------------|
| **Story ID**         | HU-07                                                                       |
| **Story Name**       | Create Challenge                                                            |
| **User Type**        | Mentor                                                                      |
| **Value**            | Allows mentors to create learning opportunities and assess learner skills   |
| **Description**      | As a mentor, I want to create code challenges with a title, description, and difficulty so that learners can practice relevant problems |
| **Observation**      | Should include validation for required fields like title, level, and language |
| **Acceptance Criteria** | ✅ Mentor can open a form to create challenge<br>✅ Challenge is saved in DB<br>✅ Challenge appears in public challenge list |
## 🟪 User Story HU-08 – Solve Challenge

| Field                | Description                                                                 |
|----------------------|-----------------------------------------------------------------------------|
| **Story ID**         | HU-08                                                                       |
| **Story Name**       | Solve Challenge                                                             |
| **User Type**        | Learner                                                                     |
| **Value**            | Helps learners practice coding, receive feedback, and build confidence      |
| **Description**      | As a learner, I want to solve code challenges using a live editor and submit my solution so I can get feedback and improve my skills |
| **Observation**      | Challenge editor should validate syntax before submission                   |
| **Acceptance Criteria** | ✅ Learner can access the challenge page<br>✅ Write and submit code in the embedded editor<br>✅ Solution is saved and marked as “Pending Evaluation” |
## 🟪 User Story HU-09 – Evaluate Submission

| Field                | Description                                                                 |
|----------------------|-----------------------------------------------------------------------------|
| **Story ID**         | HU-09                                                                       |
| **Story Name**       | Evaluate Submission                                                         |
| **User Type**        | Mentor                                                                      |
| **Value**            | Enables mentors to provide meaningful feedback and track learner growth     |
| **Description**      | As a mentor, I want to evaluate submitted challenge solutions with a score and feedback so learners can understand what they did right or wrong |
| **Observation**      | Score should be numerical (e.g., 0–100) and feedback should be optional but encouraged |
| **Acceptance Criteria** | ✅ Mentor can view a submitted solution<br>✅ Form allows score and feedback<br>✅ Evaluation is stored and linked to learner’s progress |
## 🟪 User Story HU-10 – Real-Time Chat

| Field                | Description                                                                 |
|----------------------|-----------------------------------------------------------------------------|
| **Story ID**         | HU-10                                                                       |
| **Story Name**       | Real-Time Chat                                                              |
| **User Type**        | All Users                                                                   |
| **Value**            | Allows users to communicate instantly for mentorship or collaboration       |
| **Description**      | As a user, I want to send and receive real-time messages so I can collaborate with mentors or learners more effectively |
| **Observation**      | WebSocket connection should auto-reconnect and support message history       |
| **Acceptance Criteria** | ✅ Authenticated user can open chat<br>✅ Messages are delivered instantly<br>✅ Chat UI shows message status (sent/received) |
## 🟪 User Story HU-11 – Receive Notifications

| Field                | Description                                                                 |
|----------------------|-----------------------------------------------------------------------------|
| **Story ID**         | HU-11                                                                       |
| **Story Name**       | Receive Notifications                                                       |
| **User Type**        | All Users                                                                   |
| **Value**            | Keeps users informed about important events in real-time                    |
| **Description**      | As a user, I want to receive notifications when something important happens (e.g. new message, mentorship accepted, evaluation posted) so I stay informed without refreshing the page |
| **Observation**      | WebSocket or fallback to polling for real-time delivery; badge/alert in UI   |
| **Acceptance Criteria** | ✅ Notification is triggered by backend events<br>✅ Appears in UI (toast, badge, or bell)<br>✅ Read/unread state is tracked |
## 🟪 User Story HU-12 – View Progress

| Field                | Description                                                                 |
|----------------------|-----------------------------------------------------------------------------|
| **Story ID**         | HU-12                                                                       |
| **Story Name**       | View Progress and Evaluations                                               |
| **User Type**        | Learner                                                                     |
| **Value**            | Helps learners reflect on their achievements and identify areas to improve  |
| **Description**      | As a learner, I want to view my progress and mentor evaluations so I can understand how I'm improving and what to work on next |
| **Observation**      | May include graphs, challenge scores, average feedback                      |
| **Acceptance Criteria** | ✅ Progress page shows completed challenges<br>✅ Evaluations are listed with feedback<br>✅ Metrics are updated automatically |
## 🟪 User Story HU-13 – View User List (Admin)

| Field                | Description                                                                 |
|----------------------|-----------------------------------------------------------------------------|
| **Story ID**         | HU-13                                                                       |
| **Story Name**       | View User List                                                              |
| **User Type**        | Admin                                                                       |
| **Value**            | Helps manage and moderate the platform's users                              |
| **Description**      | As an admin, I want to see a list of all users, including roles and activity, so I can monitor usage and take action if needed |
| **Observation**      | Should support filters (by role, activity) and pagination                   |
| **Acceptance Criteria** | ✅ Admin can access the user list<br>✅ List shows key user data (email, role, status)<br>✅ Filters and search return correct results |
## 🟪 User Story HU-14 – Remove Inappropriate Content

| Field                | Description                                                                 |
|----------------------|-----------------------------------------------------------------------------|
| **Story ID**         | HU-14                                                                       |
| **Story Name**       | Remove Inappropriate or Offensive Content                                   |
| **User Type**        | Admin                                                                       |
| **Value**            | Maintains a safe, respectful, and clean learning environment                |
| **Description**      | As an admin, I want to remove or disable any offensive or inappropriate content so that the platform remains professional and inclusive |
| **Observation**      | Can include challenges, messages, bios, or comments                         |
| **Acceptance Criteria** | ✅ Admin can access moderation tools<br>✅ Admin selects content to remove<br>✅ Content is hidden or deleted from the UI and marked in DB |
## 🟪 User Story HU-15 – Execute Code in Challenge

| Field                | Description                                                                 |
|----------------------|-----------------------------------------------------------------------------|
| **Story ID**         | HU-15                                                                       |
| **Story Name**       | Execute Code in Challenge                                                   |
| **User Type**        | Learner                                                                     |
| **Value**            | Allows learners to validate their code in real-time                         |
| **Description**      | As a learner, I want to run my code directly in the browser so I can test it and see if it works before submitting |
| **Observation**      | Requires secure execution sandbox and timeout controls                      |
| **Acceptance Criteria** | ✅ Clicking “Run” sends code to backend<br>✅ Output or error is returned<br>✅ Execution completes within timeout limit |
## 🟪 User Story HU-16 – Show Execution Result

| Field                | Description                                                                 |
|----------------------|-----------------------------------------------------------------------------|
| **Story ID**         | HU-16                                                                       |
| **Story Name**       | Show Execution Result                                                       |
| **User Type**        | Learner                                                                     |
| **Value**            | Provides instant feedback to help correct mistakes                          |
| **Description**      | As a learner, I want to see the result of my code execution (success or error) so I can learn from it and fix issues |
| **Observation**      | Should clearly show stdout and stderr separately                            |
| **Acceptance Criteria** | ✅ Execution result is displayed in console area<br>✅ Errors include line number or stack trace<br>✅ Output updates after every run |
## 🟪 User Story HU-17 – Access API Documentation

| Field                | Description                                                                 |
|----------------------|-----------------------------------------------------------------------------|
| **Story ID**         | HU-17                                                                       |
| **Story Name**       | Access API Documentation                                                    |
| **User Type**        | Developer                                                                   |
| **Value**            | Helps frontend devs and testers understand the API                          |
| **Description**      | As a developer, I want to access up-to-date Swagger documentation so I can integrate or test the backend easily |
| **Observation**      | Should be hosted at `/swagger-ui.html` and include request/response examples |
| **Acceptance Criteria** | ✅ OpenAPI doc auto-generates on server start<br>✅ All endpoints documented<br>✅ Try-it-out available for testing |
ing here...
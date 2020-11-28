# Login Form Demo

This repository contains a simple web application written in Java & Spring Boot.

It has a signup form and login form where you can signup for a new user account and login to an existing user account.

The goal of this kata is to simulate a real world example of how we use Test Driven Development (TDD) on an existing code base. So given a ready made web application, how would you use TDD to drive the introduction of a new feature - in this case, an enhancement to the signup form.

Your facilitators will show you how to use a "Test First" strategy of adding new automated test codes in the test suite (to simulate the new feature), before attempting to build the feature in the leanest way, and then proceed to refactor the code. We hope that you will see how having automated tests helps you in building higher quality code.

If time permits, your facilitators may also introduce you to the idea of refactoring of an existing code base.

---

## Background

The product owner is very happy with the application, but would like some enhancements. People are able to sign up for new accounts, but the ACISO (Agency Chief Information Security Officer) said that some users are able to sign-up with too short a password. Security policy recommends enforcing a longer password length of at least 12 characters to make the application more secure.

### User Story

> As a **User**, <br>
**When** I signup with for a new user account.<br>
**And** I key in a password which is too short.<br>
**Then** I should see an alert telling me my password is too short.

Acceptance Criteria:

- Error alert should be visible when the password is too short.
- Password must be at least 12 characters in length.
- This should be a server-side implementation.
- There should be additional automated test coverage.

---

## Running the application

Open this folder in your terminal app. And run these commands in the terminal.

1. Install dependencies:

    ```
    make install
    ```

2. Start the app:

    ```
    make run
    ```
    
    You can open this url in your browser to view the app: <http://localhost:8080>
    
3. To run the test:

    ```
    make test
    ```

## Architecture

TBC

## Routes

1. `GET /`

    This is the home page. The login form is also here.

2. `GET /signup`

    This is the registration form.

3. `POST /signup`

    Sign up for new user account.

4. `POST /signin`

    This is how you login to the app. You will need to login with `email` and `password`.

    Create a new user before trying to login.

5. `GET /users/welcome`

    Landing page after you have successfully logged in with the correct credentials.

6. `GET /users/logout`

    URL for logging out of the application.

# Selenium TestNG Automation Framework

A Java-based Selenium automation framework built with **Selenium WebDriver, TestNG, Maven, GitHub Actions, n8n, and Slack**.

The project automates web application test cases and integrates them with a CI/CD workflow to execute tests automatically and send test execution notifications to Slack.

## Tech Stack

* Java
* Selenium WebDriver
* TestNG
* Maven
* GitHub Actions
* n8n
* Slack
* Git
* GitHub
* Extent Reports

## Project Structure

```text
selenium-testng-automation/
│
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── base/
│   │       ├── pages/
│   │       └── utils/
│   │
│   └── test/
│       └── java/
│           └── tests/
│
├── testData/
│   └── TestData.xlsx
│
├── screenshots/
│
├── test-output/
│
├── pom.xml
│
└── .github/
    └── workflows/
        └── selenium-tests.yml
```

## Features

### Selenium Web Automation

* Browser automation using Selenium WebDriver
* Page Object Model (POM) based structure
* Reusable page classes
* TestNG-based test execution
* Configurable test data
* Screenshot capture for test failures
* HTML test reporting

### TestNG

The framework uses TestNG for:

* Test execution
* Assertions
* Test organization
* Test lifecycle management
* Test reporting

### Maven

Maven is used for:

* Dependency management
* Project build
* Test execution
* CI integration

Tests can be executed locally using:

```bash
mvn clean test
```

## CI/CD Integration

The project uses **GitHub Actions** to automatically execute Selenium tests.

The workflow performs the following steps:

```text
GitHub Push / Pull Request
          ↓
GitHub Actions
          ↓
Checkout Repository
          ↓
Setup Java
          ↓
Run Maven Tests
          ↓
Send Test Result to n8n
          ↓
Upload Test Reports
          ↓
Mark Workflow Failed if Tests Fail
```

The Selenium test execution step is configured with `continue-on-error` so that test results can still be sent to n8n even when a test fails.

## n8n Integration

After the Selenium tests finish, GitHub Actions sends the execution result to an **n8n webhook**.

Example payload:

```json
{
  "project": "selenium-testng-automation",
  "status": "failure",
  "test": "LoginTest",
  "message": "Selenium tests failed in GitHub Actions",
  "run_url": "https://github.com/<repository>/actions/runs/<run-id>"
}
```

The n8n workflow processes the result using an IF condition:

```text
                 n8n Webhook
                      ↓
                     IF
                  ↙     ↘
             Failure     Success
                ↓           ↓
          Notification  Notification
                ↓           ↓
              Slack       Slack
```

## Slack Notifications

Slack is integrated with n8n to provide automated test execution notifications.

### Failure

When the test execution fails:

```text
❌ Selenium Test Failed

Project: selenium-testng-automation
Test: LoginTest
Error: Selenium tests failed in GitHub Actions
```

### Success

When the test execution succeeds:

```text
✅ Selenium Tests Passed

Project: selenium-testng-automation
Test: LoginTest
Message: All Selenium tests passed
```

## GitHub Actions Configuration

The workflow uses:

* `actions/checkout`
* `actions/setup-java`
* Maven
* GitHub repository secrets
* n8n webhook
* Artifact upload

The n8n webhook URL is stored as a GitHub Actions secret instead of being hardcoded in the repository.

### Required Secret

Create the following repository secret:

```text
N8N_WEBHOOK_URL
```

Store the n8n production webhook URL as its value.

> Never commit webhook URLs containing sensitive credentials, API keys, tokens, or other secrets directly into the repository.

## Test Reports

Test execution artifacts are uploaded by GitHub Actions after every run.

The workflow can preserve:

```text
test-output/
target/
screenshots/
```

This allows test reports and debugging artifacts to be inspected even when a test execution fails.

## Running Tests Locally

### Prerequisites

Make sure the following are installed:

* Java 25
* Maven
* Google Chrome
* Git

Verify Java:

```bash
java -version
```

Verify Maven:

```bash
mvn -version
```

### Clone the Repository

```bash
git clone <repository-url>
```

Navigate to the project:

```bash
cd selenium-testng-automation
```

Run the tests:

```bash
mvn clean test
```

## Current Test Coverage

The framework currently contains Selenium-based login automation.

Additional test scenarios will be added as the framework evolves.

Planned test coverage includes:

* Valid login
* Invalid login
* Empty username/password validation
* Login field validation
* Logout
* Additional UI workflows

## Future Enhancements

Planned improvements include:

* Expand Selenium test coverage
* Improve test data management
* Add parallel TestNG execution
* Add cross-browser testing
* Improve failure diagnostics
* Capture detailed failure information in Slack
* Add more detailed GitHub Actions run information
* Integrate additional reporting
* Add API automation
* Improve CI/CD workflow
* Add scheduled test execution

## CI/CD + Notification Architecture

```text
                 Developer
                     │
                     ▼
                  GitHub
                     │
                     ▼
             GitHub Actions
                     │
                     ▼
              Maven + TestNG
                     │
                     ▼
             Selenium Tests
                     │
              ┌──────┴──────┐
              │             │
           Success        Failure
              │             │
              └──────┬──────┘
                     ▼
                n8n Webhook
                     │
                     ▼
                  IF Node
                 ↙      ↘
            Success      Failure
               ↓            ↓
        Slack Message  Slack Message
```

## Key Highlights

* Selenium WebDriver automation using Java
* TestNG test execution framework
* Maven-based project
* Page Object Model structure
* CI execution using GitHub Actions
* Automated test result processing using n8n
* Slack notifications for test execution results
* GitHub Actions artifact collection for reports and screenshots
* Separation of CI, test automation, workflow automation, and notification layers


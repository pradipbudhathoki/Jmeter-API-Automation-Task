# JMeter API Automation Test Plan

This repository contains a JMeter test plan designed to perform the following tasks:

1. Send a GET request to retrieve a list of items from (‘https://jsonplaceholder.typicode.com’ GET Request Path: ‘/todos’)
2. Verify the response code.
3. Save the response to a file “GET_Response.json”.
4. Modify the content of the file and save in “POST_Request.json”.
5. Send a POST request with the modified content to (‘https://jsonplaceholder.typicode.com’ POST Request Path: ‘/todos’ Content-type: ‘application/json’).
6. Validate the response of the POST request.
7. Generate an HTML report for the entire test run.

## Prerequisites

- Apache JMeter
- Java
- Groovy

## Test Plan Structure

1. **Thread Group**
   - **HTTP Request**: GET Request
     - **HTTP Header Manager**
     - **Response Assertion**
     - **View Results in Tree**
     - **View Results in Table**
     - **BeanShell PostProcessor**: Save GET response
   - **JSR223 Sampler**: Modify JSON File
   - **HTTP Request**: POST Request
     - **HTTP Header Manager**
     - **JSR223 PreProcessor**: Set modified JSON as the request body
     - **Response Assertion**
     - **View Results in Tree**
     - **View Results in Table**
     - **JSON Extractor**: Extract entire response
     - **JSR223 Assertion**: Additional validation and logging

## Instructions

### Setup

1. Clone the Repository:

   ```sh
   git clone https://github.com/pradipbudhathoki/Jmeter-API-Automation-Task.git
   cd Jmeter-API-Automation-Task
   Open JMeter:
   Open JMeter and load the test plan file (API_automation.jmx).
   ```

2. Save your test plan.
3. Run the test by clicking the Start button (green triangle icon) in the toolbar.
4. View results in the `View results in Table`, `View Results Tree` and `Aggregate Report listeners`.

### Run test in non-GUI mode and generate an HTML Report

1. Run the following command in the terminal:

   ```sh
   sh jmeter -n -t API_automation.jmx -l test.csv -e -o report
   ```

2. Open the index.html file in the output report directory to view the detailed HTML report

**Note** : The file API_automation.groovy contains the corresponding groovy scripts for the pre and post processors

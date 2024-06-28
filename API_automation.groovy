/* 
* GET Request
* BeanShell Preprocessor
*/

import org.apache.jmeter.services.FileServer;
import java.io.FileWriter;

String response = prev.getResponseDataAsString();
FileWriter writer = new FileWriter(FileServer.getFileServer().getBaseDir() + "/GET_Response.json");
writer.write(response);
writer.close();

/*
* POST Request
* JSR223 PreProcessor
*/

import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.jmeter.services.FileServer;

String filePath = FileServer.getFileServer().getBaseDir() + "/POST_Request.json";

// Read the JSON content from the file
String jsonContent = new String(Files.readAllBytes(Paths.get(filePath)));
log.info(jsonContent);

// Add the JSON content as the request body
sampler.addNonEncodedArgument("", jsonContent, "");
sampler.setPostBodyRaw(true);

/*
* POST Request
* JSR223 Assertion
*/

import groovy.json.JsonSlurper
import org.apache.jmeter.services.FileServer

// Get the expected JSON from the POST request body
String expectedFilePath = FileServer.getFileServer().getBaseDir() + "/POST_Request.json"
String expectedJsonContent = new File(expectedFilePath).text
def expectedJson = new JsonSlurper().parseText(expectedJsonContent)
log.info("expected : " + expectedJsonContent);

// Get the actual JSON response
String actualJsonContent = vars.get("responseContent")
def actualJson = new JsonSlurper().parseText(actualJsonContent)
log.info("actual : " + actualJsonContent);

// Function to compare two JSON objects
def compareJson = {
    expected, actual -> expected.eachWithIndex {
        item, index -> try {
            assert actual["${index}"].userId == item.userId
            assert actual["${index}"].id == item.id
            assert actual["${index}"].title == item.title
            assert actual["${index}"].completed == item.completed
        } catch (AssertionError e) {
            log.error("Assertion failed at index ${index}: ${e.message}")
            throw e // Re-throw the error to ensure the test fails
        }
    }
}

// Compare the expected and actual JSON content
compareJson(expectedJson, actualJson)

log.info("All assertions passed")



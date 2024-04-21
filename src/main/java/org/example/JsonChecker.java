package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class JsonChecker {

    private static final ObjectMapper mapper = new ObjectMapper();

    // Provide location of your desired JSON file
    public static boolean jsonCheck(String jsonPath) {
        try {
            JsonNode jsonNode = mapper.readTree(new File(jsonPath));

            // Check if the root node has PolicyName and PolicyDocument
            if (!jsonNode.has("PolicyName") || !jsonNode.has("PolicyDocument") ) {
                return false;
            }


            // Check if PolicyName fits the requirements - min length 1, max length 128, acceptable regex pattern [\w+=,.@-]+
            String policyName = jsonNode.get("PolicyName").asText();

            if (policyName.isEmpty() || policyName.length()>128 || !policyName.matches("[\\w+=,.@-]+")) {
                return false;
            }

            JsonNode policyDocumentNode = jsonNode.get("PolicyDocument");

            // Check if PolicyDocument has Version and Statement fields
            if (!policyDocumentNode.has("Version") || !policyDocumentNode.has("Statement")) {
                return false;
            }

            JsonNode statementNode = policyDocumentNode.get("Statement");

            // Check if Statement is an array and is not empty
            if (!statementNode.isArray() || statementNode.isEmpty()) {
                return false;
            }

            // Check if the first element of Statement array has the required fields
            JsonNode statementElement = statementNode.get(0);
            if (!statementElement.has("Sid") || !statementElement.has("Effect") || !statementElement.has("Action") || !statementElement.has("Resource")) {
                return false;
            }

            // Ensure Action field is an array and is not empty
            JsonNode actionNode = statementElement.get("Action");
            if (!actionNode.isArray() || actionNode.isEmpty()) {
                return false;
            }

            // Checking if the field contains one asterisk and returning false if it does
            return !statementElement.get("Resource").asText().equals("*");

        } catch (IOException e) {
            System.out.println("Invalid path to JSON file");
            return false;
        }
    }


    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please provide the path to the JSON file:");
            Scanner scanner = new Scanner(System.in);
            String jsonPath = scanner.nextLine();
            System.out.println(JsonChecker.jsonCheck(jsonPath));
        } else {
            for (String arg : args) {
                System.out.println(JsonChecker.jsonCheck(arg));
            }
        }
    }
}
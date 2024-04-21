package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonCheckerTest {

    @Test
    void jsonOneAsteriskCheck() {
        assertFalse(JsonChecker.jsonCheck("src/test/java/org/example/test1.json"));
    }

    @Test
    void jsonNoAsteriskCheck() {
        assertTrue(JsonChecker.jsonCheck("src/test/java/org/example/test2.json"));
    }

    @Test
    void emptyJsonCheck() {
        assertFalse(JsonChecker.jsonCheck("src/test/java/org/example/test3.json"));
    }

    @Test
    void missingFieldsTest() {
        assertFalse(JsonChecker.jsonCheck("src/test/java/org/example/test4.json"));
    }

    @Test
    void emptyPolicyNameTest() {
        assertFalse(JsonChecker.jsonCheck("src/test/java/org/example/test5.json"));
    }

    @Test
    void tooLongPolicyNameTest() {
        assertFalse(JsonChecker.jsonCheck("src/test/java/org/example/test6.json"));
    }

    @Test
    void noRegexMatchPolicyNameTest() {
        assertFalse(JsonChecker.jsonCheck("src/test/java/org/example/test7.json"));
    }
}
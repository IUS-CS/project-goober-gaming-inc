package com.rpg.rpg;

import org.junit.jupiter.api.Test;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

class GameHandlerTest {

    private String runWithInput(String input) {
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;

        ByteArrayInputStream fakeIn =
                new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        PrintStream fakeOut = new PrintStream(outBuffer, true, StandardCharsets.UTF_8);

        try {
            System.setIn(fakeIn);
            System.setOut(fakeOut);

            GameHandler handler = new GameHandler();

            try {
                handler.run(); 
            } catch (NoSuchElementException eof) {

            }

            return outBuffer.toString(StandardCharsets.UTF_8);
        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }

    @Test
    void invalidInput_reprompts() {
        // Provide only invalid input, so it will print the invalid message,
        String printed = runWithInput("x\n");

        assertTrue(printed.contains("Invalid input"),
                "Should print invalid input message");
        assertTrue(printed.contains("Choose action") || printed.contains("Enter"),
                "Should prompt user for 1 or 2");
    }

    @Test
    void choosing1_printsBasicAttackMessage() {
        // Provide one valid choice then let it run out of input.
        String printed = runWithInput("1\n");

        // GameHandler should print something like:
        // "You used Basic Attack ..." (or similar)
        assertTrue(
                printed.contains("Basic Attack") || printed.contains("attack"),
                "Should indicate the basic attack happened"
        );
    }

    @Test
    void choosing2_printsHealMessage() {
        // Provide one valid choice then let it run out of input.
        String printed = runWithInput("2\n");

        assertTrue(
                printed.contains("heal") || printed.contains("Healed") || printed.contains("healed"),
                "Should indicate the heal happened"
        );
    }
}
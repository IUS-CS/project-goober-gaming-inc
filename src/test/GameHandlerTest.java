package com.rpg.rpg;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;

public class GameHandlerTest {

    @Test
    public void testRunQuitsImmediately() {
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;

        ByteArrayInputStream testIn =
                new ByteArrayInputStream("quit\n".getBytes(StandardCharsets.UTF_8));
        ByteArrayOutputStream testOut = new ByteArrayOutputStream();

        try {
            System.setIn(testIn);
            System.setOut(new PrintStream(testOut));

            GameHandler game = new GameHandler();
            game.run();

            String output = testOut.toString();

            assertTrue(output.contains("Welcome to the RPG!"));
            assertTrue(output.contains("Move using north, east, south, or west."));
            assertTrue(output.contains("Type quit to end the game."));
            assertTrue(output.contains("Thanks for playing!"));
        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }
}

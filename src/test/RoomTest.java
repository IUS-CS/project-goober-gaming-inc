/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rpg.rpg;

/**
 *
 * @author andy
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class RoomTest {

    static class TestEncounter implements EncounterStrat {
        int callCount = 0;

        @Override
        public void runEncounter() {
            callCount++;
        }
    }

    @Test
    public void constructorShouldInitializeEncounterAndRanEncounterFalse() {
        TestEncounter encounter = new TestEncounter();
        Room room = new Room(encounter);

        assertNotNull(room);
        assertFalse(room.ranEncounter);
    }

    @Test
    public void setExitAndGetExitNorthShouldWork() {
        Room room1 = new Room(new NoEncounter());
        Room room2 = new Room(new NoEncounter());

        room1.setExit("north", room2);

        assertEquals(room2, room1.getExit("north"));
    }

    @Test
    public void setExitAndGetExitEastShouldWork() {
        Room room1 = new Room(new NoEncounter());
        Room room2 = new Room(new NoEncounter());

        room1.setExit("east", room2);

        assertEquals(room2, room1.getExit("east"));
    }

    @Test
    public void setExitAndGetExitSouthShouldWork() {
        Room room1 = new Room(new NoEncounter());
        Room room2 = new Room(new NoEncounter());

        room1.setExit("south", room2);

        assertEquals(room2, room1.getExit("south"));
    }

    @Test
    public void setExitAndGetExitWestShouldWork() {
        Room room1 = new Room(new NoEncounter());
        Room room2 = new Room(new NoEncounter());

        room1.setExit("west", room2);

        assertEquals(room2, room1.getExit("west"));
    }

    @Test
    public void setExitShouldIgnoreCase() {
        Room room1 = new Room(new NoEncounter());
        Room room2 = new Room(new NoEncounter());

        room1.setExit("NoRtH", room2);

        assertEquals(room2, room1.getExit("north"));
    }

    @Test
    public void getExitShouldIgnoreCase() {
        Room room1 = new Room(new NoEncounter());
        Room room2 = new Room(new NoEncounter());

        room1.setExit("east", room2);

        assertEquals(room2, room1.getExit("EAST"));
    }

    @Test
    public void getExitInvalidDirectionShouldReturnNull() {
        Room room = new Room(new NoEncounter());

        assertNull(room.getExit("up"));
    }

    @Test
    public void enterRoomShouldRunEncounterFirstTime() {
        TestEncounter encounter = new TestEncounter();
        Room room = new Room(encounter);

        room.enterRoom();

        assertEquals(1, encounter.callCount);
        assertTrue(room.ranEncounter);
    }

    @Test
    public void enterRoomShouldOnlyRunEncounterOnce() {
        TestEncounter encounter = new TestEncounter();
        Room room = new Room(encounter);

        room.enterRoom();
        room.enterRoom();
        room.enterRoom();

        assertEquals(1, encounter.callCount);
        assertTrue(room.ranEncounter);
    }

    @Test
    public void enterRoomSecondTimeShouldPrintNothingNewMessage() {
        TestEncounter encounter = new TestEncounter();
        Room room = new Room(encounter);

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(output));

        /*
        first time, runs encounter
        clear first output if any
        second time, should print repeat message
        */
        
        try {
            room.enterRoom(); 
            output.reset();   
            room.enterRoom(); 

            String printed = output.toString().trim();
            assertEquals("Nothing New happens here.", printed);
        } finally {
            System.setOut(originalOut);
        }
    }
}

package com.rpg.rpg;

/**
 *
 * @author andy
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class RoomHandlerTest {

    @Test
    public void createMapShouldSetCurrentRoom() {
        RoomHandler handler = new RoomHandler();

        handler.createMap();

        assertNotNull(handler.currentRoom);
    }

    @Test
    public void changeRoomShouldReturnTrueForValidDirection() {
        RoomHandler handler = new RoomHandler();
        handler.createMap();

        boolean changed = handler.changeRoom("north");

        assertTrue(changed);
    }

    @Test
    public void changeRoomShouldUpdateCurrentRoomWhenMoveIsValid() {
        RoomHandler handler = new RoomHandler();
        handler.createMap();

        Room originalRoom = handler.currentRoom;
        Room expectedRoom = originalRoom.getExit("north");

        boolean changed = handler.changeRoom("north");

        assertTrue(changed);
        assertEquals(expectedRoom, handler.currentRoom);
    }

    @Test
    public void changeRoomShouldReturnFalseForInvalidDirection() {
        RoomHandler handler = new RoomHandler();
        handler.createMap();

        boolean changed = handler.changeRoom("up");

        assertFalse(changed);
    }

    @Test
    public void changeRoomShouldNotChangeCurrentRoomWhenDirectionIsInvalid() {
        RoomHandler handler = new RoomHandler();
        handler.createMap();

        Room originalRoom = handler.currentRoom;

        boolean changed = handler.changeRoom("up");

        assertFalse(changed);
        assertEquals(originalRoom, handler.currentRoom);
    }

    @Test
    public void changeRoomShouldPrintMessageWhenDirectionIsInvalid() {
        RoomHandler handler = new RoomHandler();
        handler.createMap();

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(output));

        try {
            boolean changed = handler.changeRoom("up");

            assertFalse(changed);
            assertEquals("Room has no exit inup", output.toString().trim());
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    public void changeRoomShouldAllowReturningBackIfOppositeExitExists() {
        RoomHandler handler = new RoomHandler();
        handler.createMap();

        Room startRoom = handler.currentRoom;

        boolean movedNorth = handler.changeRoom("north");
        assertTrue(movedNorth);

        Room northRoom = handler.currentRoom;
        assertEquals(startRoom, northRoom.getExit("south"));

        boolean movedSouth = handler.changeRoom("south");
        assertTrue(movedSouth);
        assertEquals(startRoom, handler.currentRoom);
    }

    @Test
    public void changeRoomShouldTriggerEncounterWhenEnteringNewRoom() {
        RoomHandler handler = new RoomHandler();
        handler.createMap();

        Room nextRoom = handler.currentRoom.getExit("north");
        assertNotNull(nextRoom);
        assertFalse(nextRoom.ranEncounter);

        boolean changed = handler.changeRoom("north");

        assertTrue(changed);
        assertTrue(handler.currentRoom.ranEncounter);
    }
}

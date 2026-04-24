package com.rpg.rpg;

/**
 *
 * @author andy
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class RoomFactoryTest {

    @Test
    public void enterAndExitShouldConnectRoomsBothWays() {
        Room room1 = new Room(new NoEncounter());
        Room room2 = new Room(new NoEncounter());

        RoomFactory.enterAndExit(room1, "north", room2, "south");

        assertEquals(room2, room1.getExit("north"));
        assertEquals(room1, room2.getExit("south"));
    }

    @Test
    public void enterAndExitShouldWorkWithOtherDirections() {
        Room room1 = new Room(new NoEncounter());
        Room room2 = new Room(new NoEncounter());

        RoomFactory.enterAndExit(room1, "east", room2, "west");

        assertEquals(room2, room1.getExit("east"));
        assertEquals(room1, room2.getExit("west"));
    }

    @Test
    public void enterAndExitShouldNotOverwriteExistingExitOnRoom1() {
        Room room1 = new Room(new NoEncounter());
        Room room2 = new Room(new NoEncounter());
        Room room3 = new Room(new NoEncounter());

        room1.setExit("north", room3);

        RoomFactory.enterAndExit(room1, "north", room2, "south");

        assertEquals(room3, room1.getExit("north"));
        assertNull(room2.getExit("south"));
    }

    @Test
    public void enterAndExitShouldNotOverwriteExistingExitOnRoom2() {
        Room room1 = new Room(new NoEncounter());
        Room room2 = new Room(new NoEncounter());
        Room room3 = new Room(new NoEncounter());

        room2.setExit("south", room3);

        RoomFactory.enterAndExit(room1, "north", room2, "south");

        assertNull(room1.getExit("north"));
        assertEquals(room3, room2.getExit("south"));
    }

    @Test
    public void enterAndExitShouldDoNothingIfBothSidesAlreadyOccupied() {
        Room room1 = new Room(new NoEncounter());
        Room room2 = new Room(new NoEncounter());
        Room room3 = new Room(new NoEncounter());
        Room room4 = new Room(new NoEncounter());

        room1.setExit("north", room3);
        room2.setExit("south", room4);

        RoomFactory.enterAndExit(room1, "north", room2, "south");

        assertEquals(room3, room1.getExit("north"));
        assertEquals(room4, room2.getExit("south"));
    }

    @Test
    public void createRoomsMapShouldReturnNonNullStartRoom() {
        Room startRoom = RoomFactory.createRoomsMap();

        assertNotNull(startRoom);
    }

    @Test
    public void createRoomsMapStartRoomShouldHaveAtLeastOneExit() {
        Room startRoom = RoomFactory.createRoomsMap();

        boolean hasExit =
                startRoom.getExit("north") != null ||
                startRoom.getExit("east") != null ||
                startRoom.getExit("south") != null ||
                startRoom.getExit("west") != null;

        assertTrue(hasExit);
    }

    @Test
    public void createRoomsMapNorthExitShouldExistBecauseFactoryGuaranteesIt() {
        Room startRoom = RoomFactory.createRoomsMap();

        assertNotNull(startRoom.getExit("north"));
    }

    @Test
    public void createRoomsMapNorthExitShouldLeadBackSouthToStartRoom() {
        Room startRoom = RoomFactory.createRoomsMap();
        Room northRoom = startRoom.getExit("north");

        assertNotNull(northRoom);
        assertEquals(startRoom, northRoom.getExit("south"));
    }

    @Test
    public void createRoomsMapEastExitIfPresentShouldLeadBackWestToStartRoom() {
        Room startRoom = RoomFactory.createRoomsMap();
        Room eastRoom = startRoom.getExit("east");

        if (eastRoom != null) {
            assertEquals(startRoom, eastRoom.getExit("west"));
        }
    }
}

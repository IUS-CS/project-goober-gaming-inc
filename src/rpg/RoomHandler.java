package rpg;
/* Room currentRoom
 void createMap()
Creates some rooms and links them together by setting their exit directions,
then sets the currentRoom. For example, 5 rooms are created,
Room 1 is effectively in the center with Room 2 as the north exit,
Room 3 as the east exit, Room 4 as the south exit,
and Room 5 as the west exit.
Make sure that each room has the correct exit directions,
it needs to be possible for the player to both enter and exit a room back the way they came.
 boolean changeRoom(String direction)
Checks the corresponding exit of the current room
(like currentroom.north if "north" is received, etc).
Then, if that room isn't null, sets the current room to the room
located in the given direction and returns true (indicating the room successfully changed),
otherwise it returns false and doesn't change the current room
since there is no valid room to "move" to in that direction.*/
public class RoomHandler {

    Room currentRoom;

    public void createMap() {
        currentRoom = RoomFactory.createRoomsMap();

        if (currentRoom == null) {
            System.out.println("Error: rooms were not created!");
        }
    }

    public boolean changeRoom(String direction) {
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom != null) {
            currentRoom = nextRoom;
            currentRoom.enterRoom();
            return true;
        }else {
            System.out.println("Room has no exit in" + direction);
            return false;
        }
    }
}

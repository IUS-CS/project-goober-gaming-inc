package rpg;

import java.util.Random;
public class RoomFactory {
    private static Random rand = new Random();
    private static EncounterStrat randEncounter(){
        int randomizer = rand.nextInt(3);
        if(randomizer == 0){
            return new MonsterEncounterStrat();
        } else if(randomizer == 1){
            return new TreasureEncounter();
        }else{
            return new NoEncounter();
        }
    }

    public static void enterAndExit(Room room1, String direction1, Room room2, String direction2){
        if(room1.getExit(direction1) == null && room2.getExit(direction2) == null){
            room1.setExit(direction1, room2);
            room2.setExit(direction2, room1);
        }
    }

    public static Room createRoomsMap(){
        int roomAmount = 5;

        Room[] rooms = new Room[roomAmount];

        //starting room
        rooms[0] = new Room(new NoEncounter());

        //crete rooms
        for(int i = 1; i < roomAmount; i++){
            rooms[i] = new Room(randEncounter());
        }

        //starting room
        Room startRoom = rooms[0];

        //making sure starting room has an exit
        Room randRoom = rooms[rand.nextInt(roomAmount)];
        enterAndExit(startRoom, "north", randRoom, "south");
        if(rand.nextBoolean()){
            enterAndExit(startRoom, "east", randRoom, "west");
        }

        //connecting rooms
        for(int i = 0; i < roomAmount; i++){
            Room current = rooms[i];

            Room otherRooms = rooms[rand.nextInt(roomAmount)];

            if(rand.nextBoolean() && otherRooms != current){
                enterAndExit(current, "north", otherRooms, "south");
            }
            if(rand.nextBoolean() && otherRooms != current){
                enterAndExit(current, "east", otherRooms, "west");
            }
            if(rand.nextBoolean() && otherRooms != current){
                enterAndExit(current, "south", otherRooms, "north");
            }
            if(rand.nextBoolean() && otherRooms != current){
                enterAndExit(current, "west", otherRooms, "east");
            }
        }

        return startRoom;

    }
}

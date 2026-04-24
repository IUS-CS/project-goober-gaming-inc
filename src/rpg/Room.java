package rpg;
/* boolean ranEncounter
A boolean indicating whether an encounter has occurred in this room yet or not
 Room north, east, south, west
4 Rooms that will be used as directional references (null meaning there is no room in that direction)
 void setExit(String, Room)
Sets the corresponding exit direction to the Room received. Example, setExit("north", someRoom)
would set the currentRoom.north to someRoom.*/


public class Room {
    boolean ranEncounter;

    private Room north;
    private Room east;
    private Room south;
    private Room west;

    private EncounterStrat encounter;

    public Room(EncounterStrat encounter){
        this.encounter = encounter;
        ranEncounter = false; //has not entered room
    }

    public void setExit(String direction, Room room){
        if(direction.equalsIgnoreCase("north")){
            north = room;
        } else if(direction.equalsIgnoreCase("east")){
            east = room;
        } else if(direction.equalsIgnoreCase("south")){
            south = room;
        } else if(direction.equalsIgnoreCase("west")){
            west = room;
        }
    }

    public Room getExit(String direction){
        if(direction.equalsIgnoreCase("north")){
            return north;
        } else if(direction.equalsIgnoreCase("east")){
            return east;
        } else if(direction.equalsIgnoreCase("south")){
            return south;
        } else if(direction.equalsIgnoreCase("west")){
            return west;
        }
        return null;
    }

    public void enterRoom(){
        if(!ranEncounter){
            encounter.runEncounter();
            ranEncounter = true;
        }else{
            System.out.println("Nothing New happens here.");
        }
    }
}
interface EncounterStrat{
    public void runEncounter();
}

class MonsterEncounterStrat implements EncounterStrat{
    @Override
    public void runEncounter() {
        System.out.println("Monster encounter");
    }
}

class TreasureEncounter implements EncounterStrat{
    @Override
    public void runEncounter() {
        System.out.println("You found the treasure!");
    }
}

class NoEncounter implements EncounterStrat{
    @Override
    public void runEncounter() {
        System.out.println("You encounter nothing!");
    }
}



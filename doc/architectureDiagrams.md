## Goober Gaming Architecture Diagrams

## System Diagrams

The following diagrams are included to explain how the game works. 
They show the main parts of the program, how the rooms are created, what happens in each room, and how the game runs step-by-step.

## Diagram 1: High Level Class Diagram

![High-Level Class Diagram](images/diagram1-high-level-class.png)

This diagram shows the main classes in the RPG systm and how they relate to each other. 

---

## Diagram 2: Player Class Diagram

![Player Class Diagram](images/diagram2-player-class.png)

This diagram shows the fields and methods used in Player class.

---

## Diagram 3: Enemy Class Diagram

![Enemy Class Diagram](images/diagram3-enemy-class.png)

This diagram shows the fields and methods used in the Enemy class. 

---

## Diagram 4: Room Class Diagram

![Room Class Diagram](images/diagram4-room-class.png)

This diagram shows each room stores exits and encounter strategy.

---

## Diagram 5: RoomFactory Class Diagram

![RoomFactory Class Diagram](images/diagram5-roomfactory-class.png)

This diagram shows the methods responsible for randomly creating and connecting rooms.

---

## Diagram 6: Encounter Strategy Diagram

![Encounter Strategy Diagram](images/diagram6-encounter-strategy.png)

This diagram shows how the game uses different encounter strategy classes inside each room. 

---

## Diagram 7: Overall Game Flow Activity Diagram

![Overall Game Flow](images/diagram7-overall-game-flow.png)

This activity diagram shows the main flow of the game from startup to exploration and encounters. 

---

## Diagram 8: Combat Sequence Diagram

![Combat Sequence Diagram](images/diagram8-combat-sequence.png)

This sequence diagram shows the order of actions during combat between the player and enemy. 

---

## Diagram 9: Room Map Creation Activity Diagram

![Room Map Creation Activity Diagram](images/diagram9-room-map-creation.png)

This diagram shows the steps used by RoomFactory to generate the room map. 

---

## Diagram 10: Example Room Layout Diagram

![Example Room Layout Diagram](images/diagram10-example-room-layout.png)

This diagram shows one possibe room layout. Since the map is randomly generated, the actual layout may be different each time. 























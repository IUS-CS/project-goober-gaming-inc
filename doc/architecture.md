## Architecture Designs of Goober Gaming ##

## Overview
This document explains the architecture of our game project.
The project is built using several classes that each have a specific job.
By separating the game into different parts, the code is easier to understand. 

The main classes in this project are:

- `GameHandler`
- `Player`
- `Enemy`
- `Room`
- `RoomFactory`

Together, these classes control combat, movement, and room navigation. 

---

## Main Architecture Idea
The game is organized so that one class controls the overall flow, while the other classes handle specific parts of the game. 

- `GameHandler` controls the game loop and room navigation
- `Player` stores player health and actions
- `Enemy` stores enemy health and attack behavior
- `Room` represents each location on the map and stores exits to other rooms.

  This keeps responsibilities separated and makes the system easier to manage.

---

## Class Descriptions

 ## 1. GameHandler

The `GameHandler` class is the main controller of the game.
It manager both combat and room navigation. 

### Responsibilities
- Creates a `Player` and an `Enemy`
- Runs the main game loop
- Prints the HP of both the player and the enemy
- Prompts the user/player for input
- Performs player actions
- Performs enemy actions
- Ends combat when either character dies
- Stores the current room.
- Builds the map of connected rooms
- Changes rooms when the player moves
- Tracks whether an encounter has happened

### Important Variables
- `boolean ranEncounter`
- `Room currentRoom`

### Important Methods

#### `Room createRoomsMap()`
This method creates and connects the rooms used in the game.

Instead of building a fixed map shape by hand, this method creates a **randomized room map**.  
It creates a total of **5 rooms**:

- 1 starting room
- 4 additional rooms

### How the map is created
1. A `Room` array is created to hold all 5 rooms.
2. The first room (`rooms[0]`) is made the **starting room**.
3. The starting room always uses `NoEncounter`, so the player does not begin with an encounter.
4. The remaining rooms are created with random encounter types by calling `randEncounter()`.

### Random encounters
The `randEncounter()` method randomly gives a room one of these encounter strategies:
- `MonsterEncounterStrat`
- `TreasureEncounter`
- `NoEncounter`

This makes the map less predictable and gives each playthrough some variety.

### Connecting rooms
After the rooms are created, the method connects them together using the helper method:

enterAndExit(Room room1, String direction1, Room room2, String direction2).


#### `boolean changeRoom(String direction)`

This method checks whether the current room has a valid exit in the direction the player entered.

Examples:
- `"north"`
- `"east"`
- `"south"`
- `"west"`

If the exit exists:
- `currentRoom` changes to that room
- the method returns `true`

If the exit does not exist:
- `currentRoom` does not change
- the method returns `false`

### Combat Flow in GameHandler

The `GameHandler` also contains an infinite loop for combat.  
The loop does the following:

1. Prints the HP of the player and enemy
2. Prompts the user for input
3. Accepts:
   - `"1"` for basic attack
   - `"2"` for heal
4. Repeats the prompt if the input is invalid
5. If the player chooses attack, the enemy is hurt by the damage returned from the player's basic attack
6. If the player chooses heal, the player's heal method is called
7. If the enemy dies, the fight ends before the enemy acts
8. If the enemy is still alive, it performs a random action
9. The player is hurt by the damage returned from the enemy’s action
10. If the player dies, the fight ends

This makes `GameHandler` the class that coordinates the interaction between all the other classes.

---

## 2. Player
The `Player` class represents the character controlled by the user.

### Responsibilities
- Stores player HP
- Tracks whether the player is dead
- Attacks the enemy
- Heals itself
- Takes damage

### Important Variables
- `HP`
- `isDead`

### Important Methods

#### `BasicAttack()`
Returns a random damage value from **1 to 3**.

#### `Heal()`
Increases HP by **3**, but HP cannot go above **10**.

#### `Hurt(int damage)`
Decreases HP by the amount received.  
If HP reaches **0**, `isDead` becomes `true`.

### Notes
The player has a maximum HP of **10**.  
This means healing cannot raise the player above that value.

---

## 3. Enemy
The `Enemy` class represents the opponent the player fights.

### Responsibilities
- Stores enemy HP
- Tracks whether the enemy is dead
- Attacks the player
- Takes damage
- Chooses random actions during combat

### Important Variables
- `HP`
- `isDead`

### Important Methods

#### `BasicAttack()`
Returns a random damage value from **1 to 3**.

#### `HeavyAttack()`
Returns a random damage value from **4 to 5**.

#### `Hurt(int damage)`
Decreases HP by the amount received.  
If HP reaches **0**, `isDead` becomes `true`.

#### `RandomAction()`
Chooses either:
- `BasicAttack()`
- `HeavyAttack()`

Then it returns the damage dealt.

The heavy attack should happen with a probability of **1/3 or less** so the enemy does not become too strong too quickly.

### Notes
The enemy also has a maximum HP of **10**.

---

## 4. Room
The `Room` class represents one location in the map.

Each room can connect to other rooms in the four main directions.

### Responsibilities
- Stores whether an encounter has already happened in the room
- Stores exits for north, east, south, and west
- Allows exits to be assigned

### Important Variables
- A boolean indicating whether an encounter has occurred in the room yet
- `Room north`
- `Room east`
- `Room south`
- `Room west`

If one of these directions is `null`, that means there is no room in that direction.

### Important Methods

#### `void setExit(String direction, Room room)`
This method sets one of the room’s exits.

Example:
- `setExit("north", someRoom)` sets the north exit to `someRoom`

This allows rooms to be linked together to create the map.

---

## How the Classes Work Together

### Overall Interaction
The system works by having `GameHandler` control everything.

- `GameHandler` creates the `Player`
- `GameHandler` creates the `Enemy`
- `GameHandler` creates the map of `Room` objects
- `GameHandler` stores the `currentRoom`
- The player moves between rooms using `changeRoom()`
- If a room contains an encounter, combat begins
- During combat:
  - the `Player` attacks or heals
  - the `Enemy` attacks
  - both can take damage through their `Hurt()` methods
- Combat ends when either `isDead` becomes `true`

---

## Example Room Layout

        North
          |
West -- South -- East






  

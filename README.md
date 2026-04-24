# Goober Gaming (Turn-Based Battle Game)

## About This Game

This is a small RPG-style game made for our System Analysis and Design course. 
In the game, the player explores different rooms and fights enemies through an **encounter system**.

The game includes **room navigation**, allowing the player to move between rooms using directions. Each room may contain an enemy encounter, which only happens once per room. 

This project was created to practice coding skills and teamwork.

## How to Play

When the program starts, the player is placed into a room. 

### Movement:

Type a direction to move between rooms:
- `north`
- `south`
- `east`
- `west`

### When entering a room
- An enemy encounter may occur

### During each turn in combat
- The game shows the health (HP) of both the player & the enemy.
- The player chooses what action to take.

### Controls

- ***1*** to attack
- ***2*** to heal

## What the Program Does

- Generates & connects rooms for exploration
- Allows movement between rooms using directional commands
- Triggers enemy encounters when entering rooms
- Ensures encounters only happen once per room
- Displays player to attack or heal
- Validates user input
- Applies damage & healing
- Continues running after encounters end
- Ends the game **only when the player loses (HP reaches 0)**


## Main Parts of the Program

### GameHandler

Runs the main game loop, handles user input, manages room navigation, and decides when the game ends.

### Player

Stores the player's health. The player can attack, heal, and take damage.

### Enemy

Stores enemy health and attacks with random amount of damage. 

### Room

Represents each room in the game. Rooms are connected in different directions (nort, east, south, west) and may contain an encounter.

### RoomHandler/RoomFactory

Handles the creation and setup of rooms. This part of the program connects rooms together and controls how the navigation system works. 

### How to Run

***1.*** Open a terminal in the project folder.

***2.*** Compile the program.

***3.*** Run the game:

java GameHandler

## Team Members

***Tynan Anfield*** -Product Owner/Scrum Master

Organizes the project and keeps everyone on track.

***Andy Gomez*** -Tester

Runs the game and looks for bugs. 

***Ronalyn Gonzalez*** - Documentation

Writes the report and keeps documentation updated. 

***Brielle Dickens & Sara Nguyen*** -Programmers

Writes the code, fix errors, and add features.

## Purpose

The purpose of this project is to: 
- practice system design
- handle user input
- work as team

This project is for a class assignment and learning purposes. 

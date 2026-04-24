package com.rpg.rpg;

import java.lang.reflect.Field;
import java.util.Scanner;

public class GameHandler {

    private final Character player;
    private final RoomHandler roomHandler;
    private final Scanner scanner;

    public GameHandler() {
        player = new Character();
        roomHandler = new RoomHandler();
        scanner = new Scanner(System.in);
    }

    public void run() {
        roomHandler.createMap();

        if (roomHandler.currentRoom == null) {
            System.out.println("Game could not start.");
            return;
        }

        System.out.println("Welcome to the RPG!");
        System.out.println("Move using north, east, south, or west.");
        System.out.println("Type quit to end the game.");
        System.out.println();

        // Run the starting room encounter once.
        roomHandler.currentRoom.enterRoom();

        while (!player.isDead()) {
            System.out.println();
            System.out.println("==============================");
            System.out.println("Player HP: " + player.getHp());
            System.out.println("Available exits:");
            printValidExits(roomHandler.currentRoom);

            System.out.print("Enter direction: ");
            String input = scanner.nextLine().trim().toLowerCase();

            while (!isValidDirection(input) && !input.equals("quit")) {
                System.out.print("Invalid input. Enter north, east, south, west, or quit: ");
                input = scanner.nextLine().trim().toLowerCase();
            }

            if (input.equals("quit")) {
                System.out.println("Thanks for playing!");
                break;
            }

            Room nextRoom = roomHandler.currentRoom.getExit(input);

            if (nextRoom == null) {
                roomHandler.changeRoom(input);
                continue;
            }

            boolean firstVisit = !nextRoom.ranEncounter;
            boolean monsterRoom = isMonsterRoom(nextRoom);

            boolean moved = roomHandler.changeRoom(input);

            if (moved) {
                System.out.println("You moved " + input + ".");

                if (firstVisit && monsterRoom) {
                    fightEnemy();
                }
            }

            if (player.isDead()) {
                System.out.println("Game Over!");
                break;
            }
        }
    }

    private void fightEnemy() {
        Enemy enemy = new Enemy();

        System.out.println();
        System.out.println("A wild enemy attacks!");

        while (!player.isDead() && !enemy.isDead()) {
            System.out.println();
            System.out.println("==============================");
            System.out.println("Player HP: " + player.getHp());
            System.out.println("Enemy  HP: " + enemy.getHp());
            System.out.println("1. Basic Attack");
            System.out.println("2. Heal");
            System.out.print("Choose an action: ");

            String input = scanner.nextLine().trim();

            while (!input.equals("1") && !input.equals("2")) {
                System.out.print("Invalid input. Enter 1 for basic attack or 2 for heal: ");
                input = scanner.nextLine().trim();
            }

            if (input.equals("1")) {
                int damage = player.basicAttack(enemy);
                System.out.println("Player attacks for " + damage + " damage.");
            } else {
                player.heal();
                System.out.println("Player heals. Current HP: " + player.getHp());
            }

            if (enemy.isDead()) {
                System.out.println("Enemy defeated! Huzzah!");
                break;
            }

            int enemyDamage = enemy.randomAttack(player);
            System.out.println("Enemy attacks for " + enemyDamage + " damage.");

            if (player.isDead()) {
                System.out.println("The player has been defeated! RIP, you'll get 'em next time!");
                break;
            }
        }
    }

    private boolean isValidDirection(String direction) {
        return direction.equals("north")
                || direction.equals("east")
                || direction.equals("south")
                || direction.equals("west");
    }

    private void printValidExits(Room currentRoom) {
        if (currentRoom.getExit("north") != null) {
            System.out.println("- north");
        }
        if (currentRoom.getExit("east") != null) {
            System.out.println("- east");
        }
        if (currentRoom.getExit("south") != null) {
            System.out.println("- south");
        }
        if (currentRoom.getExit("west") != null) {
            System.out.println("- west");
        }
    }

    private boolean isMonsterRoom(Room room) {
        try {
            Field encounterField = Room.class.getDeclaredField("encounter");
            encounterField.setAccessible(true);
            Object encounter = encounterField.get(room);
            return encounter instanceof MonsterEncounterStrat;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        GameHandler game = new GameHandler();
        game.run();
    }
}
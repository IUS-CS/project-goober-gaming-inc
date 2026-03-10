package com.rpg.rpg;

import java.util.Scanner;

public class GameHandler {

    public void run() {
        // Creates a Player and an Enemy
        Character player = new Character();
        Enemy enemy = new Enemy();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Prints HP of the Player and the Enemy
            System.out.println("\n==============================");
            System.out.println("Player HP: " + player.getHp());
            System.out.println("Enemy  HP: " + enemy.getHp());
            System.out.println("==============================");

            // Prompts and receives user input
            String choice;
            while (true) {
                System.out.print("Choose action: (1) Basic Attack  (2) Heal : ");
                choice = scanner.nextLine().trim();

                if (choice.equals("1") || choice.equals("2")) {
                    break;
                }
                System.out.println("Invalid input. This isn't rocket science. Please enter 1 or 2.. or else.");
            }

            // Performs corresponding action
            if (choice.equals("1")) {
                int dmg = player.basicAttack(enemy);
                System.out.println("You used Basic Attack and dealt " + dmg + " damage!");
            } else {
                player.heal();
                System.out.println("You healed yourself! Good move!");
            }

            // Ends the fight if the Enemy is dead before the Enemy acts
            if (enemy.isDead()) {
                System.out.println("\nEnemy HP: 0");
                System.out.println("You win! The enemy has been defeated. Huzzah!");
                break;
            }

            // Damages player by the value returned from the enemy's random action
            int enemyDmg = enemy.randomAttack(player);
            System.out.println("Enemy dealt " + enemyDmg + " damage to you!");

            // Ends the fight if the Player is dead immediately after the Enemy acts
            if (player.isDead()) {
                System.out.println("\nPlayer HP: 0");
                System.out.println("You lose! You have been defeated. You fought valiantly. You'll get 'em next time, Tiger.");
                break;
            }
        }

        scanner.close();
    }
}
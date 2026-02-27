package com.rpg.rpg;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnemyTest {

    @Test
    void heavyAttack_damageIsAlwaysFourOrFive_andDamagesTarget() {
        Enemy enemy = new Enemy();
        Character target = new Character();

        int before = target.getHp();
        int dmg = enemy.heavyAttack(target);

        assertTrue(dmg == 4 || dmg == 5, "heavyAttack damage must be 4 or 5");
        assertEquals(Math.max(0, before - dmg), target.getHp(), "Target HP should decrease by heavy damage");
    }

    @Test
    void randomAttack_damageIsAlwaysInValidRanges() {
        Enemy enemy = new Enemy();

        // Use a fresh target each time so it doesn't die and clamp to 0 too early
        for (int i = 0; i < 200; i++) {
            Character target = new Character();
            int dmg = enemy.randomAttack(target);

            boolean isBasic = (dmg >= 1 && dmg <= 3);
            boolean isHeavy = (dmg == 4 || dmg == 5);

            assertTrue(isBasic || isHeavy, "randomAttack must return basic [1..3] or heavy [4..5] damage");
        }
    }

    @Test
    void randomAttack_heavyOccursAboutOneThirdOfTheTime_statisticalCheck() {
        Enemy enemy = new Enemy();

        int trials = 600;
        int heavyCount = 0;

        for (int i = 0; i < trials; i++) {
            Character target = new Character();
            int dmg = enemy.randomAttack(target);
            if (dmg == 4 || dmg == 5) heavyCount++;
        }

        // Expected heavyCount ≈ 200.
        assertTrue(heavyCount >= 120 && heavyCount <= 280,
                "Heavy attacks should occur roughly ~1/3 of the time. Observed: " + heavyCount);
    }
}
package com.rpg.rpg;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CharacterTest {

    @Test
    void constructor_initialValuesAreInExpectedRanges() {
        Character c = new Character();

        // HP is initialized randomly from 1 through 10
        assertTrue(c.getHp() >= 1 && c.getHp() <= 10, "HP should start in [1,10]");

        // Attack is initialized randomly from 1 through 3
        assertTrue(c.getAttack() >= 1 && c.getAttack() <= 3, "Attack should be in [1,3]");

        // Newly created character should not be dead 
        assertFalse(c.isDead(), "New character should not be dead");
    }

    @Test
    void takeDamage_negativeDamageDoesNotIncreaseHp() {
        Character c = new Character();
        int before = c.getHp();

        c.takeDamage(-999); 

        assertEquals(before, c.getHp(), "Negative damage should not change HP");
        assertFalse(c.isDead(), "Character should not become dead from negative damage");
    }

    @Test
    void takeDamage_canKillCharacterAndSetsHpToZero() {
        Character c = new Character();

        c.takeDamage(10_000); // guaranteed kill

        assertEquals(0, c.getHp(), "HP should clamp to 0 on death");
        assertTrue(c.isDead(), "isDead should be true when HP hits 0");
    }

    @Test
    void heal_neverExceedsTen_andRevivesFromDead() {
        Character c = new Character();

        // Kill first
        c.takeDamage(10_000);
        assertTrue(c.isDead());
        assertEquals(0, c.getHp());

        // Heal once (random 1 through 3). Heal() also sets isDead=false when hp >= 0.
        c.heal();

        assertFalse(c.isDead(), "heal() should revive from dead");
        assertTrue(c.getHp() >= 1 && c.getHp() <= 3, "From 0 HP, one heal should result in HP in [1,3]");
        assertTrue(c.getHp() <= 10, "HP must never exceed 10");
    }

    @Test
    void basicAttack_returnsAttackValue_andDamagesTarget() {
        Character attacker = new Character();
        Character target = new Character();

        int targetBefore = target.getHp();
        int damage = attacker.basicAttack(target);

        assertEquals(attacker.getAttack(), damage, "basicAttack should deal exactly attack stat damage");
        assertEquals(Math.max(0, targetBefore - damage), target.getHp(), "Target HP should reduce by damage");
    }
}

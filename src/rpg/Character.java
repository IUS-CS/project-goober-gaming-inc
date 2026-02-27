package rpg;

import java.util.Random;
/* HP variable (caps at 10): done
-isDead variable: done
-Basic Attack method (returns damage dealt, which is a random number from 1-3): done
-Heal method (increases HP by 3, caps at 10)
-Hurt method (decreases HP by received number, sets isDead true if HP is 0)*/

public class Character {
    //Idenification stats
    private static final Random rand = new Random();
    private int maxHp;
    private int hp;
    private int attack;

    private boolean isDead;

    public Character() {
        int min = 1, max = 10;
        //generate health points from 1 to cap at 10
        this.maxHp = 10;
        this.hp = rand.nextInt((max - min)+1)+min;

        this.attack = rand.nextInt(3)+1;

        isDead = false;

    }

    public int basicAttack(Character target){
        int damage = this.attack;
        target.takeDamage(damage);
        return damage;
    }
    public void takeDamage(int damage) {
        if(damage < 0){
            damage = 0;
        }

        hp -= damage;

        if(hp <= 0){
            hp = 0;
            isDead = true;
        }
    }
    public void heal() {
        int healing = rand.nextInt(3)+1;

        hp += healing;

        if(hp > maxHp){
            hp = maxHp;
        }

        if(hp >= 0){
            isDead = false;
        }
    }

    public int getHp(){
        return hp;
    }
    public int getAttack(){
        return attack;
    }
    public boolean isDead() {
        return isDead;
    }
}
package rpg;
/* HP variable (caps at 10)
 isDead variable
 Basic Attack method (returns damage dealt, which is a random number from 1-3)
 Heavy Attack method (returns damage dealt, which is a random number from 4-5)
 Hurt method (decreases HP by received number, sets isDead true if HP is 0)
 RandomAction method (calls either Basic Attack or Heavy Attack and returns damage dealt)*/
import java.util.Random;
public class Enemy extends Character {
    private static final Random rand = new Random();

    public int heavyAttack(Character target){
        int min = 4, max = 5;
        int damage = rand.nextInt((max - min)+1)+min;

        System.out.println("Enemy used Heavy Attack");

        target.takeDamage(damage);
        return damage;
    }

    public int randomAttack(Character target){
        int randomizer = rand.nextInt(3)+1;

        if(randomizer <= 2){
            return basicAttack(target);
        }else{
            return heavyAttack(target);
        }
    }
}
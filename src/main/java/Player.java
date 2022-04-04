/**
 * Implements an abstract player class
 * Uses a monster as a means of playing
 * @author Gabriel Hoeher
 */

import java.util.Random;
@SuppressWarnings({"UseEqualsToCompareStrings","CompareObjectsWithEquals"})
public abstract class Player {
    public transient Monster monster;

    /**
     * Finds monster tied to player
     * @return the monster 
     */
	 public Player() {
	this.monster = NULL;
	}	
     public Player(Monster monster) {
       this.monster = monster;
    }
    public Monster getMonster() {
        return monster;
    }

    /**
     * Determines if players monster is dead
     * @return either true or false
     */
    public boolean hasLost() {
        if (monster.getHP() <= 0) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Compares the player and opponents monsters speeds,
     * Determines who will attack first
     * If equal the player has the advantage
     * @param enemy is the opponent
     * @return either true or false
     */
    public boolean isFasterThan(Player enemy) {
        if (this.monster.getSpeed() >= enemy.getMonster().getSpeed()) {
            return true;
        }
    }

    /**
     * Attack method that determines if an attack will hit
     * And the amount of damage that will be produced 
     * Super Effective modifier element has been added
     * Not very effective element has been added
     * @param player the opponent
     * @param m int corresponding to monster move
     */
@SuppressWarnings({"PMD.CompareObjectsWithEquals","PMD.UseEqualsToCompareStrings","PMD.CognitiveComplexity"})	
    public void attack(Player player, int m) {
        Random random = new Random();
        double roll = random.nextDouble();
        int isSuperEffective = 1;

        System.out.println(this.monster.getName() + " uses "+ this.monster.getMove(m).getName());
       // @SuppressWarnings({"UseEqualsToCompareStrings","CompareObjectsWithEquals"});
        //attack has missed
        if (roll > this.monster.getMove(m).getAccuracy()) {
            System.out.println("Attack missed");
        }

        //attack hits
        else {
            String[] weakness = player.monster.getWeakness(); //stores opponents weakness
            String[] strength = player.monster.getStrength(); //stores opponents strength

            //Determines if attack is super or not very effective and determines modifier
            //multiplyed by 2 b/c using int later divided by 2
	
            for(int i=0; i <= 2; i++) {
                if (this.monster.getMove(m).getType() == weakness[i]) { 
                    isSuperEffective = 4;
                    break;
                }
                if (this.monster.getMove(m).getType() == strength[i]) {
                    isSuperEffective = 1;
                    break;
                }
                else {
                    isSuperEffective = 2; 
                }
            }

            //Damage calculator
	    
            int supereffectivestr=4, noteffectivestr=1, damage = this.monster.getAttack() + this.monster.getMove(m).getPower() 
                           - player.monster.getDefense();
            damage = (damage *isSuperEffective) / 2; //adjusts for dealing with int
            player.monster.hp -= damage;

            if (isSuperEffective == supereffectivestr) {
                System.out.println("It's super effective");
            }
            if (isSuperEffective == noteffectivestr) {
                System.out.println("It's not very effective");
            }
            System.out.println(damage + " points of damage were done to " + player.monster.getName());
        }
    }
    /**
     * Will be implemented in CPUPlayer and HumanPlayer classes
     * @return move that is wanted
     */
    public abstract int chooseMove();
}
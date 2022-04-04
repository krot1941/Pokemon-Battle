/**
 * Implements an abstract player class
 * Uses a monster as a means of playing
 * @author Gabriel Hoeher
 */

import java.util.Random;
//@SuppressWarnings({"UseEqualsToCompareStrings","CompareObjectsWithEquals"})
public abstract class Player {
    public transient Monster monster;

    /**
     * Finds monster tied to player
     * @return the monster 
     */	
	public Player() {
      System.out.println("zadolbalsya bl");
	 }
    public Monster getMonster() {
        return monster;
    }

    /**
     * Determines if players monster is dead
     * @return either true or false
     */
    public boolean hasLost() {
        return monster.getHP() <= 0;
    }

    /**
     * Compares the player and opponents monsters speeds,
     * Determines who will attack first
     * If equal the player has the advantage
     * @param enemy is the opponent
     * @return either true or false
     */
    public boolean isFasterThan(Player enemy) {
       return  this.monster.getSpeed() >= enemy.getMonster().getSpeed();
    }

    /**
     * Attack method that determines if an attack will hit
     * And the amount of damage that will be produced 
     * Super Effective modifier element has been added
     * Not very effective element has been added
     * @param player the opponent
     * @param m int corresponding to monster move
     */
@SuppressWarnings({"PMD.DataflowAnomalyAnalysis","PMD.CompareObjectsWithEquals","PMD.UseEqualsToCompareStrings","PMD.CognitiveComplexity"})	
    public void attack(Player player, int movement) {
        Random random = new Random();
        double roll = random.nextDouble();
        int issupereffective = 10;
	int supereffectivestr=4, noteffectivestr=1, normeffective = 2;
        System.out.println(this.monster.getName() + " uses "+ this.monster.getMove(movement).getName());
       // @SuppressWarnings({"UseEqualsToCompareStrings","CompareObjectsWithEquals"});
        //attack has missed
        if (roll > this.monster.getMove(movement).getAccuracy()) {
            System.out.println("Attack missed");
        }

        //attack hits
        else {
            String[] weakness = player.monster.getWeakness(); //stores opponents weakness
            String[] strength = player.monster.getStrength(); //stores opponents strength

            //Determines if attack is super or not very effective and determines modifier
            //multiplyed by 2 b/c using int later divided by 2
	
            for(int i=0; i <= 2; i++) {
                if (this.monster.getMove(movement).getType() == weakness[i]) { 
                    issupereffective = supereffectivestr;
                    break;
                }
                if (this.monster.getMove(movement).getType() == strength[i]) {
                    issupereffective = noteffectivestr;
                    break;
                }
                else {
                    issupereffective = normeffective; 
                }
            }

            //Damage calculator
	    
            int damage = this.monster.getAttack() + this.monster.getMove(movement).getPower() 
                           - player.monster.getDefense();
            damage = (damage *issupereffective) / 2; //adjusts for dealing with int
            player.monster.hp -= damage;

            if (issupereffective == supereffectivestr) {
                System.out.println("It's super effective");
            }
            if (issupereffective == noteffectivestr) {
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
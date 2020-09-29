import  java.util.Random;
/**
 * special ship that act like the human but cant die.
 */
public class SpaceShipSpecial extends SpaceShip {
    /**
     *
     * @return this function give the special ship Immortality and some random moves
     */
    public boolean isDead() {
        return false;
    }
    public void doAction(SpaceWars game) {
        Random rand=new Random();
        int rand_int1 = rand.nextInt(20);
        this.Shield=false;
        int turn =0;
        boolean accel=false;
        if(rand_int1==1){
            fire(game);
            turn=1;
            this.shieldOn();
            accel=true;
        }
        else {
            turn=-1;
        }
        setCurrentEnergylevel();
        this.firetime+=1;
        this.ShipPhysics.move(accel,turn);
    }
}

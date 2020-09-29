import oop.ex2.GameGUI;
import oop.ex2.SpaceShipPhysics;
import java.awt.*;

/**
 * This ship pursues other ships and tries to fire at them. It will always accelerate,
 * and turn towards the nearest ship. When its angle to the nearest ship is less than 0.21
 * radians, it will try to fire.
 */
public class SpaceShipAggressive extends SpaceShip {
    /**
     * Does the actions of this ship for this round.
     * This is called once per round by the SpaceWars game driver.
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
        this.Shield=false;
        boolean accel=true;
        int turn =0;
        double closetAngle=this.ShipPhysics.angleTo(game.getClosestShipTo(this).ShipPhysics);
        if (closetAngle<0)turn=-1;
        if (closetAngle>0) turn =1;
        if (closetAngle<0.21)fire(game);
        this.ShipPhysics.move(accel,turn);
        this.firetime+=1;
        setCurrentEnergylevel();


    }
}

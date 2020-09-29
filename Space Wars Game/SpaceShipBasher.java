import oop.ex2.GameGUI;
import oop.ex2.SpaceShipPhysics;
import java.awt.*;

/**
 * This ship attempts to collide with other ships. It will always accelerate, and will
 * constantly turn towards the closest ship. If it gets within a distance of 0.19 units (inclusive)
 * from another ship, it will attempt to turn on its shields.
 */
public class SpaceShipBasher extends SpaceShip {
    /**
     * Does the actions of this ship for this round.
     * This is called once per round by the SpaceWars game driver.
     * @param game the game object to which this ship belongs.
     */
    /**
     * Does the actions of this ship for this round.
     *  This is called once per round by the SpaceWars game driver.
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
        this.Shield=false;
        boolean accel=true;
        int turn =0;
        double closetDistance=this.ShipPhysics.distanceFrom(game.getClosestShipTo(this).ShipPhysics);
        double closetAngle=this.ShipPhysics.angleTo(game.getClosestShipTo(this).ShipPhysics);
        if (closetAngle<0)turn=-1;
        if (closetAngle>0) turn =1;
        if (closetDistance<=0.19)shieldOn();
        this.ShipPhysics.move(accel,turn);
        this.firetime+=1;
        setCurrentEnergylevel();


    }
}

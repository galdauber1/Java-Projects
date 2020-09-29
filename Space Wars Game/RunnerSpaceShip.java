import oop.ex2.GameGUI;
import oop.ex2.SpaceShipPhysics;
import java.awt.*;
/**
 * This spaceship attempts to run away from the fight. It will always accelerate, and
 * will constantly turn away from the closest ship. If the nearest ship is closer than 0.25 units,
 * and if its angle to the Runner is less than 0.23 radians, the Runner feels threatened and will
 * attempt to teleport.
 */
public class RunnerSpaceShip extends SpaceShip {
    /**
     * Does the actions of this ship for this round.
     * This is called once per round by the SpaceWars game driver.
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
        this.Shield=false;
        boolean accel=true;
        int turn =0;
        double closetDistance=this.ShipPhysics.distanceFrom(game.getClosestShipTo(this).ShipPhysics);
        double closetAngle=this.ShipPhysics.angleTo(game.getClosestShipTo(this).ShipPhysics);
        if (closetAngle<0)turn=1;
        if (closetAngle>0) turn =-1;
        if (closetDistance<0.25&closetAngle<0.23)teleport();
        this.ShipPhysics.move(accel,turn);
        this.firetime+=1;
        setCurrentEnergylevel();


    }
}

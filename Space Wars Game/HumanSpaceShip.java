import oop.ex2.GameGUI;
import oop.ex2.SpaceShipPhysics;

import java.awt.*;

public class HumanSpaceShip extends SpaceShip {
    /**
     *
     * @return the image of the human spaceship.
     */
    public Image getImage(){
        if (this.getsheild()) return  GameGUI.SPACESHIP_IMAGE_SHIELD;
        return GameGUI.SPACESHIP_IMAGE;

    }

    /**
     * Does the actions of this ship for this round.
     * This is called once per round by the SpaceWars game driver.
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
        this.Shield=false;
        boolean accel=false;
        int turn =0;
        GameGUI currentgame= game.getGUI();
        if ((currentgame.isTeleportPressed())){
            this.teleport();}
        if(currentgame.isLeftPressed() & currentgame.isUpPressed()){
            accel= true; turn =1;}
        else if(currentgame.isRightPressed() & currentgame.isUpPressed()){
            accel= true; turn =-1;}
        else if (currentgame.isRightPressed()){
            turn=-1;}
        else if (currentgame.isLeftPressed()){
            turn=1;}
        else if (currentgame.isUpPressed()){
            accel=true;}
        else if ((currentgame.isShieldsPressed())){
            this.shieldOn();}
        if( currentgame.isShotPressed()){
            fire(game);}
        this.ShipPhysics.move(accel,turn);
        this.firetime+=1;
        setCurrentEnergylevel();
        }
}


import oop.ex2.GameGUI;
import oop.ex2.SpaceShipPhysics;

/**
 * this ship act exactly the opposite from the humanspaceship
 * if the human press right it goes left.
 * shield is always on.
 */
public class SpaceShipDrunked extends SpaceShip {
    /**
     * Does the actions of this ship for this round.
     * This is called once per round by the SpaceWars game driver.
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
        this.Shield=true;
        boolean accel=true;
        int turn =0;
        GameGUI currentgame= game.getGUI();
        if(currentgame.isLeftPressed() & currentgame.isUpPressed()){accel= true; turn =1;}
        else if(currentgame.isRightPressed() & currentgame.isUpPressed()){accel= true; turn =-1;}
        else if (currentgame.isRightPressed()){ turn=1;}
        else if (currentgame.isLeftPressed()){ turn=-1;}
        else if (currentgame.isUpPressed()){accel=false;}
        else if ((currentgame.isShieldsPressed())){this.Shield=false;}
        else if ((currentgame.isTeleportPressed())){this.teleport();}
        else  if( currentgame.isShotPressed()){fire(game);};
        this.ShipPhysics.move(accel,turn);
        this.firetime+=1;
        setCurrentEnergylevel();







    }

}

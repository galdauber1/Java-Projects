import java.awt.Image;
import oop.ex2.*;
/**
 * The API spaceships need to implement for the SpaceWars game. 
 * It is your decision whether SpaceShip.java will be an interface, an abstract class,
 *  a base class for the other spaceships or any other option you will choose.
 *  
 * @author oop
 */
public class SpaceShip{
    private  int MaxEnergylevel=210;
    private  int CurrentEnergylevel=190;
    private  int HealthLevel=22;
     SpaceShipPhysics ShipPhysics;
     boolean Shield = false;
    int firetime=0;
    SpaceShip(){
        this.ShipPhysics=new SpaceShipPhysics();
    }
    /**
     * Does the actions of this ship for this round. 
     * This is called once per round by the SpaceWars game driver.
     * 
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
    }

    /**
     * This method is called every time a collision with this ship occurs 
     */
    public void collidedWithAnotherShip(){
        if( !this.Shield) {
            this.HealthLevel -= 1;
            this.MaxEnergylevel-=10;
            this.CurrentEnergylevel=Math.max(MaxEnergylevel,CurrentEnergylevel);

        }
        this.CurrentEnergylevel+=18;
        this.MaxEnergylevel+=18;


    }

    /** 
     * This method is called whenever a ship has died. It resets the ship's 
     * attributes, and starts it at a new random position.
     */
    public void reset(){
        if(this.HealthLevel==0);
        this.MaxEnergylevel=210;
        this.CurrentEnergylevel=190;
        this.HealthLevel=22;
        this.Shield=false;
        this.firetime=0;
        this.ShipPhysics=new SpaceShipPhysics();
    }
    /**
     * Checks if this ship is dead.
     * 
     * @return true if the ship is dead. false otherwise.
     */
    public boolean isDead() {
        if (this.HealthLevel<=0) return true;
        return false;
    }
    /**
     * Gets the physics object that controls this ship.
     * 
     * @return the physics object that controls the ship.
     */
    public SpaceShipPhysics getPhysics() {

        return this.ShipPhysics;
    }
    /**
     * This method is called by the SpaceWars game object when ever this ship
     * gets hit by a shot.
     */
    public void gotHit() {
        if( !this.Shield) {
            this.HealthLevel -= 1;
            this.MaxEnergylevel-=10;
            this.CurrentEnergylevel=Math.max(MaxEnergylevel,CurrentEnergylevel);

        }

    }

    /**
     * Gets the image of this ship. This method should return the image of the
     * ship with or without the shield. This will be displayed on the GUI at
     * the end of the round.
     * 
     * @return the image of this ship.
     */
    public Image getImage(){
        if (this.getsheild()) return GameGUI.ENEMY_SPACESHIP_IMAGE_SHIELD;
        return GameGUI.ENEMY_SPACESHIP_IMAGE;
    }
    /**
     * Attempts to fire a shot.
     * 
     * @param game the game object.
     */
    public void fire(SpaceWars game) {
        if (this.CurrentEnergylevel >=19 & firetime>7){
            game.addShot(ShipPhysics);
            firetime=0;
            CurrentEnergylevel-=19;

        }
       
    }
    /**
     * Attempts to turn on the shield.
     */
    public void shieldOn() {
        if (this.CurrentEnergylevel >=3) {
            this.Shield = true;
            CurrentEnergylevel-=3;
        }
    }

    /**
     * Attempts to teleport.
     */
    public void teleport() {
        if(CurrentEnergylevel>=190){
            this.ShipPhysics=new SpaceShipPhysics();
            CurrentEnergylevel-=190;
        }
    }

    /**
     *
     * @return true if sheild on false else
     */
    public  boolean getsheild(){
        return this.Shield;
    }
    /**
     * add 1 to the energy level
     */
    public void setCurrentEnergylevel(){
     if (this.CurrentEnergylevel < this.MaxEnergylevel){
         this.CurrentEnergylevel+=1;
     }
    }
}

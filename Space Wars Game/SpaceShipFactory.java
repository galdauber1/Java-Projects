import oop.ex2.*;

public class SpaceShipFactory {
    static SpaceShip[] SpaceShipArry;

    /**
     *
     * @param args - the current arguments
     * @return array of spaceship by the arguments.
     */
    public static SpaceShip[] createSpaceShips(String[] args) {
        SpaceShipArry= new SpaceShip[args.length];
        for (int i=0; i<args.length;i++){
            if (args[i].equals("h")){
                SpaceShipArry[i]= new HumanSpaceShip();
            }
            else  if ( args[i].equals("r")){
                SpaceShipArry[i]=new RunnerSpaceShip();
            }

            else  if ( args[i].equals("a")){
                SpaceShipArry[i]=new SpaceShipAggressive();
            }
            else  if ( args[i].equals("d")){
                SpaceShipArry[i]=new SpaceShipDrunked();
            }
            else  if ( args[i].equals("b")){
                SpaceShipArry[i]=new SpaceShipBasher();
            }
            else  if ( args[i].equals("s")){
                SpaceShipArry[i]=new SpaceShipSpecial();
            }



        }
        return SpaceShipArry;

    }
}

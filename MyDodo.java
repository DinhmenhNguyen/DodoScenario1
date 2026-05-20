import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 *
 * @author Sjaak Smetsers & Renske Smetsers-Weeda
 * @version 3.0 -- 20-01-2017
 */
public class MyDodo extends Dodo
{
    private int myNrOfEggsHatched;
    
    public MyDodo() {
        super( EAST );
        myNrOfEggsHatched = 0;
    }

    public void act() {
    }

    /**
     * Move one cell forward in the current direction.
     * 
     * <P> Initial: Dodo is somewhere in the world
     * <P> Final: If possible, Dodo has moved forward one cell
     *
     */
    public void move() {
        if ( canMove() ) {
            step();
        } else {
            showError( "I'm stuck!" );
        }
    }

    /**
     * Test if Dodo can move forward, (there are no obstructions
     *    or end of world in the cell in front of her).
     * 
     * <p> Initial: No requirements
     * <p> Final: equal to initial situation
     * 
     * @return boolean true if Dodo can move (no obstructions ahead)
     *                 false if Dodo can't move
     *                      (an obstruction or end of world ahead)
     */
    public boolean canMove() {
        if ( fenceAhead() ){
            return false;
        } else {
            return true;
        }
    }

    /**
     * Hatches the egg in the current cell by removing
     * the egg from the cell.
     * Gives an error message if there is no egg
     * 
     * <p> Initial: Dodo is somewhere in the world. There is an egg in Dodo's cell.
     * <p> Final: Dodo is in the same cell. The egg has been removed (hatched).     
     */    
    public void hatchEgg () {
        if ( onEgg() ) {
            pickUpEgg();
            myNrOfEggsHatched++;
        } else {
            showError( "There was no egg in this cell" );
        }
    }
    
    /**
     * Returns the number of eggs Dodo has hatched so far.
     * 
     * @return int number of eggs hatched by Dodo
     */
    public int getNrOfEggsHatched() {
        return myNrOfEggsHatched;
    }
    
    /**
     * Move given number of cells forward in the current direction.
     * 
     * <p> Initial: No requirements
     * <p> Final: If possible, Dodo has taken 'distance' nr of steps in the
     *            direction she is facing. If 'distance' is too large, then
     *            Dodo stops at the edge of the world and error shown
     * 
     * @param   int distance: the number of steps made
     */
    public void jump( int distance ) {
        int nrStepsTaken = 0;               // set counter to 0
        while ( nrStepsTaken < distance ) { // check if more steps must be taken  
            move();                         // take a step
            nrStepsTaken++;                 // increment the counter
            
            // compliments steps taken
            System.out.println ("moved " + nrStepsTaken + " cell(s) foward");
        }
    }
    
    /**
     * Walks to edge of the world printing the coordinates at each step
     * 
     * <p> Initial: Mimi on the West side, facing east.
     * <p> Final:   Mimi on the East side, facing East. Coordinates of each cell
     *              printed in console
     *
     */

    public void walkToWorldEdge( ){
        while( ! borderAhead() && ! fenceAhead() ){
            move();
        }
    }

    /**
     * Test if Dodo can lay an egg.
     *          (there is not already an egg in the cell)
     * 
     * <p> Initial: Dodo is somewhere in the world
     * <p> Final:   Same as initial situation
     * 
     * @return boolean true if Dodo can lay an egg (no egg there)
     *                 false if Dodo can't lay an egg
     *                      (already an egg in the cell)
     */

    public boolean canLayEgg( ){
        if( onEgg() ){
            return false;
        }else{
            return true;
        }
    }
    
    /**
     * Turn 180 degrees from current position
     * 
     * <p> Initial: No requirements
     * <p> Final: Dodo is in the same cell, facing the opposite direction
     * 
     */
    
    public void turn180( ){
        turnRight();
        turnRight();
    }
    
    /**
     * Jumps over a fence by turning and moving
     * 
     * <p> Initial: Dodo is facing a fence anywhere in the world
     * <p> Final: Dodo is on the opposite side of the fence from before
     * 
     */
    
    public void jumpOverFence( ) {
        if ( fenceAhead() ) {
            turnLeft();
            move();
            turnRight();
            move();
            move();
            turnRight();
            move();
            turnLeft();
        }
    }
    
    /**
     * Checks if there is a grain ahead of the dodo by,
     * walking forward and back
     *
     *    
     * <p> Initial: No requirements
     * <p> Final: equal to initial situation
     *
     */
    
     public boolean grainAhead() {
         move();
         boolean steppedOnGrain = false;
         if ( onGrain() ) {
              steppedOnGrain=true;
         }
         stepOneCellBackwards();
         return steppedOnGrain;
         
     }
     
    /**
      * Walks forward untill it is ontop of an egg
      * 
      * <p> Initial: An egg lies x cells ahead of Dodo. The cells between Dodo
      *              and the egg are empty
      * <p> Final: Dodo is standing on top of the egg. Dodo is facing the
      *            original direction
      * 
      */
     public void goToEgg() {
         while ( ! onEgg() && ! borderAhead() ) {
             move();
         }
     }
     
    /**
     * Turns back and moves towards edge of the world and turns back around
     * 
     * <p> Initial: Dodo is somewhere in the world
     * <p> Final: Dodo is at the start of the world and is facing equal to 
     *            initial situation
     * 
     */
    public void goBackToStartOfRowAndFaceBack( ) {
        turn180();
        walkToWorldEdge();
        turn180();
    }
    
    /**
     * Walks to edge of the world while jumping over fences IF there are 
     * fences obstructing the way
     * 
     * <p> Initial: Dodo is anywhere in the world with fences
     * <p> Final: Is at the edge of the world facing the same direction as
     *            as initial situation
     */
    public void walkToWorldEdgeClimbingOverFences() {
        while( ! borderAhead() ) {
            move();
            if( fenceAhead() ) {
                jumpOverFence();
            }
        }
    }
    
    /**
     * Walks to edge of the world while picking up grains and printing
     * their coordinates in the console
     * 
     * <p> Initial: Dodo is anywhere in the world with grains
     * <p> Final: Dodo reaches the edge of the world or against a
     *     fence and prints all the picked up grains in the console
     * 
     */
    public void pickUpGrainsaAndPrintCoordinates() {
        while( ! borderAhead() ) {
            move();
            if (onGrain()) {
                System.out.println (super.getX()+ ", " + super.getY());
                pickUpGrain();
            }
        }
    }
    
    /**
     * Steps one cell backwards
     * 
     * <p> Initial: No requirements
     * <p> Final: Dodo takes one step backwards
     * 
     */
    public void stepOneCellBackwards() {
        turn180();
        move();
        turn180();
    }
    
    /**
     * Walks to edge of the world while laying eggs in empty nests
     * 
     * <p> Initial: Dodo is anywhere in the world with empty nests
     * <p> Final: Dodo is at the edge of the world and has filled
     *            the nests on the way which were empty
     * 
     */
    public void walkToWorldEdgeLayEggsInEmptyNest() {
        while( ! borderAhead() ) {
            move();
            if (onNest()) {
                if(canLayEgg()) {
                    layEgg();
                }
            }
        }
    }
}


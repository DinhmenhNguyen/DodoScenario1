import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 *
 * @author Sjaak Smetsers & Renske Smetsers-Weeda
 * @version 3.0 -- 20-01-2017
 */
public class MyDodo extends Dodo
{
    private int myNrOfEggsHatched;
    
    /**
     * The value for the blue and golden egg swaps
     * 
     */
    public void eggValueSwap() {
        int tijdelijkeWaardeEi;
        BlueEgg waardeBlauweEi = new BlueEgg();
        GoldenEgg waardeGoudenEi = new GoldenEgg();
    
        tijdelijkeWaardeEi = waardeBlauweEi.getValue();
        waardeBlauweEi.setValue(waardeGoudenEi.getValue());
        waardeGoudenEi.setValue(tijdelijkeWaardeEi);
    }

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
        if ( fenceAhead()){
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
        while( ! borderAhead() && ! fenceAhead() ) {
            move();
            if (onNest()) {
                if(canLayEgg()) {
                    layEgg();
                }
            }
        }
    }
    
        /**
     * Walks to edge of the world while jumping over fences IF there are 
     * fences obstructing the way
     * 
     * <p> Initial: Dodo is anywhere in the world with fences and a nest
     * <p> Final: Dodo is standing on the nest with a egg it laid.
     * 
     */
    public void walkToEmptyNestClimbingOverFences() {
        while( ! onNest() ) {
            if( fenceAhead() ) {
                jumpOverFence();
            } else {
                move();
            }
        }
        layEgg();
    }
    
    /**
     * Walks around a fenced area and stops when standing on the egg
     * 
     * <p> Initial: Dodo is anywhere in the world with a fenced area
     * <p> Final: Dodo has walked around a fenced area and is standing on the Egg
     * 
     */
    public void walkAroundFencedArea() {
        while( ! onEgg() ) {
            if(canMove() && ! onEgg() ) {
                turnRight();
                if(canMove() ) {
                    move();
                }
            } else {
                turnLeft();
                move();
            }
        }
    }
    
    /**
     * Walks on a trail of eggs and stops once on the nest
     * 
     * <p> Initial: Is standing next to a trail of eggs
     * <p> Final: Is standing on the next at the end of the trail of eggs
     * 
     */
    public void eggTrailToNest() {
        while( ! onNest()) {
                if(canMove() && eggAhead() || nestAhead()) {
                move();
            } else {
                turnRight();
                if(! eggAhead()) {
                    turn180();
                }
            }
        }
    }
    
    /**
     * Walks towards the nest in a maze of fences 
     * 
     * <p> Initial: Is standing anywhere in a maze of fences with a possible way to the nest
     * <p> Final: Is standing on the nest after traversing through the maze
     * 
     */
    public void walkToNestInFenceMaze() {
        while( ! onNest()) {
            turnRight();
            if(canMove()) {
                move();
            } else {
                turnLeft();
                if(canMove()) {
                    move();
                } else {
                    turnLeft();
                        if(canMove()) {
                        move();
                        } else {
                        turn180();
                        if(canMove()) {
                        move();
                        }
                    }
                }
            }
        }
        showCompliment("Congratulations!! You've found the nest.");
    }
    
    
    /**
     * Mimi faces north
     * 
     * <p> Initial: Mimi is facing any direction
     * <p> Final: Mimi is facing north
     * 
     */
    public void faceNorth() {
        while(getDirection() != NORTH){
            turnRight();
        }
    }
    
    /**
     * Mimi faces east
     * 
     * <p> Initial: Mimi is facing any direction
     * <p> Final: Mimi is facing east
     * 
     */
    public void faceEast() {
        while(getDirection() != EAST){
            turnRight();
        }
    }
    
    /**
     * Mimi faces south
     * 
     * <p> Initial: Mimi is facing any direction
     * <p> Final: Mimi is facing south
     * 
     */
    public void faceSouth() {
        while(getDirection() != SOUTH){
            turnRight();
        }
    }
    
    
    /**
     * Mimi faces west
     * 
     * <p> Initial: Mimi is facing any direction
     * <p> Final: Mimi is facing west
     * 
     */
    public void faceWest() {
        while(getDirection() != WEST){
            turnRight();
        }
    }
    
    /**
     * Mimi faces direction
     * 
     * <p> Initial: Mimi is facing any direction
     * <p> Final: Mimi is facing the direction that is given by the user
     * 
     */
    public void faceDirection(int faceDirection) {
        int direction = 0;
        while(direction < faceDirection) {
            direction++;
        }
        if(direction == 0) {
            faceNorth();
        } else if(direction == 1) {
            faceEast();
        } else if(direction == 2) {
            faceSouth();
        } else if(direction == 3) {
            faceWest();
        } else {
            System.out.println("Deze directie bestaat niet.");
        }
    }
    
    /**
     * Mimi faces direction
     * 
     * <p> Initial: Mimi is facing any direction
     * <p> Final: Mimi is facing the direction that is given by the user
     * 
     */
    public void faceDirectionV2(int faceDirectionV2) {
        if(faceDirectionV2 < 4 && faceDirectionV2 >= 0) {
            // bepalen hoe vaak draaien
            int currentDirection = direction();
            while(direction() != faceDirectionV2) {
                turnRight();
            }
        }
    }
    
    /**
     * Shows if Dodo has reached the destination
     * 
     * <p> Initial: no requirements
     * 
     */
    public boolean locationReached(int x, int y) {
        return getX() == x && getY() == y;
    }
    
    /**
     * Mimi goes to given location
     * 
     * <p> Initial: Mimi is standing anywhere in the world
     * <p> Final: Mimi is standing on given location
     * 
     */
    public void goToLocation(int x, int y) {
        while( ! locationReached(x, y)) {
            if(getX() < x) {
                faceEast();
                if(canMove()) {
                    move();
                }
            } else if(getX() > x) {
                faceWest();
                if(canMove()) {
                    move();
                }
            } else if(getY() < y) {
                faceSouth();
                if(canMove()) {
                    move();
                }
            } else if(getY() > y) {
                faceNorth();
                if(canMove()) {
                    move();
                }
            }
        }
    }
    
    /**
     * Mimi goes to given location
     * 
     * <p> Initial: Mimi is standing anywhere in the world
     * <p> Final: Mimi is standing on given location
     * 
     */
    public void goToLocationV2(int x, int y) {
        while( ! locationReached(x, y)) {
            if(getX() < x) {
                faceDirectionV2(1);
                if(canMove()) {
                    move();
                }
            } else if(getX() > x) {
                faceDirectionV2(3);
                if(canMove()) {
                    move();
                }
            } else if(getY() < y) {
                faceDirectionV2(2);
                if(canMove()) {
                    move();
                }
            } else if(getY() > y) {
                faceDirectionV2(0);
                if(canMove()) {
                    move();
                }
            }
        }
    }
    
    /**
     * Tells u if the written coordinates are correct to the corresponding coordinates
     * 
     */
    public boolean validCoordinates( int x, int y ) {
        return getX() == x && getY() == y;
    }
    
    /**
     * Counts all the eggs Dodo walks over
     * 
     * <p> Initial: Dodo is standing anywhere in the world with eggs
     * <p> Final: Dodo is at the end of the row of the eggs and counts all the eggs
     * 
     */
    public int countEggsInRow() {
        int numberOfEggs = 0;
        goBackToStartOfRowAndFaceBack();
        if(onEgg()) {
            numberOfEggs++;
        }
        while(canMove() && ! borderAhead()) {
            move();
            if(onEgg()) {
                numberOfEggs++;
            }
        }
        return numberOfEggs;
    }
    
    /**
     * Lays an given amount of eggs in a trail
     * 
     * <p> Initial: Dodo is standing anywhere in the world
     * <p> Final: Dodo walked a given amount of steps and laid an egg in each cell walked over
     * 
     */
    public void layTrailOfEggs(int n) {
        int layEggsAmount = 0;
        if(n <1) {
            showError("Te weinig eieren");
        }
        while(layEggsAmount < n) {
            layEgg();
            if(canMove()) {
                move();
            }
            layEggsAmount++;
        }
    }
    
    /**
     * Dodo goes through the entire world and counts all the eggs
     * 
     * <p> Initial: Dodo is standing anywhere in the world
     * <p> Final: Dodo is at the end of the world at the bottom and has counted every egg in the world
     * 
     */
    public void countAllEggsInWorld() {
        int totalEggs = 0;
        int world = getWorld().getHeight();
        for (int i = 0; i < world; i++) {
            goToLocation(0, i);
            faceEast();
            totalEggs = totalEggs + countEggsInRow();
        }
        System.out.println("Totaal: " + totalEggs + " eieren");
    }
    
    /**
     * Dodo goes through the entire world and counts all the eggs and gives back the row with the most eggs
     * 
     * <p> Initial: Dodo is standing anywhere in the world
     * <p> Final: Dodo is at the end of the world at the bottom and has counted every egg in the world and given back the row with the most eggs
     * 
     */
    public void findRowWithMostEggs() {
        int worldHeight = getWorld().getHeight();
        int maxEggs = -1;
        int mostEggsRow = 0;

        for (int i = 0; i < worldHeight; i++) {
            goToLocation(0, i);
            faceEast();
            int eggRow = countEggsInRow();
    
            if (eggRow > maxEggs) {
                maxEggs = eggRow;
                mostEggsRow = i;
            }
        }

        System.out.println("Rij met de meeste eieren: " + mostEggsRow);
        goToLocation(0, 0);
        faceEast();
    }
    
    /**
     * Dodo lays eggs in a staircase form as far as possible
     * 
     * <p> Initial: Dodo is anywhere in the world
     * <p> Final: Dodo has laid a staircase full of eggs
     * 
     */
    public void layEggsInStaircase() {
        int worldHeight = getWorld().getHeight();
        for(int i = 0; i < worldHeight; i++) {
            goToLocation(0,i);
            faceEast();
            layTrailOfEggs(i+1);
        }
    }
    
    /**
     * Dodo lays eggs in a staircase monument where it gets larger by multiplying the previous number
     * 
     * <p> Initial: Dodo is anywhere in the world
     * <p> Final: Dodo has laid a staircase full of eggs
     * 
     */
    public void layEggsInStaircase2() {
        int worldWidth = getWorld().getWidth();
        int eggs = 1;
        layEgg();
        for(int i = 0; i < 4; i++) {
            goToLocation(0,i+1);
            faceEast();
            if(canMove() && !borderAhead() && canLayEgg()) {
                layTrailOfEggs(eggs *=2);
            }
        }
    }
    
    /**
     * Dodo lays a pyramid of eggs
     * 
     * <p> Initial: Dodo is anywhere in the world
     * <p> Final: Dodo has laid a pyramid of eggs
     * 
     */
    public void pyramidOfEggs() {
        int height = 12;
        int width = -1;
        int eggs = 13;
        
        for (int i = 0; i < 6; i++) {
            goToLocation(width+=1,height-=1);
            faceEast();
            layTrailOfEggs(eggs-=2);
        }
    }
    
    /**
     * Dodo counts the average eggs per row
     * 
     * <p> Initial: Dodo is anywhere in the world with eggs
     * <p> Final: Dodo has gone through the whole map and has counted the average eggs per row
     * 
     */
    public double averageEggsPerRow() {
        int worldHeight = getWorld().getHeight();
        int eggs = 0;
        for (int i = 0; i < worldHeight; i++) {
            goToLocation(0, i);
            faceEast();
            eggs += countEggsInRow();
        }
        double average = (double) eggs / worldHeight;
        System.out.println("Gemiddeld aantal eieren per rij: " + average);
        goToLocation(0, 0);
        faceEast();
        return average;
    }
    
    /**
     * Dodo checks the whole map and places an egg on the uneven spot
     * 
     * <p> Inital: Dodo is anywhere on the map with 1 uneven row/column
     * <p> Final: Dodo has filled the uneven spot with an egg
     * 
     */
    public void parityTester() {
        int worldHeight = getWorld().getHeight();
        int worldWidth = getWorld().getWidth();
        int savedx = -1;
        int savedy = -1;
        for(int i = 0; i < worldHeight; i++) {
            goToLocation(0, i);
            faceEast();
            if(countEggsInRow() % 2 != 0) {
                savedy = i;
                System.out.println(savedy);
            }
        }
        
        for(int i = 0; i < worldWidth; i++) {
            goToLocation(i, 0);
            faceSouth();
            if(countEggsInRow() % 2 != 0) {
                savedx = i;
                System.out.println(savedx);
            }
        }
        
        if (savedx != -1 && savedy != -1) {
            goToLocation(savedx, savedy);
            if(canLayEgg()) {
                layEgg();
            }
        }
    }
    
    /**
     * Checks for direction
     * 
     * <p> Inital: Dodo takes a step forward or backwards and goes back
     * <p> Final: Dodo know its direction and is standing on previous cell
     */
    public int direction() {
        int previousX = getX();
        int previousY = getY();
        int direction = -1;
        
        if(borderAhead()) {
            stepOneCellBackwards();
            previousX = getX();
            previousY = getY();
            move();
            if(previousX < getX()) {
                direction = 1;
            } else if(previousX > getX()) {
                direction = 3;
            } else if(previousY < getY()) {
                direction = 2;
            } else if(previousY > getY()){
                direction = 0;
            }
        } else {
            move();
            if(previousX < getX()) {
                direction = 1;
            } else if(previousX > getX()) {
                direction = 3;
            } else if(previousY < getY()) {
                direction = 2;
            } else if(previousY > getY()){
                direction = 0;
            }
            stepOneCellBackwards();
        }
        return direction;
    }
    
    /**
     * Dodo checks the whole map and places an egg on the uneven spot while not knowing any directions
     * 
     * <p> Inital: Dodo is anywhere on the map with 1 uneven row/column
     * <p> Final: Dodo has filled the uneven spot with an egg
     * 
     */
    public void parityTesterWithoutDirection() {
        int worldHeight = getWorld().getHeight();
        int worldWidth = getWorld().getWidth();
        int savedx = -1;
        int savedy = -1;
        for(int i = 0; i < worldHeight; i++) {
            goToLocationV2(0,i);
            faceDirectionV2(1);
            if(countEggsInRow() % 2 != 0) {
                savedy = i;
                System.out.println(savedy);
            }
        }
        
        for(int i = 0; i < worldWidth; i++) {
            goToLocationV2(i,0);
            faceDirectionV2(2);
            if(countEggsInRow() % 2 != 0) {
                savedx = i;
                System.out.println(savedx);
            }
        }
        
        if (savedx != -1 && savedy != -1) {
            goToLocation(savedx, savedy);
            if(canLayEgg()) {
                layEgg();
            }
        }
    }
    
    /**
     * 
     */
    public void makeListOfSupriseEggs() {
            
    }
}
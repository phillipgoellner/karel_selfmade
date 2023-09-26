import com.pgoellner.karel.Karel;

public class MadLabyrinthKarel extends Karel {
    public void run() {
        do {
            if (frontIsClear()) {
                probeSurroundings();
            } else if (rightIsClear()) {
                turnRight();
            } else {
                turnLeft();
            }
        }
        // We continue until we have reached the final field (with a single beeper)
        while (countBeepersOnGround() != 1);
    }

    // Pick up all present beepers, put them back and return the count
    public int countBeepersOnGround() {
        int count = 0;

        while (beeperIsPresent()) {
            pickBeeper();
            count++;
        }

        for (int i = 0; i < count; i++) {
            putBeeper();
        }

        return count;
    }

    /*
     * This checks the surrounding fields for the field with the lowest number of beepers
     * By continuously doing this, the path will eventually lead to the end of the labyrinth
     */
    public void probeSurroundings() {
        int beeperThreshold = 0;

        while (true) {
            for (int i = 0; i < 4; i++) {
                if (frontIsClear()) {
                    move();
                    int newBeeperCount = countBeepersOnGround();
                    // If true, we have found the red finishing field
                    if (newBeeperCount == 1) {
                        return;
                    }
                    // Should the current field be the one with the lowest beeper count (or tied for lowest), we abort the probing
                    if (newBeeperCount <= beeperThreshold) {
                        putBeeper();
                        putBeeper();
                        return;
                    }
                    // Here we return to the previous field
                    else {
                        turnAround();
                        move();
                        turnAround();
                    }
                }
                // Continue with the next field in clockwise order
                turnRight();
            }
            // No suitable field to move to has been found, so the threshold is increased
            beeperThreshold = beeperThreshold + 2;
        }

    }

    void turnAround() {
        turnLeft();
        turnLeft();
    }

    void turnRight() {
        turnLeft();
        turnLeft();
        turnLeft();
    }
}

/**
 * this class creates field, methods, constructors, and members for Domino(es)
 * @author  Kiefer Lin
 */
public class Domino {
    // a field storing the domino value "in front"
    private int dominoInFront;

    // a field storing the domino value "in back"
    private int dominoInBack;

    // a field storing whether the domino is rotated or not
    private boolean rotate;

    /**
     * a constructor creating a single domino using values for the dominoes front value, back value, and rotate value
     * @param dominoInFront an int that is the domino's front value
     * @param dominoInBack  an int that is the domino' back value
     */
    public Domino(int dominoInFront, int dominoInBack) {
        this.dominoInFront = dominoInFront;
        this.dominoInBack = dominoInBack;
        this.rotate = false;
    }

    /**
     * a method that returns the dominoInFront value
     *
     * @return the front value of the domino
     */
    public int getDominoInFront() {
        return this.dominoInFront;
    }

    /**
     * a method that returns the dominoInBack value
     *
     * @return the back value of the domino
     */
    public int getDominoInBack() {
        return this.dominoInBack;
    }

    /**
     * a method that returns the flipped value
     *
     * @return whether the domino has been flipped or not
     */
    public boolean isRotated() {
        return this.rotate;
    }

    /**
     * a method that rotates the dominoes front and back values and then changes the rotate value when rotated
     */
    public void rotate() {
        int save = this.dominoInFront;
        this.dominoInFront = dominoInBack;
        this.dominoInBack = save;
        this.rotate = !this.rotate;
    }

    /**
     * overrides the toString method of Object to show the string representation of the domino
     *
     * @return the string representation of the domino
     */
    @Override
    public String toString() {
        String dominoString = "";
        dominoString = ("[" + this.dominoInFront + "|" + this.dominoInBack + "]");
        return dominoString;
    }
}



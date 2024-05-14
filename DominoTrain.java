/**
 * this class creates field, methods, constructors, and members for a DominoTrain
 * @author Kiefer Lin
 */
public class DominoTrain extends LinkedList{
    // a field storing the startingDomino value
    private int startingDomino;

    /**
     * creates the DominoTrain by taking an int value that is the starting double domino used in the train
     * @param startingDomino the train's starting double domino
     */
    public DominoTrain(int startingDomino) {
        Domino doubleDomino = new Domino(startingDomino,startingDomino);
        LLNode<Domino> firstNode = new LLNode<Domino>(doubleDomino, null);
        setFirstNode(firstNode);
        this.startingDomino = startingDomino;
    }

    /**
     * creates a default constructor for DominoTrain
     */
    public DominoTrain() {
    }

    /**
     * a method that returns the value of the starting double domino
     */
    public int getStartingDomino() {
        return this.startingDomino;
    }

    /**
     * the addToFront method only adds a domino if one side of the domino matches the
     "front" value of the first domino in the train
     * @param domino the domino to add to the front of the train
     */
    public void addToFront(Domino domino) {
        // check if the Domino can be added first
        if (canAdd(domino)) {
            // check if the front value or back value of the Domino matches
            if (domino.getDominoInFront() == getStartingDomino()) {
                domino.rotate();
                super.addToFront(domino);
            }
            else if (domino.getDominoInBack() == getStartingDomino()) {
                super.addToFront(domino);
            }
        }
        else {
            System.out.println("Neither the front nor the back values of this domino match the starting domino");
        }
    }

    /**
     * the addToFront method only adds a domino if one side of the domino matches the
     "front" value of the domino in front of it
     * @param domino hte domino to add to the end of the train
     */
    public void addToEnd(Domino domino) {
        // check if the Domino can be added first
        if (canAdd(domino)) {
            // check if the front value or back value of the Domino matches
            if (domino.getDominoInFront() == getEndValue()) {
                super.addToEnd(domino);
            }
            else if (domino.getDominoInBack() == getEndValue()) {
                domino.rotate();
                super.addToEnd(domino);
            }
        }
        else {
            System.out.println("Neither the front nor the back values of this domino match the previous domino");
        }
    }

    /**
     * a method that returns the int value that is required to add a domino to this train.
     */
    public int getEndValue() {
        LLNode<Domino> nodeptr = getFirstNode();
        // loops through till the last node - designated by the getNext() function being null
        while (nodeptr.getNext() != null)
            nodeptr = nodeptr.getNext();
        return nodeptr.getElement().getDominoInBack();
    }

    /**
     * a method that returns whether the domino matches the "end value" for the train
     * @param domino a domino that you are trying to add to the train
     * @return a boolean that shows whether th domino matches the "end value" for the rain
     */
    public boolean canAdd(Domino domino) {
        // checks if either the front value or back value of the Domino is equal to the end value of the Domino being added to
        if ((domino.getDominoInFront() == getEndValue()) || (domino.getDominoInBack() == getEndValue())) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * a method that returns a string representation of DominoTrain
     * @return
     */
    @Override
    public String toString() {
        LLNode<Domino> nodeptr = getFirstNode();
        String nodesString = nodeptr.getElement().toString();
        // loops through the DominoTrain and prints out its contents
        while (nodeptr.getNext() != null) {
            nodeptr = nodeptr.getNext();
            nodesString = nodesString + ", " + nodeptr.getElement().toString();
        }
        return nodesString;
    }
}


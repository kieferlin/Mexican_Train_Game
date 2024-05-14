import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * a JUnit class to conduct tests on the other classes in the project
 * @author Kiefer Lin
 */

public class JUnit {
    /**
     * this method tests the firstElement method in the Linked List class by first creating a node that is null and a linked list
     * this method tests a linked list with null nodes
     */
    @Test
    public void firstElementTester1() {
        LLNode<Integer> testNode1 = new LLNode<Integer>(null, null);

        LinkedList<Integer> testLinkedList = new LinkedList<Integer>();
        testLinkedList.addToFront(testNode1.getElement());

        assertEquals(null, testLinkedList.firstElement());
    }

    /**
     * this method tests the firstElement method in the Linked List class by first creating a node and a linked list
     * this method tests a linked list with one node
     */
    @Test
    public void firstElementTester2() {
        LLNode<Integer> testNode1 = new LLNode<Integer>(0, null);

        LinkedList<Integer> testLinkedList = new LinkedList<Integer>();
        testLinkedList.addToFront(testNode1.getElement());

        assertEquals(0, (int) testLinkedList.firstElement());
    }

    /**
     * this method tests the firstElement method in the Linked List class by first creating multiple nodes and a linked list
     * this method tests a linked list with many nodes
     */
    @Test
    public void firstElementTester3() {
        LLNode<Integer> testNode2 = new LLNode<Integer>(10, null);
        LLNode<Integer> testNode1 = new LLNode<Integer>(5, testNode2);

        LinkedList<Integer> testLinkedList = new LinkedList<Integer>();
        testLinkedList.addToFront(testNode1.getElement());

        assertEquals(5, (int) testLinkedList.firstElement());
    }

    /**
     * this method tests the remove method in the linked list class
     * this method tests a linked list with no nodes
     */
    @Test
    public void removeTester1() {
        LinkedList<Integer> testLinkedList = new LinkedList<Integer>();

        assertEquals(0, testLinkedList.length());

        boolean thrown = false;
        try {
            testLinkedList.removeFromEnd();
        }
        catch (NoSuchElementException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    /**
     * this method tests the remove method in the linked list class
     * this method tests a linked list with one node
     */
    @Test
    public void removeTester2() {
        LLNode<Integer> testNode1 = new LLNode<Integer>(5, null);

        LinkedList<Integer> testLinkedList = new LinkedList<Integer>();
        testLinkedList.addToFront(testNode1.getElement());

        assertEquals(1, testLinkedList.length());

        testLinkedList.removeFromEnd();

        assertEquals(0, testLinkedList.length());
    }

    /**
     * this method tests the remove method in the linked list class
     * this method tests a linked list with many nodes
     */
    @Test
    public void removeTester3() {
        LLNode<Integer> testNode2 = new LLNode<Integer>(10, null);
        LLNode<Integer> testNode1 = new LLNode<Integer>(5, testNode2);

        LinkedList<Integer> testLinkedList = new LinkedList<Integer>();
        testLinkedList.addToFront(testNode1.getElement());
        testLinkedList.addToEnd(testNode2.getElement());

        assertEquals(2, testLinkedList.length());

        testLinkedList.removeFromEnd();

        assertEquals(1, testLinkedList.length());
    }

    /**
     * this method tests the constructor AND the getDomino values for the Domino class
     */
    @Test
    public void dominoConstructor() {
        Domino newDomino = new Domino(5,6);
        assertEquals(5, newDomino.getDominoInFront());
        assertEquals(6, newDomino.getDominoInBack());
    }

    /**
     * this method tests the isRotated method for the Domino class, testing both true and false cases
     * this method also tests the getDomino values to see if they align with the isRotated value
     * this method tests when the domino has NOT been flipped AT ALL
     */
    @Test
    public void dominoIsFlipped1() {
        Domino newDomino = new Domino(4,9);
        assertEquals(false, newDomino.isRotated());
        assertEquals(4, newDomino.getDominoInFront());
        assertEquals(9, newDomino.getDominoInBack());
    }

    /**
     * this method tests the isRotated method for the Domino class, testing both true and false cases
     * this method also tests the getDomino values to see if they align with the isRotated value
     * this method tests when the domino has been flipped once
     */
    @Test
    public void dominoIsFlipped2() {
        Domino newDomino = new Domino(4,9);
        assertEquals(false, newDomino.isRotated());
        assertEquals(4, newDomino.getDominoInFront());
        assertEquals(9, newDomino.getDominoInBack());

        newDomino.rotate();
        assertEquals(true, newDomino.isRotated());
        assertEquals(9, newDomino.getDominoInFront());
        assertEquals(4, newDomino.getDominoInBack());
    }

    /**
     * this method tests the isRotated method for the Domino class, testing both true and false cases
     * this method also tests the getDomino values to see if they align with the isRotated value
     * this method tests when the domino has been flipped twice
     */
    @Test
    public void dominoIsFlipped3() {
        Domino newDomino = new Domino(4,9);
        assertEquals(false, newDomino.isRotated());
        assertEquals(4, newDomino.getDominoInFront());
        assertEquals(9, newDomino.getDominoInBack());

        newDomino.rotate();
        assertEquals(true, newDomino.isRotated());
        assertEquals(9, newDomino.getDominoInFront());
        assertEquals(4, newDomino.getDominoInBack());

        newDomino.rotate();
        assertEquals(false, newDomino.isRotated());
        assertEquals(4, newDomino.getDominoInFront());
        assertEquals(9, newDomino.getDominoInBack());
    }

    /**
     * this method tests the isRotated method for the Domino class, testing both true and false cases
     * this method also tests the getDomino values to see if they align with the isRotated value
     * this method tests when the domino has been flipped three times
     */
    @Test
    public void dominoIsFlipped4() {
        Domino newDomino = new Domino(4,9);
        assertEquals(false, newDomino.isRotated());
        assertEquals(4, newDomino.getDominoInFront());
        assertEquals(9, newDomino.getDominoInBack());

        newDomino.rotate();
        assertEquals(true, newDomino.isRotated());
        assertEquals(9, newDomino.getDominoInFront());
        assertEquals(4, newDomino.getDominoInBack());

        newDomino.rotate();
        assertEquals(false, newDomino.isRotated());
        assertEquals(4, newDomino.getDominoInFront());
        assertEquals(9, newDomino.getDominoInBack());

        newDomino.rotate();
        assertEquals(true, newDomino.isRotated());
        assertEquals(9, newDomino.getDominoInFront());
        assertEquals(4, newDomino.getDominoInBack());
    }

    /**
     * this method tests the toString method using the constructor in the Domino class
     * this method tests the toString method when the domino isn't rotated at all
     */
    @Test
    public void dominoToString1() {
        Domino newDomino = new Domino(7,3);
        assertEquals("[7|3]", newDomino.toString());
    }

    /**
     * this method tests the toString method using the constructor in the Domino class
     * this method tests the toString method when the domino is rotated once
     */
    @Test
    public void dominoToString2() {
        Domino newDomino = new Domino(7,3);
        assertEquals("[7|3]", newDomino.toString());

        newDomino.rotate();
        assertEquals("[3|7]", newDomino.toString());
    }

    /**
     * this method tests the toString method using the constructor in the Domino class
     * this method tests the toString method when the domino is rotated twice
     */
    @Test
    public void dominoToString3() {
        Domino newDomino = new Domino(7,3);
        assertEquals("[7|3]", newDomino.toString());

        newDomino.rotate();
        assertEquals("[3|7]", newDomino.toString());

        newDomino.rotate();
        assertEquals("[7|3]", newDomino.toString());
    }

    /**
     * this method tests the constructor AND the getStartingDomino method of the DominoTrain class
     */
    @Test
    public void dominoTrain() {
        DominoTrain newDominoTrain = new DominoTrain(6);
        assertEquals(6,newDominoTrain.getStartingDomino());
    }

    /**
     * this method tests the addToFront AND getEndValue methods by adding a new domino to the DominoTrain using the "front" value
       of the given domino
     */
    @Test
    public void dominoTrainAddToFront1() {
        DominoTrain newDominoTrain = new DominoTrain(6);
        Domino newDomino = new Domino(6,8);
        newDominoTrain.addToFront(newDomino);

        assertEquals(6, newDominoTrain.getEndValue());
    }

    /**
     * this method tests the addToFront AND getEndValue methods by adding a new domino to the DominoTrain using the "back" value
       of the given domino
     */
    @Test
    public void dominoTrainAddToFront2() {
        DominoTrain newDominoTrain = new DominoTrain(6);
        Domino newDomino = new Domino(8,6);
        newDominoTrain.addToFront(newDomino);

        assertEquals(6, newDominoTrain.getEndValue());
    }

    /**
     * this method tests the addToEnd AND getEndValue methods by adding a new domino to the DominoTrain using the "front" value
       of the given domino
     */
    @Test
    public void dominoTrainAddToEnd1() {
        DominoTrain newDominoTrain = new DominoTrain(6);
        Domino newDomino = new Domino(6,8);
        newDominoTrain.addToEnd(newDomino);

        assertEquals(8, newDominoTrain.getEndValue());
    }

    /**
     * this method tests the addToEnd AND getEndValue methods by adding a new domino to the DominoTrain using the "front" value
       of the given domino
     */
    @Test
    public void dominoTrainAddToEnd2() {
        DominoTrain newDominoTrain = new DominoTrain(6);
        Domino newDomino = new Domino(8,6);
        newDominoTrain.addToEnd(newDomino);

        assertEquals(8, newDominoTrain.getEndValue());
    }

    /**
     * this method tests the canAdd method by creating a domino that CAN be added to the DominoTrain
     */
    @Test
    public void dominoCanAdd1() {
        DominoTrain newDominoTrain = new DominoTrain(6);
        Domino newDomino = new Domino(8, 6);

        assertEquals(true, newDominoTrain.canAdd(newDomino));
    }

    /**
     * this method tests the canAdd method by creating a domino that can NOT be added to the DominoTrain
     */
    @Test
    public void dominoCanAdd2() {
        DominoTrain newDominoTrain = new DominoTrain(6);
        Domino newDomino = new Domino(3, 5);

        assertEquals(false, newDominoTrain.canAdd(newDomino));
    }

    /**
     * this method tests the toString method of DominoTrain by creating a DominoTrain using its constructor and then reading its values
     */
    @Test
    public void dominoTrainToString1() {
        DominoTrain newDominoTrain = new DominoTrain(6);

        assertEquals("[6|6]", newDominoTrain.toString());
    }

    /**
     * this method tests the toString method of DominoTrain by creating a DominoTrain using its constructor, adding a new Domino, and then reading its values
     */
    @Test
    public void dominoTrainToString2() {
        DominoTrain newDominoTrain = new DominoTrain(6);
        Domino newDomino = new Domino(6,7);
        newDominoTrain.addToEnd(newDomino);

        assertEquals("[6|6], [6|7]", newDominoTrain.toString());
    }

    /**
     * this method tests the toString method of DominoTrain by creating a DominoTrain using its constructor, adding a new Domino, and then reading its values
     */
    @Test
    public void dominoTrainToString3() {
        DominoTrain newDominoTrain = new DominoTrain(6);
        Domino newDomino1 = new Domino(6,7);
        Domino newDomino2 = new Domino(7,8);
        newDominoTrain.addToEnd(newDomino1);
        newDominoTrain.addToEnd(newDomino2);

        assertEquals("[6|6], [6|7], [7|8]", newDominoTrain.toString());
    }
}


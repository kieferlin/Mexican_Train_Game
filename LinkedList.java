import java.util.NoSuchElementException;

/**
 * this class creates field, methods, constructors, and members for a LinkedList of LLNodes
 * @author Kiefer Lin
 */

public class LinkedList<T> implements Iterable<T> {
    /**
     * the first node of the list, or null if the list is empty
     */
    private LLNode<T> firstNode;

    /**
     * creates an initially empty linked list
     */
    public LinkedList() {
        firstNode = null;
    }

    /**
     * returns the first node.
     */
    protected LLNode<T> getFirstNode() {
        return firstNode;
    }

    /**
     * returns the last node
     */
    protected LLNode<T> getLastNode() {
        LLNode<T> nodeptr = getFirstNode();
        while (nodeptr != null) {
            nodeptr = nodeptr.getNext();
        }
        return nodeptr;
    }

    /**
     * changes the front node.
     *
     * @param node the node that will be the first node of the new linked list
     */
    protected void setFirstNode(LLNode<T> node) {
        this.firstNode = node;
    }

    /**
     * return whether the list is empty
     *
     * @return true if the list is empty
     */
    public boolean isEmpty() {
        return (getFirstNode() == null);
    }

    /**
     * add an element to the front of the linked list
     */
    public void addToFront(T element) {
        setFirstNode(new LLNode<T>(element, getFirstNode()));
    }

    /**
     * removes and returns the element at the front of the linked list
     * @return the element removed from the front of the linked list
     * @throws NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        if (isEmpty())
            throw new NoSuchElementException();
        else {
            T save = getFirstNode().getElement();
            setFirstNode(getFirstNode().getNext());
            return save;
        }
    }

    /**
     * returns the length of the linked list
     *
     * @return the number of nodes in the list
     */
    public int length() {
        int lengthSoFar = 0;
        LLNode<T> nodeptr = getFirstNode();
        while (nodeptr != null) {
            lengthSoFar++;
            nodeptr = nodeptr.getNext();
        }
        return lengthSoFar;
    }

    /**
     * add an element to the end of a list.
     *
     * @param element the element to add
     */
    public void addToEnd(T element) {
        if (isEmpty())
            addToFront(element);
        else {
            LLNode<T> nodeptr = getFirstNode();
            while (nodeptr.getNext() != null)
                nodeptr = nodeptr.getNext();
            nodeptr.setNext(new LLNode<T>(element, null));
        }
    }

    /**
     * the method required by the Iterable interface returns an iterator that loops over the data in the list
     * @return an iterator that loops over the data in the list
     */
    public LinkedListIterator<T> iterator() {
        return new LinkedListIterator<T>(getFirstNode());
    }

    /**
     * a method that returns the first element of the linked list without removing it.
     * @return the first element of the linked
     */
    public T firstElement() {
        return firstNode.getElement();
    }

    /**
     * a method that removes the last value returned by next from the linked list
     */
    public void removeFromEnd() {
        if (isEmpty())
            throw new NoSuchElementException();
        else if (length() == 1) {
            firstNode = null;
        }
        else {
            LLNode<T> nodeptr = getFirstNode();
            LLNode<T> previousptr = null;
            while (nodeptr.getNext() != null) {
                previousptr = nodeptr;
                nodeptr = nodeptr.getNext();
            }
            previousptr.setNext(null);
        }
    }
}
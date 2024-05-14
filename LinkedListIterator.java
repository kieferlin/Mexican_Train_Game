import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * this class is a class that is an iterator for the linked list.
 * the iterator loops over the data in the list from the first node to the last.
 * @author  Kiefer Lin
 */
public class LinkedListIterator<T> implements Iterator<T> {
    // a field that keeps track of which node will store the next value of the iteration
    private LLNode<T> nodeptr;

    /**
     * creates an iterator that loops over the data in the list starting at the first node
     * @param firstNode the node to start the loop
     */
    public LinkedListIterator(LLNode<T> firstNode) {
        nodeptr = firstNode;
    }
    /**
     * returns true if there is more data we can loop over and false if the loop reached the end of the list.
     * @return true if there is more data to loop over
     */
    public boolean hasNext() {
        return nodeptr != null;
    }
    /**
     * Returns the next value from the linked list in this iterator that loops over the list
     * @return the next value in this loop over the linked list data
     * @throws NoSuchElementException if next() is called after the loop reaches the end of the list
     */
    public T next() {
        if (nodeptr == null)
            throw new NoSuchElementException();
        T save = nodeptr.getElement();
        nodeptr = nodeptr.getNext();
        return save;
    }
}

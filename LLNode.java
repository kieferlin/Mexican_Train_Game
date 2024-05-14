/**
 * this class creates field, methods, constructors, and members for an LLNode
 * @author Kiefer Lin
 */
public class LLNode<T> {
    // a field that stores the element in the node
    private T element;

    // a field that stores the next node in the list
    private LLNode<T> next;

    /**
     * creates a new LLNode given an element and a next node
     * @param element the element stored in the node
     * @param next the next node in the list
     */
    public LLNode(T element, LLNode<T> next) {
        this.element = element;
        this.next = next;
    }

    /**
     * a method that returns the element in the node
     * @return the element stored in the node
     */
    public T getElement() {
        return element;
    }

    /**
     * a method that returns the next node in the list
     * @return the next node in the list
     */
    public LLNode<T> getNext() {
        return next;
    }

    /**
     * a method that sets the element stored in the node
     * @param element the new element to be stored in the node
     */
    public void setElement(T element) {
        this.element = element;
    }

    /**
     * a method that sets the node that is stored after this node
     * @param next the next node that is stored after this node
     */
    public void setNext(LLNode<T> next) {
        this.next = next;
    }
}

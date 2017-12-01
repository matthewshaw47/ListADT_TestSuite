import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The iterator implementation for LinkedList.  The constructor for this
 * class requires that a reference to a Listnode with the first data
 * item is passed in.
 * 
 * If the Listnode reference used to create the LinkedListIterator is null,
 * that implies there is no data in the LinkedList and this iterator
 * should handle that case correctly.
 * 
 * COMPLETE THIS CLASS AND HAND IN THIS FILE
 */
public class LinkedListIterator<T> implements Iterator<T> {
	
	private Listnode<T> curr;

	/**
	 * Constructs a LinkedListIterator when given the first node
	 * with data for a chain of nodes.
	 * 
	 * Tip: do not construct with a "blank" header node.
	 * 
	 * @param a reference to a List node with data. 
	 */
	public LinkedListIterator(Listnode<T> head) {
		curr = head;
	}
	
	/**
	 * Returns the next element in the iteration and then advances the
	 * iteration reference.
	 * 
	 * @return the next data item in the iteration that has not yet been returned 
	 * @throws NoSuchElementException if the iteration has no more elements 
	 */
	public T next() {
		T element = curr.getData();
		curr = curr.getNext(); 
		return element;
	}
	
	/**
	 * Returns true if there are more data items to iterate through 
	 * for this list.
	 * 
	 * @return true if there are any remaining data items to iterate through
	 */
	public boolean hasNext() {
		if ( curr != null )
			return true;
		else 
			return false;
	} 
       
    /**
     * The remove operation is not supported by this iterator
     * 
     * @throws UnsupportedOperationException if the remove operation is not 
     * supported by this iterator
     */
	public void remove() {
	  throw new UnsupportedOperationException();
	}

}

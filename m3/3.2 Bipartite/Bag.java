/**
 * importing files.
 */
import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * bag class.
 * @param <Item> Bag of items.
 */
public class Bag<Item> implements Iterable<Item> {
    /**
     * number of elements in bag.
     */
    private int size;
    /**
     * beginning of bag.
     */
    private Node first;
    /**
     * node class.
     */
    private class Node {
        /**
         * Item.
         */
        private Item item;
        /**
         * next of type Node.
         */
        private Node next;
    }

    /**
      * Create an empty stack.
      */
    public Bag() {
        first = null;
        size = 0;
    }

    /**
      * Is the BAG empty?
      * complexity is O(1).
      * @return bool.
      */
    public boolean isEmpty() {
        return first == null;
    }

    /**
      * Return the number of items in the bag.
      * complexity is O(1).
      * @return size.
      */
    public int size() {
        return size;
    }

    /**
      * Add the item to the bag.
      * complexity is O(1).
      * @param item item.
      */
    public void add(final Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        size++;
    }


    /**
      * Return an iterator that
      * iterates over the items in the bag.
      * complexity is O(N).
      * @return iterator.
      */
    public Iterator<Item> iterator()  {
        return new ListIterator();
    }
    /**
     * list iterator class.
     */
    // an iterator, doesn't implement remove()
    //since it's optional
    private class ListIterator implements Iterator<Item> {
        /**
         * current node points to first.
         */
        private Node current = first;
        /**
         * returns true/false.
         * complexity is O(1).
         * @return bool value[description]
         */
        public boolean hasNext() {
            return current != null;
        }
        /**
         * removes.
         * complexity is O(1).
         */
        public void remove() {
            throw new UnsupportedOperationException();
        }
        /**
         * next item.
         * complexity is O(1).
         * @return description
         */
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

}

/**.
TernaryST the class
@param <E> the generic type
*/
class TST<E> {
/**.
size the size of the tst object
*/
    private int size;
/**.
root node
*/
    private Node<E> root;
/**.
Node<E> the class for node
@param <E> the generic type
*/
    private class Node<E> {
/**.
char c the the character to be put ikn the node
*/
    private char c;
/**.
left mid right the nodes for the tst data structure
*/
    private Node<E> left, mid, right;
/**.
val the vlaue
*/
        private E val;
    }
/**.
@TernaryST() the public constructor
*/
    TST() { }
/**.
size() fucntion returns the size of the tst
@return returns the size
Complexity is O(1)
*/
    public int size() {
        return size;
    }
/**.
@contains() the contains fucntion to check the presence of the key
@param key the key string
@return returns the boolean
Complexity is O(1)
*/
    public boolean contains(final String key) {
        return get(key) != null;
    }
/**.
get() the function for getting the key value
@return returns the value
@param key the key for getting
Complexity is O(1)
*/
    public E get(final String key) {
        Node<E> x = get(root, key, 0);
        if (x == null) {
            return null;
        }
        return x.val;
    }
/**.
get() the function for getting the appropriate key starting at d
@return the return for the character.
@param x the node x
@param key the string
@param d the position of the key
Complexity : O(m) m is the length of the string
generalised complexity is O(m*n) m is the length of the string
n is number of strings
*/
    private Node<E> get(final Node<E> x, final String key, final int d) {
        if (x == null) {
            return null;
        }
        char c = key.charAt(d);
        if (c < x.c) {
            return get(x.left, key, d);
        } else if (c > x.c) {
            return get(x.right, key, d);
        } else if (d < key.length() - 1) {
            return get(x.mid, key, d + 1);
        } else {
            return x;
        }
    }
/**.
put() the function for inserting the key along with the value
@param key the key to be inserted
@param val the value of the key
Complexity : O(m) m is the lenth of the string
generalised complexity is O(m*n) where m is the length of the string
n is the number of strings
*/
    public void put(final String key, final E val) {
        if (!contains(key)) {
            size++;
        }
        root = put(root, key, val, 0);
    }
/**.
@param y the node of tst
@param key the key to be put
@param val the value of the key
@param d the pistioin of the charater
@return return the node after putting
Compelexity : O(m) m is the length of the string
*/
    private Node<E> put(final Node<E> y,
        final String key,
        final E val,
        final int d) {
        Node<E> x = y;
        char c = key.charAt(d);
        if (x == null) {
            x = new Node<E>();
            x.c = c;
        }
        if (c < x.c) {
           x.left  = put(x.left,  key, val, d);
        } else if (c > x.c)   {
            x.right = put(x.right, key, val, d);
        } else if (d < key.length() - 1) {
            x.mid   = put(x.mid,   key, val, d + 1);
        } else   {
            x.val   = val;
        }
        return x;
    }
/**.
@param prefix the prefix of the string passed
@return the return for the keyswithprefix
*/
    public Iterable<String> keysWithPrefix(final String prefix) {
            Queue<String> queue = new Queue<String>();
            Node<E> x = get(root, prefix, 0);
            if (x == null) {
                return queue;
            }
            if (x.val != null) {
                queue.enqueue(prefix);
            }
            collect(x.mid, new StringBuilder(prefix), queue);
            return queue;
    }
/**.
@param x the node x
@param prefix the prefix
@param queue object for queueing the prefixes
*/
    private void collect(final Node<E> x,
        final StringBuilder prefix,
        final Queue<String> queue) {
        if (x == null) {
            return;
        }
        collect(x.left,  prefix, queue);
        if (x.val != null) {
          queue.enqueue(prefix.toString() + x.c);
        }
        collect(x.mid,   prefix.append(x.c), queue);
        prefix.deleteCharAt(prefix.length() - 1);
        collect(x.right, prefix, queue);
    }

}
import java.util.Iterator;

public class SortedList<E extends Comparable<? super E>> extends List<E> {

    // Implementation of the iterator inner class
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> curr = head;

            public boolean hasNext() {
                return curr != null;
            }

            public E next() {
                E temp = curr.data;
                curr = curr.next;
                return temp;
            }
        };
    }

    // Insert method
    public void insert(E data) {
        Node<E> temp = new Node<>(data);
        head = insert(head, temp);
    }

    private Node<E> insert(Node<E> curr, Node<E> node) {
        if (curr == null || node.data.compareTo(curr.data) < 0) {
            node.next = curr;
            return node;
        } else {
            curr.next = insert(curr.next, node);
            return curr;
        }
    }

    // Remove method
    public void remove(E data) {
        head = remove(head, data);
    }

    private Node<E> remove(Node<E> curr, E data) {
        if (curr == null) {
            return null;
        }
        if (data.compareTo(curr.data) == 0) {
            return curr.next;
        }
        curr.next = remove(curr.next, data);
        return curr;
    }

    // Retrieve method
    public E retrieve(int index) {
        return retrieve(head, index);
    }

    private E retrieve(Node<E> curr, int index) {
        if (curr == null) return null;
        if (index == 0) return curr.data;
        return retrieve(curr.next, index - 1);
    }

    // Search method
    public boolean search(E data) {
        return search(head, data);
    }

    private boolean search(Node<E> curr, E data) {
        if (curr == null) return false;
        if (data.compareTo(curr.data) == 0) return true;
        return search(curr.next, data);
    }

    // New method: Size
    public int size() {
        return size(head);
    }

    private int size(Node<E> curr) {
        if (curr == null) return 0;
        return 1 + size(curr.next);
    }

    // New method: Clear
    public void clear() {
        head = null;
    }
}

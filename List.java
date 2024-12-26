public abstract class List<E> implements Iterable<E> {

    //implements the node class 
    protected class Node<T> {

        protected Node(T data) {
            this.data = data;
        }

        protected T data;
        protected Node<T> next;
    }
   //abstract methods that need to be made recursive 
    public abstract void insert(E data);
    public abstract void remove(E data);
    public abstract E retrieve(int index);
    public abstract boolean search(E data);

    protected Node<E> head;
}

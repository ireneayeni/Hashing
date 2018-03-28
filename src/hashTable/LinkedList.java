package hashTable;

/**
 * LinkList implementation with generics
 *
 * @param <T>
 * @author oyinkansola
 */
public class LinkedList<T> {
    private LinkedNode<T> first;
    private LinkedNode<T> last;
    private int count;

    /**
     * Internal linked node implementation
     *
     * @param <T>
     */
    private class LinkedNode<T> {
        private T data;
        private LinkedNode<T> next;

        public LinkedNode() {
            this.data = null;
            this.next = null;
        }

        public LinkedNode(T obj) {
            this.data = obj;
            this.next = null;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public LinkedNode<T> getNext() {
            return next;
        }

        public void setNext(LinkedNode<T> next) {
            this.next = next;
        }

    }

    public LinkedList() {
        LinkedNode<T> newLiked = new LinkedNode<T>();
        this.first = newLiked;
        this.last = this.first;
    }

    public T get(int index) {//getting the next connected node.
        LinkedNode<T> item = this.first;
        while (index > 0 && item.getNext() != null) {
            item = item.getNext();
            index--;
        }
        return index == 0 && item.data != null ? item.data : null;
    }

    /**
     * Add values to the list
     *
     * @param data
     */
    public void add(T data) {//pulling in  the data into a new node
        LinkedNode<T> newData = new LinkedNode<T>(data);
        if (this.first.getData() == null) {
            this.first = newData;
            this.last = this.first;
        } else {
            this.last.setNext(newData);
            this.last = newData;
        }
        count++;
    }

    /**
     * Remove values from the list
     *
     * @param data
     */
    public void remove(T data) {//handles when there is just one node on tye list
        LinkedNode<T> current = first;
        if (this.first.getData().equals(data)) {
            if (this.first.getNext() == null) {
                LinkedNode<T> newNode = new LinkedNode<T>();
                this.first = newNode;
                this.last = this.first;
            } else {
                this.first.setData(null);
                this.first = this.first.getNext();
            }
        } else {
            boolean wasDeleted = false;//handles deletion fron the list
            while (!wasDeleted) {
                LinkedNode<T> currentNext = current.getNext();
                if (currentNext.getData().equals(data)) {
                    currentNext.setData(null);
                    current.setNext(currentNext.getNext());
                    currentNext = null;
                    wasDeleted = true;
                    count--;
                } else {
                    current = current.getNext();
                }
            }
        }
    }

    public void remove(int index) {//deletes based on index and then deletes the value.
        this.remove(get(index));
    }

    public int size() {
        return count;
    }
}
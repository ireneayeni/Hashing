package hashTable;
import java.util.Currency;

/**
 * LinkList implementation with generics
 *
 * @author malalanayake
 *
 * @param <T>
 */
public class LinkedList<T> {
    private LinkedNode<T> first;
    private LinkedNode<T> last;
    private int count;

    /**
     * Internal linked node implementation
     *
     * @author malalanayake
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

    /**
     * Add values to the list
     *
     * @param data
     */
    public void add(T data) {
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
    public void remove(T data) {
        LinkedNode<T> current = first;
        if (this.first.getData().equals(data)) {
            if (this.first.getNext() == null) {
                LinkedNode<T> newNode = new LinkedNode<T>();
                this.first.setData(null);
                this.first = newNode;
                this.last = this.first;
            } else {
                this.first.setData(null);
                this.first = this.first.getNext();
            }
        } else {
            boolean wasDeleted = false;
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

    public void print() {
        boolean allPrinted = false;
        LinkedNode<T> crr = first;
        System.out.print("[");
        while (!allPrinted) {
            if (crr.getData() != null) {
                if (crr.getNext() != null) {
                    System.out.print(crr.getData().toString() + ",");
                    LinkedNode<T> crrNext = crr.getNext();
                    crr = crrNext;
                } else {
                    System.out.print(crr.getData().toString() + "]");
                    allPrinted = true;
                }
            } else {
                allPrinted = true;
            }
        }
        System.out.println();
    }

    public int getCount() {
        return count;
    }

    public static void main(String[] args) {
        LinkedListWithGenerics<String> linkedLst = new LinkedListWithGenerics<String>();
        linkedLst.add("Test");
        linkedLst.add("Free");
        linkedLst.add("Yes");
        linkedLst.add("Me");

        linkedLst.print();
        System.out.println(linkedLst.getCount());

        linkedLst.remove("Me");

        linkedLst.print();

        System.out.println(linkedLst.getCount());

    }
}
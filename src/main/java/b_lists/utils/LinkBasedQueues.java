package b_lists.utils;

public class LinkBasedQueues<E> {
    private Node<E> first;
    private Node<E> last;
    private int numElement;

    private static class Node<E> {
        private E data;
        private Node<E> next;

        public Node(E data) {
            this.data = data;
            this.next = null;
        }
    }

    public boolean isEmpty() {
        return first == null;
    }


    public LinkBasedQueues() {
        this.first = null;
        this.last = null;
        this.numElement = 0;
    }

    public void enqueue(E element) {
        Node<E> newNode = new Node(element);
        if (isEmpty()) {
            first = newNode;
            last = newNode;
        } else {
            last.next = newNode;
            last = newNode;
        }
        numElement++;
    }

    public E dequeue() {
        if (isEmpty()) {
            throw new QueueEmptyException("Queue is empty");
        } else {
            E data = first.data;
            first = first.next;
            numElement--;
            if (numElement == 0) {
                last = null;
            }
            return data;
        }
    }

    public int size(){
        return numElement;
    }

    public E peek() {
        if (isEmpty()) {
            throw new QueueEmptyException("Queue is empty");
        } else {

            return first.data;
        }
    }
}

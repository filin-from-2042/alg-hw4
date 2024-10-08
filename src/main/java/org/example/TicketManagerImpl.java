package org.example;

public class TicketManagerImpl implements TicketManager {

    private final Ticket[] heap;
    private int size = 0;

    public TicketManagerImpl(int capacity) {
        this.heap = new Ticket[capacity];
    }

    @Override
    public void add(Ticket ticket) {
        if (ticket == null) {
            throw new IllegalArgumentException("ticket is null");
        }
        heap[size] = ticket;
        checkRules(size++);
    }

    @Override
    public Ticket next() {
        if (size == 0) {
            return null;
        }
        Ticket res = heap[0];
        int newSize = --size;
        heap[0] = heap[newSize];
        heap[newSize] = null;
        heapify();
        return res;
    }

    private void checkRules(int currentIndex) {
        int parentIndex = getParent(currentIndex);
        Ticket value = heap[currentIndex];
        while (currentIndex > 0 && value.compareTo(heap[parentIndex]) > 0) {
            heap[currentIndex] = heap[parentIndex];
            currentIndex = parentIndex;
            parentIndex = getParent(currentIndex);
        }
        heap[currentIndex] = value;
    }

    private int getParent(int i) {
        return (i - 1) / 2;
    }

    private void heapify() {
        int currentIndex = 0;
        int leftIndex;
        int rightIndex;
        int largestIndex;
        while (heap[currentIndex] != null) {
            largestIndex = currentIndex;
            leftIndex = 2 * currentIndex + 1;
            rightIndex = 2 * currentIndex + 2;
            if (leftIndex < heap.length && isSecondBigger(largestIndex, leftIndex)) {
                largestIndex = leftIndex;
            }
            if (rightIndex < heap.length && isSecondBigger(largestIndex, rightIndex)) {
                largestIndex = rightIndex;
            }
            if (largestIndex == currentIndex) {
                break;
            }
            Ticket tmp = heap[currentIndex];
            heap[currentIndex] = heap[largestIndex];
            heap[largestIndex] = tmp;
            currentIndex = largestIndex;
        }
    }

    private boolean isSecondBigger(int first, int second) {
        return heap[second] != null && heap[first].compareTo(heap[second]) < 0;
    }
}

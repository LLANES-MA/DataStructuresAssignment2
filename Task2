import java.util.NoSuchElementException;

public class Task2 {

    public class Key {
        private int jobID;
        private int processTime;
        private int priority;

        public Key(int jobID, int processTime, int priority) {
            this.jobID = jobID;
            this.processTime = processTime;
            this.priority = priority;
        }

        public int getJobID() {
            return jobID;
        }

        public int getProcessTime() {
            return processTime;
        }
        public int getPriority() {
            return priority;
        }
    }

    private Key[] heap;
    private int size;
    private double processTime;
    private double completionTimeSum;

    public Task2 (int capacity) {
        heap = new Key[capacity];
        size = 0;
        processTime = 0;
        completionTimeSum = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void insert(int jobID, int processTime, int priority) {
        Key key = new Key(jobID, processTime, priority);
        // insert key into last element of the array
        heap[size] = key;
        // then the tree sorts the element to where it needs to be
        swim(size++);
    }

    private boolean less(int i, int j) {
        if (heap[i] == null || heap[j] == null) {
            return false;   // Return false if any element is null (i was having issues with null values)
        }
        if (heap[i].getPriority() != heap[j].getPriority()) {
            return heap[i].getPriority() < heap[j].getPriority();
        }
        return heap[i].getProcessTime() < heap[j].getProcessTime();
    }

    private void exch(int i, int j) {
        Key key = heap[i];
        heap[i] = heap[j];
        heap[j] = key;
    }

    private void swim(int k) {
        while (k > 0 && less(k, (k - 1) / 2)) {
            exch(k, (k - 1) / 2);
            k = (k - 1) / 2;
        }
    }

    private void sink(int k) {
        while (2 * k + 1 <= size) {             // While the left child exists
            int j = 2 * k + 1;                  // Left child
            if (j + 1 <= size && less(j + 1, j)) { // Choose the smaller of the two children
                j++;                             // Right child is smaller
            }
            if (!less(j, k)) {                   // If the parent is smaller than both children, stop
                break;
            }
            exch(k, j);                          // Swap the parent with the smaller child
            k = j;
        }
    }

    public Key delMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap underflow");  // Add error handling for empty heap
        }
        Key min = heap[0];
        exch(0, size - 1);
        heap[size - 1] = null;
        size--;
        sink(0);
        return min;
    }

    public void sort() {
        Task2 sortedHeap = new Task2(size);
        while (!isEmpty()) {
            Key min = delMin();
            sortedHeap.insert(min.getJobID(), min.getProcessTime(), min.getPriority());
        }
        sortedHeap.arrPrint();
    }

    public void arrPrint() {
        System.out.print("Execution order: [");
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i].getJobID() + (i < size - 1 ? ", " : ""));
            processTime += heap[i].getProcessTime();  // Update total elapsed time
            completionTimeSum += processTime;
        }
        System.out.print("]\n");

        double averageCompletionTime = completionTimeSum / size;
        System.out.printf("Average completion time: %.2f\n", averageCompletionTime);
    }
}

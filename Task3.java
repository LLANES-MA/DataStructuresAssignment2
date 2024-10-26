import java.util.NoSuchElementException;

public class Task3 {

    public class Key {
        private int jobID;
        private int processTime;
        private int priority;
        private int arrivalTime;

        public Key(int jobID, int processTime, int arrivalTime) {
            this.jobID = jobID;
            this.processTime = processTime;
            this.priority = 0;
            this.arrivalTime = arrivalTime;
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
        public int getArrivalTime() {
            return arrivalTime;
        }
        public void setArrivalTime(int arrivalTime) {
            this.arrivalTime = arrivalTime;
        }
    }

    private Key[] heap;
    private int size;
    private double processTime;
    private double completionTimeSum;
    private int currentProcessTime;

    public Task3 (int capacity) {
        heap = new Key[capacity];
        size = 0;
        processTime = 0;
        completionTimeSum = 0;
        currentProcessTime = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void insert(int jobID, int processTime, int arrivalTime) {
        Key key = new Key(jobID, processTime, arrivalTime);
        // Calculate priority: lower processTime and earlier arrival get higher priority
        key.priority = calculatePriority(processTime, arrivalTime);
        // insert key into last element of the array
        heap[size] = key;
        // then the tree sorts the element to where it needs to be
        swim(size++);
    }

    private int calculatePriority(int processTime, int arrivalTime) {
        // jobs with shorter process time and earlier arrival time get higher priority
        return processTime + arrivalTime;
    }

    private boolean less(int i, int j) {
        if (heap[i] == null || heap[j] == null) {
            return false;   // Return false if any element is null (I was having issues with null values)
        }
        if (heap[i].getPriority() != heap[j].getPriority()) {
            return heap[i].getPriority() < heap[j].getPriority();
        }
        return heap[i].getArrivalTime() < heap[j].getArrivalTime();
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
        while (2 * k + 1 <= size) {           
            int j = 2 * k + 1;           
            if (j + 1 <= size && less(j + 1, j)) {
                j++;                        
            }
            if (!less(j, k)) {             
                break;
            }
            exch(k, j);                    
            k = j;
        }
    }

    public Key delMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap underflow");  
        }
        Key min = heap[0];
        exch(0, size - 1);
        heap[size - 1] = null;
        size--;
        sink(0);
        return min;
    }

    public void simulate() {
        currentProcessTime = 0;
        Task3 scheduler = new Task3(heap.length);
        boolean[] jobInserted = new boolean[size]; // Track jobs inserted into scheduler
        int executedJobs = 0;
        System.out.print("Execution order: [");

        while (!allJobsProcessed(jobInserted) || !scheduler.isEmpty()) {
            // Insert jobs that have arrived up to currentProcessTime
            for (int i = 0; i < size; i++) {
                Key job = heap[i];
                if (job != null && !jobInserted[i] && job.getArrivalTime() <= currentProcessTime) {
                    scheduler.insert(job.getJobID(), job.getProcessTime(), job.getArrivalTime());
                    jobInserted[i] = true;
                }
            }

            // Process the job if the scheduler has jobs
            if (!scheduler.isEmpty()) {
                Key job = scheduler.delMin();
                executedJobs++;
                System.out.print(job.getJobID());
                if (executedJobs == size) {
                    System.out.print("");
                } else {
                    System.out.print(", ");
                }
                currentProcessTime += job.getProcessTime(); // Move time forward by the job's processing time
                completionTimeSum += currentProcessTime;
            } else {
                // No jobs to process; increment time to keep checking for arrivals
                currentProcessTime++;
            }
        }
        System.out.println("]");
        double averageCompletionTime = completionTimeSum / size;
        System.out.printf("Average completion time: %.2f\n", averageCompletionTime);
    }

    private boolean allJobsProcessed(boolean[] jobInserted) {
        for (boolean inserted : jobInserted) {
            if (!inserted) {  // If any job is not inserted, return false
                return false;
            }
        }
        return true;
    }
}

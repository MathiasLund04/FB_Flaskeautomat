import Model.Bottle;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {
    private final LinkedList<Bottle> queue = new LinkedList<>();
    private final int capacity;

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public Buffer(int capacity) {
        this.capacity = capacity;
    }

    public void produce(Bottle bottle) throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() == capacity) {
                System.out.println("Buffer full. Waiting for consumer...");
                notFull.await();
            }
            queue.add(bottle);
            notEmpty.signal();

        } finally {
            lock.unlock();
        }
    }

    public Bottle consume(int minItems) throws InterruptedException {
        lock.lock();

        try {
            while (queue.size() < minItems) {
                notEmpty.await();
            }
            Bottle bottle = queue.removeFirst();
            if(queue.size() < 5) {
                notFull.signal();
            }
            return bottle;
        } finally {
            lock.unlock();
        }
        }


}

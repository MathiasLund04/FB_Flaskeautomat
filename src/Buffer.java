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
                notFull.await();
            }
            queue.add(bottle);
            notEmpty.signal();

        } finally {
            lock.unlock();
        }
    }

    public Bottle consume() throws InterruptedException {
        lock.lock();

        try {
            while (true){
            if (queue.size() < 3) {
                notEmpty.await();
            }
            Bottle bottle = queue.removeFirst();
            notFull.signal();
            return bottle;
            }
        } finally {
            lock.unlock();
        }
        }


}

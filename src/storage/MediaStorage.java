package storage;


import observer.Observer;
import observer.Subject;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MediaStorage implements Subject {

    private final Lock lock = new ReentrantLock();
    private final Set<Observer> observerList = ConcurrentHashMap.newKeySet();
    private final long diskSize;
    private long availableMediaStorageInMB;

    public MediaStorage(long diskSize) {
        this.diskSize = diskSize;
        availableMediaStorageInMB = this.diskSize;
    }

    public void addMediaInStorage(long mediaSize) throws NoStorageException {
        this.lock.lock();
        try {
            // check sufficient Storage
            if (availableMediaStorageInMB < mediaSize) {
                throw new NoStorageException();
            } else {
                availableMediaStorageInMB -= mediaSize;
                notifyObserver();
            }
        } finally {
            this.lock.unlock(); // end the critical area
        }

    }

    public void deletedMediaFromStorage(long mediaSize) {
        this.lock.lock();
        try {
            availableMediaStorageInMB += mediaSize;
            notifyObserver();
        } finally {
            this.lock.unlock();
        }
    }

    public void clear() {
        this.lock.lock();
        try {
            availableMediaStorageInMB = diskSize;
            notifyObserver();
        } finally {
            this.lock.unlock();
        }
    }

    public long getDiskSize() {
        return diskSize;
    }

    public long getAvailableMediaStorageInMB() {
        return availableMediaStorageInMB;
    }

    @Override
    public void registerObserver(Observer observer) {
        if(observer == null) {
            throw new IllegalArgumentException("Observer is null");
        }
        this.observerList.add(observer);
    }

    @Override
    public void unregisterObserver(Observer observer) {
        if(observer == null) {
            throw new IllegalArgumentException("Observer is null");
        }
            this.observerList.remove(observer);
    }

    @Override
    public void notifyObserver() {
        for (Observer observer : observerList) {
            observer.updateObserver();
        }
    }


}

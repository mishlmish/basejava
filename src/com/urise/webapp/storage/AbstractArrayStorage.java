package com.urise.webapp.storage;

import com.urise.webapp.exeption.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    private int size;

    protected abstract void insertResume(Object index, Resume r);

    protected abstract void deleteResume (Object searchKey);

    @Override
    protected void doSave(Resume r, Object searchKey) {
        saveOverflow(r.getUuid());
        insertResume(searchKey, r);
        size++;
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        storage[(int) searchKey] = r;
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return storage[(int) searchKey];
    }

    @Override
    protected void doDelete(Object searchKey) {
        deleteResume (searchKey);
        storage[size - 1] = null;
        size--;
    }

    @Override
    public List<Resume> doCopyAll() {

        return Arrays.asList(Arrays.copyOf(storage, size ));
    }

    @Override
    public void clear() {
        Arrays.fill(storage, null);
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    private void saveOverflow(String uuid) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Array storage overflowed, can not save uuid " + uuid, uuid);
        }
    }
}



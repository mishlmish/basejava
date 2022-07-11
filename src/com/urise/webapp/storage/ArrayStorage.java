package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {
    @Override
    protected void saveToIndex(Object index, Resume r) {
        storage[size()] = r;
    }

    @Override
    protected void fillDeletedElement(Object searchKey) {
        storage[(int) searchKey] = storage[size() - 1];
    }

    @Override
    protected boolean isExist(String uuid) {
        return (int) getSearchKey(uuid) >= 0;
    }

    @Override
    protected Object getSearchKey(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}

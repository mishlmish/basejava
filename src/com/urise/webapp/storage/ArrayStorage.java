package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {
    @Override
    protected void insertResume(Object index, Resume r) {
        storage[size()] = r;
    }

    @Override
    protected void deleteResume (Object searchKey) {
        storage[(int) searchKey] = storage[size() - 1];
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return (int) searchKey >= 0;
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

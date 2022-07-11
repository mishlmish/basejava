package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void saveToIndex(Object searchKey, Resume r) {
        int index = -(int) searchKey - 1;
        int length = size() - index;
        System.arraycopy(storage, index, storage, index + 1, length);
        storage[index] = r;
    }

    @Override
    protected void fillDeletedElement(Object searchKey) {
        int emptyIndex = (int) searchKey;
        System.arraycopy(storage, emptyIndex + 1, storage, emptyIndex, size() - emptyIndex);
    }

    @Override
    protected boolean isExist(String uuid) {
        return (int) getSearchKey(uuid) >= 0;
    }

    @Override
    protected Object getSearchKey(String uuid) {
        Resume keyResume = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size(), keyResume);
    }
}

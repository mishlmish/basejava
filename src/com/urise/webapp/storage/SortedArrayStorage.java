package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    int insertionPoint(int index) {
        int insertionPoint = (-index) - 1;

        System.arraycopy(storage, insertionPoint, storage, insertionPoint + 1,
                size - insertionPoint);
        return insertionPoint;
    }

    @Override
    void deleteElement(int index) {
        if (index != size - 1) {
            System.arraycopy(storage, index + 1, storage, index, size - index);
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}

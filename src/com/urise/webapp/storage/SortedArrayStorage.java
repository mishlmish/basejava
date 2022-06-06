package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage{

    @Override
    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if(index < 0) {
            System.out.println("\"Резюме с uuid " + r.getUuid() + " не найдено. Обновление не произведено");
        } else {
            storage[index] = r;
        }
    }

    @Override
    public void save(Resume r) {
        if (size >= STORAGE_LIMIT) {
            System.out.println("Резюме не может быть введено из-за переполнения базы данных");
        } else {
            int insertionPoint = getIndex(r.getUuid());
            if (insertionPoint >= 0) {
                System.out.println("Резюме c uuid " + r.getUuid() + " уже существует");
            } else {
                insertionPoint = (-insertionPoint) - 1;
                if (size() < STORAGE_LIMIT - 1) {
                    System.arraycopy(storage, insertionPoint , storage, insertionPoint + 1,
                            size - insertionPoint + 1);
                }
                storage[insertionPoint] = r;
                size++;
            }
        }
    }

    @Override
    public void delete(String uuid) {
        int deletionPoint = getIndex(uuid);

        if(deletionPoint < 0) {
            System.out.println("Резюме с uuid " + uuid + " не найдено. Из базы данных ничего не удалено");
        } else  {
            if(deletionPoint != size - 1) {
                System.arraycopy(storage, deletionPoint + 1, storage, deletionPoint,
                        size - deletionPoint);
            }
            storage[size - 1] = null;
            size--;
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}

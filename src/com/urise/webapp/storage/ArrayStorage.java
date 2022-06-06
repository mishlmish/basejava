package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage  extends AbstractArrayStorage {

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index == -1) {
            System.out.println("\"Резюме с uuid " + r.getUuid() + " не найдено. Обновление не произведено");
        } else {
            storage[index] = r;
        }
    }

    public void save(Resume r) {
        if (size >= STORAGE_LIMIT) {
            System.out.println("Резюме не может быть введено из-за переполнения базы данных");
        } else if (getIndex(r.getUuid()) != -1) {
            System.out.println("Резюме c uuid " + r.getUuid() + " уже существует");
        } else {
                storage[size] = r;
                size++;
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Резюме с uuid " + uuid + " не найдено. Из базы данных ничего не удалено");
        } else {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}

package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    protected static final int STORAGE_LIMIT = 10000;
    private final Resume[] storage = new Resume[STORAGE_LIMIT];
    private int size;

    public void clear() {
        Arrays.fill(storage, 0, size - 1, null);
        size = 0;
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index != -1) {
            storage[index] = r;
        } else {
            System.out.println("\"Резюме с uuid " + r.getUuid() + " не найдено. Обновление не произведено");
        }
    }

    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (size == STORAGE_LIMIT) {
            System.out.println("Резюме не может быть введено из-за переполнения базы данных");
        } else if (index != -1) {
            System.out.println("Резюме c uuid " + r.getUuid() + " уже существует");
        } else {
                storage[size++] = r;
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
            return storage[index];
        }
        System.out.println("Резюме с uuid " + uuid + " не существует");
        return null;
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
            storage[index] = storage[size - 1];
            storage[--size] = null;
        } else {
            System.out.println("Резюме с uuid " + uuid + " не найдено. Из базы данных ничего не удалено");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}

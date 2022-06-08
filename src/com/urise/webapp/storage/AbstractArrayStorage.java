package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        if (size >= STORAGE_LIMIT) {
            System.out.println("Резюме не может быть введено из-за переполнения базы данных");
        } else {
            int index = getIndex(r.getUuid());
            if (index >= 0) {
                System.out.println("Резюме c uuid " + r.getUuid() + " уже существует");
            } else {
                storage[insertionPoint(index)] = r;
                size++;
            }
        }
    }

    abstract int insertionPoint(int index);

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index < 0) {
            System.out.println("\"Резюме с uuid " + r.getUuid() + " не найдено. Обновление не произведено");
        } else {
            storage[index] = r;
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            return null;
        }
//        System.out.println("Get check storage uuid " + uuid + " storage[index] " + storage[index]);
        return storage[index];
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);

        if (index < 0) {
            System.out.println("Резюме с uuid " + uuid + " не найдено. Из базы данных ничего не удалено");
        } else {
            deleteElement(index);

            storage[size - 1] = null;
            size--;
        }
    }

    abstract void deleteElement(int index);

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected abstract int getIndex(String uuid);
}



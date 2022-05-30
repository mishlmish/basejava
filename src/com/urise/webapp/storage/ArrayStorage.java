package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size;

    public void clear() {
        Arrays.fill(storage, 0, size - 1, null);
        size = 0;
    }

    public void update(Resume r) {
        if (exist(r.getUuid())) {
            Scanner console = new Scanner(System.in);
            System.out.println("Введите новый uuid вместо " + r.getUuid());
            r.setUuid(console.nextLine());
            System.out.println("Новый uuid:" + r.getUuid());
        } else {
            System.out.println("\"Резюме с uuid " + r.getUuid() + " не найдено. Обновление не произведено");
        }
    }

    public void save(Resume r) {
        if (size == storage.length) {
            System.out.println("Резюме с uuid" + r.getUuid() + " не может быть добавлено " +
                    "из-за переполнения базы данных");
        } else if (!exist(r.getUuid())) {
            storage[size++] = r;
        } else {
            System.out.println("Резюме uuid " + r.getUuid() + " уже существует, сохраните новое резюме под другим " +
                    "uuid");
        }
    }

    public Resume get(String uuid) {
        if (exist(uuid)) {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(uuid)) {
                    return storage[i];
                }
            }
        }
        System.out.println("Резюме с uuid " + uuid + " не существует");
        return null;
    }

    public void delete(String uuid) {
        if (exist(uuid)) {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(uuid)) {
                    if (i != (size - 1)) {
                        System.arraycopy(storage, i + 1, storage, i, size() - 1 - i);
                    }
                    storage[--size] = null;
                }
            }
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

    private boolean exist(String uuid) {

        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return true;
            }
        }
        return false;
    }
}

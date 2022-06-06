package com.urise.webapp;

import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.AbstractArrayStorage;
import com.urise.webapp.storage.ArrayStorage;
import com.urise.webapp.storage.SortedArrayStorage;

/**
 * Test for your com.urise.webapp.storage.ArrayStorage implementation
 */
public class MainTestArrayStorage {
    private static final AbstractArrayStorage  ARRAY_STORAGE = new SortedArrayStorage();

    private static final AbstractArrayStorage  ARRAY_STORAGE1 = new SortedArrayStorage();
    private static final AbstractArrayStorage  ARRAY_STORAGE2 = new ArrayStorage();

    public static void main(String[] args) {
        final Resume r3 = new Resume();
        r3.setUuid("uuid3");
        final Resume r5 = new Resume();
        r5.setUuid("uuid5");
        final Resume r1 = new Resume();
        r1.setUuid("uuid1");
        final Resume r2 = new Resume();
        r2.setUuid("uuid2");
//        Resume r10001 = new Resume();

        ARRAY_STORAGE.save(r3);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r1);
//        ARRAY_STORAGE.save(r10001);
//
   //     System.out.println("Index of r2 " + Arrays.binarySearch(ARRAY_STORAGE.storage, 0, ARRAY_STORAGE.size(), r5));

        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));
        printAll();

        ARRAY_STORAGE.update(r3);
        printAll();
        ARRAY_STORAGE.update(r5);
        printAll();

        ARRAY_STORAGE.delete(r1.getUuid());
        ARRAY_STORAGE.delete(r5.getUuid());
        printAll();

        Resume[] testR = new Resume[10005];
        for (int i = 0; i < 10005; i++) {
            testR[i] = new Resume();
            testR[i].setUuid("uuid" + i);
            ARRAY_STORAGE.save(testR[i]);
        }
//
//        ARRAY_STORAGE.save(r10001);
       printAll();
        System.out.println("Size: " + ARRAY_STORAGE.size());
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All ");
        for (Resume r : ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }
    }
}

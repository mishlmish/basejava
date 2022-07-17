package com.urise.webapp;

import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.AbstractArrayStorage;
import com.urise.webapp.storage.ArrayStorage;
import com.urise.webapp.storage.ListStorage;
import com.urise.webapp.storage.SortedArrayStorage;

public class MainTestArrayStorage {
    private static final AbstractArrayStorage ARRAY_STORAGE = new SortedArrayStorage();
    private static final AbstractArrayStorage ARRAY_STORAGE1 = new SortedArrayStorage();
    private static final AbstractArrayStorage ARRAY_STORAGE2 = new ArrayStorage();

    public static void main(String[] args) {

         Resume r1= new Resume("u1");
         Resume r2 = new Resume("u2");
         Resume r3 = new Resume("u3");
         Resume r4 = new Resume("u4");
         Resume r5 = new Resume("u5");
//        Resume r10001 = new Resume();

        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);
//        ARRAY_STORAGE.save(r10001);
//
        //     System.out.println("Index of r2 " + Arrays.binarySearch(ARRAY_STORAGE.storage, 0, ARRAY_STORAGE.size()
        //     , r5));

        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

//        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));
//        printAll();

        ARRAY_STORAGE.update(r3);
        printAll();
//        ARRAY_STORAGE.update(r5);
        printAll();

        ARRAY_STORAGE.delete(r1.getUuid());
//        ARRAY_STORAGE.delete(r5.getUuid());
        printAll();

        for (int i = 0; i < 9997; i++) {
            ARRAY_STORAGE.save(new Resume("newName"));
        }
        ARRAY_STORAGE.save(r4);
//        ARRAY_STORAGE.save(r10001);
        printAll();

//        ARRAY_STORAGE.get("uuid9998");

        System.out.println("Size: " + ARRAY_STORAGE.size());
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    public static ListStorage list = new ListStorage();

    static void printAll() {
        System.out.println("\nGet All ");
        for (Resume r : ARRAY_STORAGE.getAllSorted()) {
            System.out.println(r);
        }
    }
}

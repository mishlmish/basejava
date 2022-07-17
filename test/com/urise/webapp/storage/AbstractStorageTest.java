package com.urise.webapp.storage;

import com.urise.webapp.exeption.ExistStorageException;
import com.urise.webapp.exeption.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public abstract class AbstractStorageTest {

    protected Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final String FULL_NAME_1 = "fullName1";
    private static final String FULL_NAME_2 = "fullName2";
    private static final String FULL_NAME_3 = "fullName3";
    private static final String FULL_NAME_4 = "fullName4";
    private static final String FULL_NAME_5 = "fullName5";
    private static final String FULL_NAME_6 = "fullName6";

    private static final String UUID_NOT_EXIST = "dummy";

     static final Resume RESUME_1 = new Resume(UUID_1, FULL_NAME_1);
     static final Resume RESUME_2 = new Resume(UUID_2, FULL_NAME_2);
     static final Resume RESUME_3 = new Resume(UUID_3, FULL_NAME_3);
     static final Resume RESUME_4 = new Resume(UUID_4, FULL_NAME_4);
     static final Resume RESUME_5 = new Resume(UUID_4, FULL_NAME_5);


    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_2);
        storage.save(RESUME_3);
        storage.save(RESUME_1);
    }

    @Test
    public void size() throws Exception {
        assertSize(3);
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void save() throws Exception {
//        System.out.println("check to add Resume with generated uuid");
        storage.save(new Resume(FULL_NAME_6));
        assertSize(4);
//        System.out.println("check to add Resume with non generated uuid");
        storage.save(RESUME_4);
        assertSize(5);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExistException() throws Exception {
        storage.save(new Resume(UUID_1, "new name"));
    }

    @Test
    public void update() throws Exception {
        Resume newResume = new Resume(UUID_1, "New Name");
        storage.update(newResume);
        assertGet(newResume);
        Assert.assertSame(newResume, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.update(new Resume(UUID_NOT_EXIST));
    }

    @Test
    public void get() throws Exception {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get(UUID_NOT_EXIST);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        storage.delete(UUID_1);
        assertSize(2);
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete(UUID_NOT_EXIST);
    }

    @Test
    public void getAllSorted() throws Exception {
        List<Resume> list = storage.getAllSorted();
        assertSize(3);
        String previous = "";
        for (int i = 0; i < storage.size() - 1; i++) {
            Assert.assertTrue((previous.compareTo(list.get(i).getFullName()) <= 0));
            previous = list.get(i).getFullName();
        }
    }

    private void assertSize(int size) {
        Assert.assertEquals(size, storage.size());
    }

    private void assertGet(Resume r) {
        Assert.assertEquals(r, storage.get(r.getUuid()));
    }
}

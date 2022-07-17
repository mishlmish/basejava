package com.urise.webapp.storage;

import com.urise.webapp.exeption.ExistStorageException;
import com.urise.webapp.exeption.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Collections;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    protected abstract void doSave(Resume r, Object searchKey);

    protected abstract void doUpdate(Resume r, Object searchKey);

    protected abstract Resume doGet(Object searchKey);

    protected abstract void doDelete(Object searchKey);

    protected abstract boolean isExist(Object searchKey);

    protected abstract List<Resume> doCopyAll();

    protected abstract Object getSearchKey(String uuid);

    public void save(Resume newResume) {
        Object toSearchKey = getNotExistSearchKey(newResume.getUuid());
        doSave(newResume, toSearchKey);
    }

    public void update(Resume oldResume) {
        Object toSearchKey = getExistSearchKey(oldResume.getUuid());
        doUpdate(oldResume, toSearchKey);
    }

    public Resume get(String uuid) {
        Object fromSearchKey = getExistSearchKey(uuid);
        return doGet(fromSearchKey);
    }

    public void delete(String uuid) {
        Object fromSearchKey = getExistSearchKey(uuid);
        doDelete(fromSearchKey);
    }

    public List<Resume> getAllSorted() {
        List<Resume> list = doCopyAll();
        Collections.sort(list, (r1, r2) -> r1.getFullName().compareTo(r2.getFullName()) != 0 ?
                r1.getFullName().compareTo(r2.getFullName()) : r1.getUuid().compareTo(r2.getUuid()));
        return list;
    }

    private Object getExistSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return getSearchKey(uuid);
    }

    private Object getNotExistSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return getSearchKey(uuid);
    }
}

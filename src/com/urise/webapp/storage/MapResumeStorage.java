package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage {
    public static final Map<String, Resume> map = new HashMap<>();

    @Override
    protected void doSave(Resume r, Object searchKey) {
        map.put(r.getUuid(), r);
    }

    @Override
    protected void doUpdate(Resume newResume, Object searchKey) {
        Resume oldResume = (Resume) searchKey;
        map.replace(oldResume.getUuid(), newResume);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return (Resume) searchKey;
    }

    @Override
    protected void doDelete(Object searchKey) {
        map.remove(((Resume) searchKey).getUuid());
    }

    @Override
    protected boolean isExist(Object searchKey) {
        if (searchKey == null) {
            return false;
        } else {
            Resume r = (Resume) searchKey;
            return map.containsKey(r.getUuid());
        }
    }

    @Override
    protected List<Resume> doCopyAll() {
        return new ArrayList<>( map.values());
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return map.get(uuid);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public int size() {
        return map.size();
    }
}

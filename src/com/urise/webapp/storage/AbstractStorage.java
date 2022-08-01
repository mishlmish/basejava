package com.urise.webapp.storage;

import com.urise.webapp.exeption.ExistStorageException;
import com.urise.webapp.exeption.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {
//    protected  final Logger log = Logger.getLogger(getClass().getName());
    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    protected abstract void doSave(Resume r, SK searchKey);

    protected abstract void doUpdate(Resume r, SK searchKey);

    protected abstract Resume doGet(SK searchKey);

    protected abstract void doDelete(SK searchKey);

    protected abstract boolean isExist(SK searchKey);

    protected abstract List<Resume> doCopyAll();

    protected abstract SK getSearchKey(String uuid);

    public void save(Resume newResume) {
        LOG.info("Save " + newResume);
        SK toSearchKey = getNotExistSearchKey(newResume.getUuid());
        doSave(newResume, toSearchKey);
    }

    public void update(Resume modifiedResume) {
        LOG.info("Update " + modifiedResume);
        SK toSearchKey = getExistSearchKey(modifiedResume.getUuid());
        doUpdate(modifiedResume, toSearchKey);
    }

    public Resume get(String uuid) {
        LOG.info("Get " + uuid);
        SK fromSearchKey = getExistSearchKey(uuid);
        return doGet(fromSearchKey);
    }

    public void delete(String uuid) {
        LOG.info("Delete " + uuid);
        SK fromSearchKey = getExistSearchKey(uuid);
        doDelete(fromSearchKey);
    }

    public List<Resume> getAllSorted() {
        LOG.info("GetAllSorted ");
        List<Resume> list = doCopyAll();
//        for history)))
//        list.sort((r1, r2) -> r1.getFullName().compareTo(r2.getFullName()) != 0 ?
//                r1.getFullName().compareTo(r2.getFullName()) : r1.getUuid().compareTo(r2.getUuid()));
        list.sort(Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid));
        return list;
    }

    private SK getExistSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            LOG.warning("Resume " + uuid + " did not exist");
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private SK getNotExistSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            LOG.warning("Resume " + uuid + " already exists ");
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }
}

package com.urise.webapp.storage;

import com.urise.webapp.exeption.StorageException;
import com.urise.webapp.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private File directory;

    protected AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, " directory require not null");
        if (directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() && !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
    }

    protected abstract void doWrite(Resume r, File file) throws IOException;

    protected abstract Resume doRead(File file);

    @Override
    protected void doSave(Resume r, File file) {
        try {
            file.createNewFile();
            doWrite(r, file);
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    protected void doUpdate(Resume r, File file) {
        try {
            doWrite(r, file);
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    protected Resume doGet(File file) {
        return doRead(file);
    }

    @Override
    protected void doDelete(File file) {
        file.delete();
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected List<Resume> doCopyAll() {
        File[] files = directory.listFiles();
        assert files != null;
        Resume[] resumes = new Resume[files.length];
        int counter = 0;
        for (File file : files) {
            resumes[counter] = doRead(file);
        }
        return Arrays.asList(resumes);
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    public void clear() {
        File[] files = directory.listFiles();
        for (File file : files) {
            file.delete();
        }

    }

    @Override
    public int size() {
        return directory.list().length;
    }
}

package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {

//    private  static class ResumeComparator implements Comparator<Resume> {
//
//        @Override
//        public int compare(Resume o1, Resume o2) {
//            return o1.getUuid().compareTo(o2.getUuid());
//        }
//    }

//    private static final Comparator<Resume> RESUME_COMPARATOR = new Comparator<Resume>() {
//        @Override
//        public int compare(Resume o1, Resume o2) {
//            return o1.getUuid().compareTo(o2.getUuid());
//        }
//    };

//    private static final Comparator<Resume> RESUME_COMPARATOR = (o1, o2) -> o1.getUuid().compareTo(o2.getUuid());

    private static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getUuid);

    @Override
    protected void insertResume(Integer searchKey, Resume r) {
        int index = -(int) searchKey - 1;
        int length = size() - index;
        System.arraycopy(storage, index, storage, index + 1, length);
        storage[index] = r;
    }

    @Override
    protected void deleteResume(Integer searchKey) {
        int emptyIndex = searchKey;
        System.arraycopy(storage, emptyIndex + 1, storage, emptyIndex, size() - emptyIndex);
    }

    @Override
    protected boolean isExist(Integer searchKey) {
        return searchKey >= 0;
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        Resume keyResume = new Resume(uuid, "dummy");
        return Arrays.binarySearch(storage, 0, size(), keyResume, RESUME_COMPARATOR);
    }
}


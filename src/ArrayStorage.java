import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i < size(); i++) {
            storage[i] = null;
        }
    }

    void save(Resume r) {
        storage[size()] = r;
    }

    Resume get(String uuid) {
        if (storage[0].uuid.equals(uuid)) {
            return storage[0];
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (storage[i].uuid.equals(uuid)) {
                if (i != (size() - 1)) {
                    System.arraycopy(storage, i + 1, storage, i, size() - 1 - i);
                }
                storage[size() - 1] = null;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, size());
    }

    int size() {
        int size = 0;
        if (storage[0] != null) {
            for (Resume resume : storage) {
                if (resume != null) {
                    size++;
                }
            }
        }
        return size;
    }
}

package ru.job4j.generic;

import java.util.Objects;

public abstract class AbstractStore<T extends Base> implements Store<T>  {

    private SimpleArray<T> store;
    private int size;

    public AbstractStore(int size) {
        this.store = new SimpleArray<>(size);
        this.size = size;
    }

    /**
     * @deprecated
     * @param size
     */
    public void createArray(int size) {
        this.store = new SimpleArray<>(size);
        this.size = size;
    }

    @Override
    public void add(T model) {
        store.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        boolean result = false;

        int fibi = findIndexById(id);
        if (fibi != -1) {
            this.store.set(fibi, model);
            result = true;
        }

        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;

        int fibi = findIndexById(id);
        if (fibi != -1) {
            this.store.remove(fibi);
            result = true;
        }

        return result;
    }

    @Override
    public T findById(String id) {
        T result = null;

        for (T t : this.store) {
            if (t == null) {
                break;
            }
            if (Objects.equals(t.getId(), id)) {
                result = t;
                break;
            }
        }
        return result;
    }

    private int findIndexById(String id) {
        int result = -1;

        int index = 0;
        for (T t : this.store) {
            if (Objects.equals(t.getId(), id)) {
                result = index;
                break;
            }
            index++;
        }

        return result;
    }
}

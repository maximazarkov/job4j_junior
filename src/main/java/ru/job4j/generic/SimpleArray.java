package ru.job4j.generic;

import java.util.Iterator;

/**
 * Универсальная обертка над массивом. Структура массива не динамическая.
 * @param <T> - Тип объекта
 */
public class SimpleArray<T> implements Iterable<T> {
    private final int size;
    private int count = 0;
/**
 *  Вариант, если применять рефлексию
 *   private T[] array;
 */
    private final Object[] array;

    /**
     * @param size - количество ячеек
     */
    public SimpleArray(int size) {
        this.size = size;
        this.array = new Object[size];
/** нужно попробовать реализовать все через рефлексию. Первая попытка была неудачной:
 *   Class<T> t = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
 *
 *   try {
 * //T value = t.getDeclaredConstructor().newInstance();
 * //System.out.print("string. " + value);
 *       this.array = (T[]) t.getDeclaredConstructor().newInstance() ;
 *   } catch (Exception e) {
 *       e.printStackTrace();
 *   }
 */
    }

    /**
     * добавляет указанный элемент (model) в первую свободную ячейку;
     * Если идет переполнение надо выкинуть ошибку.
     * @param model - элемент
     * @throws ArrayIndexOutOfBoundsException - формируется исключение в случае
     * обращения за пределы массива.
     */
    public void add(T model) {
        this.array[count++] = model;
    }

    /**
     * заменяет указанным элементом (model) элемент, находящийся по индексу index;
     * @param index - индекс элемента
     * @param model - элемент
     * @throws ArrayIndexOutOfBoundsException - формируется исключение при попытке
     * обратиться за пределы массива.
     */
    public void set(int index, T model) {
        checkOutOfBounds(index, "попытка обратиться за пределы массива");
            this.array[index] = model;
    }

    /**
     * удаляет элемент по указанному индексу, все находящиеся справа элементы при этом
     * необходимо сдвинуть на единицу влево (в середине массива не должно быть пустых ячеек);
     * @param index - индекс элемента
     * @throws ArrayIndexOutOfBoundsException - формируется исключение, в случае обращения
     * за пределы массива.
     */
    public void remove(int index) {
        checkOutOfBounds(index, "попытка обратиться за пределы массива");
            int lenNewArray = count - index - 1;
            if (lenNewArray != 0) {
                System.arraycopy(this.array, (index + 1), this.array, (index), lenNewArray);
            } else {
                this.array[index] = null;
            }
            count--;
    }

    /**
     * возвращает элемент, расположенный по указанному индексу;
     * @param index - индекс элемента.
     * @return - элемент, расположенный по индексу index.
     * @throws ArrayIndexOutOfBoundsException - формируется исключение, если выполнено
     * обращение за пределы массива.
     */
    public T get(int index) throws ArrayIndexOutOfBoundsException {
        checkOutOfBounds(index, "попытка обратиться за пределы массива");
        return (T) this.array[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            int it = 0;
            @Override
            public boolean hasNext() {
                return  size > it;
            }

            @Override
            public T next() {
                return (T) array[it++];
            }
        };
    }

    private void checkOutOfBounds(int index, String s) {
        if (index > count) {
            throw new ArrayIndexOutOfBoundsException(s);
        }
    }
}
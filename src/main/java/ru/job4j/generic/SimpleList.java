package ru.job4j.generic;

/**
 * Демонстрация работы с Generic-ами. На примере класса Stack и модифицированного конструктора SimpleList
 * можно получить тип генерика. Это удобно, когда мы не можем получить тип генерика непосредственно, т.к. при
 * компиляции генерики подчищаются и компилятор не поймет, какой тип объекта был передан
 * @param <T> - подстановочный тип
 */

public class SimpleList<T> {

    Object[] objects;
    int index = 0;

    public SimpleList(int size) {
        this.objects = new Object[size];
/**
 *   Class<T> t = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
 *   try {
 *       T value = t.getDeclaredConstructor().newInstance();
 *       System.out.printf("string. " + value);
 *   } catch (Exception e) {
 *       e.printStackTrace();
 *   }
 */
    }

    public <K> K print(K value) {
        System.out.println(value);
        return value;
    }

    public void add(T value) {
        this.objects[index++] = value;
    }

    public T get(int position) {
        return (T) this.objects[position];
    }
}

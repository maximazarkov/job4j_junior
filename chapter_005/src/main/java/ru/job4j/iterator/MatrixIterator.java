package ru.job4j.iterator;

import java.util.Iterator;
/*
* Необходимо создать итератор для двухмерного массива.
int[][] value = {
   {1, 2}
   {3, 4}
};

метод next = должен вернуть последовательно 1, 2, 3, 4.

Старайтесь написать универсальное решение, чтобы оно не было жестко ориентировано на тестовый пример.
И хотя в примере указана квадратная матрица, программа должна корректно обрабатывать и jagged array тоже.
Пример jagged array - {{1},{2, 3, 4, 5,},{6, 7}, {8, 9, 10, 11, 12, 13, 14}}
Не используйте в данном задании коллекции из JDK, вспомогательные массивы и т.д.
Постарайтесь реализовать последовательным проходом по массиву.
*
* JaggedArrayIteratorTest - test done
* */

/**
 * Matrix Iterator
 *
 * @author Maxim Azarkov
 * @version $Id$
 * @since 0.1
 */
public class MatrixIterator implements Iterator {
    private int[][] values;
    private int indexCol = 0;
    private int indexRow = 0;

    /**
     * Constructor MatrixIterator
     * @param values - int[][]
     */
    public MatrixIterator(int[][] values) {
        this.values = values;
    }

    /**
     * hasNext - метод для проверки окончания элементов в двумерной матрице
     * @return - true, если в матрице еще есть элементы, false - следующий элемент отсуствует
     */
    @Override
    public boolean hasNext() {
//        return values.length > indexCol && values[indexCol].length > indexRow;
        return values.length > indexCol;
    }

    /**
     * next - возвращает элемент из матрицы и переключает маркер на следующий элемент
     * @return - возвращает элемент из массива
     */
    @Override
    public Object next() {
        int result = values[indexCol][indexRow++];
        if (values[indexCol].length == indexRow) {
            indexRow = 0;
            indexCol++;
        }
        return result;
    }
}

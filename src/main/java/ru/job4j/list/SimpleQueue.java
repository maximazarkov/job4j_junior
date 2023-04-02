package ru.job4j.list;

/**
 * Нужно реализовать очередь.
 *
 * Public class SimpleQueue<T>{
 *    public <T> poll()
 *
 *    public void push(T value);}
 *
 * Метод poll() - должен возвращать значение и удалять его из коллекции.
 * Метод push(T value) - помещает значение в коллекцию.
 *
 * Внутри очереди нужно использовать Стеки из задания 5.3.3. Используя контейнер на базе связанного списка создать контейнер Stack
 *
 * Описание Queue - очередь. Описывается FIFO - first input first output.
 *
 * То есть, первый зашел и первый вышел. Например.
 *
 * Push(1);
 * push(2);
 * push(3);
 *
 * poll() - 1
 * poll() - 2
 * poll() - 3
 *
 * Это задание является тестовым заданием на собеседованиях.
 * @param <T>
 */
public class SimpleQueue<T> {
    private SimpleStack<T> inputStack = new SimpleStack<>();
    private SimpleStack<T> outputStack = new SimpleStack<>();

    /**
     * Метод, прежде чем извлечь элемент, делать "переворот" второй коллекции путем сохранения ее в первой коллекции.
     * После перемещения первый введенный элемент оказывается на вершине стека - коллекции, но и удаляется по LIFO, т.к.
     * считается, что он пришел последним. Благодаря данным переворотам получается эффект FIFO.
     * @return
     */
    public T poll() {
        if (outputStack.isEmpty()) {
            while (!inputStack.isEmpty()) {
                outputStack.push(inputStack.poll());
            }
        }
        return (outputStack.isEmpty()) ? null : outputStack.poll();
    }

    /**
     * метод, прежде чем сохранить элемент, делать "переворот" первой коллекции путем сохранения ее во второй коллекции,
     * но при этом последний - новый элемент всегда остается в первой коллекции.
     * @param value - единственный элемент, который будет сохранен в первой коллекции, и перемещен во вторую при
     *              добавлении следующего элемента
     */
    public void push(T value) {
        inputStack.push(value);
    }
}

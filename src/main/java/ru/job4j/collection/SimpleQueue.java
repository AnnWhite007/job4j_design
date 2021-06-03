package ru.job4j.collection;

/**
 * 5. Очередь на двух стеках
 * Термин FIFO - first input first output. Первый пришел, первый ушел.
 */
public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int countPoll = 0;
    private int countPush = 0;

    //возвращает первое значение и удалять его из коллекции
    public T poll() {
        if (countPoll == 0) {
            while (countPush != 0) {
                out.push(in.pop());
                countPush--;
                countPoll++;
            }
        }
        countPoll--;
        return out.pop();
    }
//помещает значение в конец
    public void push(T value) {
        in.push(value);
        countPush++;
    }
}
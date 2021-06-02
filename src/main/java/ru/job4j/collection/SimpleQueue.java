package ru.job4j.collection;

/**
 * 5. Очередь на двух стеках
 * Термин FIFO - first input first output. Первый пришел, первый ушел.
 */
public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    //возвращает первое значение и удалять его из коллекции
    public T poll() {
        if (out == null) {
            while (in != null) {
                out.push(in.pop());
            }
        }
        return out.pop();
    }
//помещает значение в конец
    public void push(T value) {
        in.push(value);
    }
}
package ru.job4j.question;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * 2. Статистика по коллекции.
 * Определить разницу между начальным и измененным состояниями множества,
 * где элементами являются пользователи, у которых есть идентификатор и имя.
 * Существует три действия: добавили пользователя, удалили пользователя, изменили имя пользователя.
 * Два пользователя считаются равными, если у них равны идентификаторы и имена.
 * Необходимо вычислить:
 * - Сколько добавлено новых пользователей.
 * Добавленным считается такой пользователь, что ранее его не было в множестве previous, но в множестве current он есть.
 * - Сколько изменено пользователей. Изменённым считается объект, в котором изменилось имя, а id осталось прежним.
 * - Сколько удалено пользователей. Удаленным считается такой пользователь,
 * что ранее он был в множестве previous, но теперь в множестве current его нет.
 */

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Iterator <User> itprev = previous.iterator();
        Iterator <User> itcur = current.iterator();
        HashMap<Integer, String> changed = new HashMap<>();
        Info rsl = new Info(0, 0, 0);

        while (itcur.hasNext()) {
            User value = itcur.next();
            changed.put(value.getId(), value.getName());
        }
        int count = changed.size();

        while (itprev.hasNext()) {
            User value = itprev.next();
            if (changed.containsKey(value.getId())) {
                count--;
                if (!changed.get(value.getId()).equals(value.getName())) {
                    rsl.setChanged(rsl.getChanged() + 1);
                }
            } else {
                rsl.setDeleted(rsl.getDeleted() + 1);
            }
        }
        rsl.setAdded(count);
        return rsl;
    }
}
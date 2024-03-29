package ru.job4j.gc;

import java.util.Random;

/**
 * Виды сборщиков мусора
 *
 * Доступные сборщики мусора + Ключи для запуска:
 * - Serial => -XX:+UseSerialGC
 * Он является Serial, Stop The World, Copying, т.е. выполняет сборку мусора в одном потоке, вызывается событие Stop The World и используется Copying сборку мусора.
 * Плюс: минимальные требования к среде. Минус: медленный.
 * - Parallel => -XX:+UseParallelGC
 * Это усовершенствованная версия сборщика GC. Он является Parallel, Stop The World, Copying, т.е. его единственное отличие в том, что он выполняет сборку мусора в нескольких потоках.
 * Плюс: пошустрее чем Serial GC. Минус: недостаточно быстрый, по сравнению с Serial требует наличие нескольких ядер.
 * - CMS => -XX:+UseConcMarkSweepGC (доступен до JDK 14)
 * Это улучшенная версия Parallel GC. Он является Parallel, Concurrent, Copying. Лучше прошлого он тем, что используется параллелизм, т.е. часть heap чиститься наряду с работой приложения.
 * Плюс: небольшие паузу на сборку мусора. Минус: более высокие системные требования.
 *  - G1 => -XX:+UseG1GC
 *  Мощный сборщик сборщик мусора, усовершенствованная версия CMS. Он является Parallel, Concurrent, Copying. Его особенностью является высокая пропускная способность. Следовательно сборка мусора выполняется редко.
 *  Плюс: скорость. Минус: нужен большой heap (> 4gb)
 * - ZGC => -XX:+UseZGC
 * Он является Parallel, Concurrent, Copying. Причем он использует параллелизм на полную мощь. Тем самым для очистки вовсе не требует остановки приложения, в отличие от G1, где паузы хоть и маленькие но присутствуют.
 * Плюс: нет паузы. Минус: требует сверх большой heap в несколько терабайтов для эффективной работы
 *
 * Инструменты анализа памяти
 * Профилирование - это сбор и анализ характеристик работы приложения.
 * Для того чтобы получить pid (сокр. от proccess id), можно воспользоваться консольной утилитой - jps
 *
 * jmap - утилита позволяет увидеть как объекты создаются, какие ожидают удаления, т.е. объекты имеющие непосредственное отношение к памяти.
 * Также может сделать дамп памяти, т.е. сохранить ее состояние, как бы "сфоткать" ее.
 * jmap -histo pid
 *
 * jstat - предоставляет сводную информацию о состоянии памяти программы.
 * Она делает сэмприлование, т.е. периодически получает данные о состоянии разных участков памяти и их обобщает.
 * jstat -gc pid 1s 10 (сэмплинг каждую секунду в течении 10 секунд. Флаг -gc указывает на то, что мы хотим увидеть различные области памяти)
 *
 * jconsole - наиболее удобная программа для профилирования, т.к. она обладает графическим интерфейсом.
 */

public class GCTypeDemo {
    public static void main(String[] args) {
        Random random = new Random();
        int length = 100;
        String[] data = new String[1_000_000];
        for (int i = 0; ; i = (i + 1) % data.length) {
            data[i] = String.valueOf(
                    (char) random.nextInt(255)
            ).repeat(length);
        }
    }
}
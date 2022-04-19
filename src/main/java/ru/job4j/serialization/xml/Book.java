package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

/**
 * 3. Формат XML
 * XML eXtensible Markup Language  — расширяемый язык разметки. Данный язык очень похож на HTML, только в отличии от него является расширяемым,
 * что значит, мы можем писать свои теги, а не использовать зарезервированные.
 * В XML нет определенного синтаксиса для описания коллекции элементов, как в JSON.
 * Поэтому пишут так, добавляя окончание s или es, что говорит о том что это коллекция.
 * Применение:
 * - также как и JSON может быть использован для передачи данных между ресурсами в интернете.
 * - используется для декларативной настройки программ, т.е. формально через него мы можем настроить программу. Яркий пример pom.xml
 * - может служить основой для построения целых систем. Например, https://ru.wikipedia.org/wiki/Sedna
 * - в силу своей структуры может быть использован для формализации каких-то правил. Яркий пример checkstyle.xml
 * Структура XML:
 * 1. Объявление XML
 * Это первая строка, которая должна идти в файле, под расширением XML. В ней указывается кодировка и версия XML
 * 2. Теги
 * Теги - это основные детали из которых строится документ. Тег имеет имя и располагается между <>.
 * Между открывающим и закрывающим тегом уже располагаются либо другие теги либо текст той сущности, что мы описываем через тег.
 * 3. Атрибуты
 * - это часть синтаксиса, которая позволяет определить свойста элементов.
 * Атрибуты пишутся в открывающем теге, после его имени в формате: имяАтрибут="его значение"
 * 4. Комментарии
 * Комментарии как однострочные, так и многострочные пишутся внутри <!-- и -->
 *
 * 4. JAXB. Преобразование XML в POJO.
 * POJO (Plain Old Java Object) — «старый добрый Java-объект», простой Java-объект.
 * - не наследуется от других классов (возможно, кроме POJO-классов того же пакета)
 * - не реализует интерфейсов (иногда делается исключение для маркерных интерфейсов из стандартной библиотеки, или тех, которые нужны для бизнес-модели),
 * - не использует аннотаций в определениях
 * - не зависит от сторонних библиотек.
 * 1. Для начала нам нужно добавить зависимости на библиотеку JAXB с помощью которой мы будет делать преобразования
 * 2. Для использования JAXB нам нужно в модели добавить дефолтные конструкторы. С полей для простоты также уберем final.
 * 3. Для того чтобы сериализовать и десериализовать нам нужно добавить аннотации JAXB, которая даст библиотеке информацию о том как парсить объект.
 * 1) xml обязательно должен иметь корневой тег, в котором все и будет располагаться. Для его обозначения служит
 * "@XmlRootElement" Эту аннотацию нужно ставить над сущностью, которая будет корневой в нашем случае это Book
 * 2) Над вложенными сущностями нам нужно поставить просто @XmlElement
 * 3) Для того чтобы поле считалось атрибутом нужно поставить  @XmlAttribute, по умолчанию поле парсится как тег.
 * 4) Мы можем указать также как мы хотим читать/писать объект. По геттерам/сеттерам или напрямую по полям (используется рефлексия).
 * Для доступа по полям служит аннотация @XmlAccessType
 * Для выведения статусов необходимо переименовать элемент publishes в publish и использовать тег @XmlElementWrapper
 * "@XmlElementWrapper(name = "publishes")"
 * "@XmlElement(name = "publish")"
 * private String[] publishes;
 */

@XmlRootElement(name = "book")
@XmlAccessorType(XmlAccessType.FIELD)
public class Book {

    @XmlAttribute
    private String name;

    @XmlAttribute
    private int pages;
    private Author author;
    private String[] publish;

    @XmlAttribute
    private boolean read;

    public Book() {
    }

    public Book(String name, int pages, Author author, String[] publish, boolean read) {
        this.name = name;
        this.pages = pages;
        this.author = author;
        this.publish = publish;
        this.read = read;
    }

    @Override
    public String toString() {
        return "Book{"
                + "name=" + name
                + ", pages=" + pages
                + ", Author=" + author
                + ", publish=" + Arrays.toString(publish)
                + ", read=" + read
                + '}';
    }

    public static void main(String[] args) throws JAXBException {
        final Book book = new Book("Ono", 846, new Author("King", "21.09.1947"),
                new String[]{"Prosvescenie", "AST"}, false);

        /* Получаем контекст для доступа к АПИ */
        JAXBContext context = JAXBContext.newInstance(Book.class);
        /* Создаем сериализатор */
        Marshaller marshaller = context.createMarshaller();
        /* Указываем, что нам нужно форматирование */
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            /* Сериализуем */
            marshaller.marshal(book, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        } catch (Exception e) {

        }
        /* Для десериализации нам нужно создать десериализатор */
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            /* десериализуем */
            Book result = (Book) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}

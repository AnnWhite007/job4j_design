package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "author")
public class Author {

    @XmlAttribute
    private String name;

    @XmlAttribute
    private String birthday;

    public Author() { }

    public Author(String name, String birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Author{"
                + "name='" + name + '\''
                + "birthday='" + birthday + '\''
                + '}';
    }
}

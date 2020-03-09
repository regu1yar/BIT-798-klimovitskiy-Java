package hw2.reflection;

import java.util.List;

public interface XmlGenerator {
    <T> String toXml(List<T> entities) throws IllegalAccessException;
}

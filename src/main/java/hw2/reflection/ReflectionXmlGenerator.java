package hw2.reflection;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReflectionXmlGenerator implements XmlGenerator {
    private static final String TAB = "  ";

    private String indent;
    private StringBuilder xml;

    public ReflectionXmlGenerator() {
        this.indent = "";
        this.xml = new StringBuilder();
    }

    @Override
    public<T> String toXml(List<T> entities) throws IllegalAccessException {
        indent = "";
        xml.setLength(0);
        if (!entities.isEmpty()) {
            Class<?> entityClass = entities.get(0).getClass();
            String entityClassName = entityClass.getSimpleName();
            openMultilineTag(entityClassName);

            for (T entity : entities) {
                openMultilineTag(entityClassName.toLowerCase());

                for (Field field : entityClass.getDeclaredFields()) {
                    field.setAccessible(true);
                    openSinglelineTag(field.getName());
                    addSinglelineContent(field.get(entity).toString());
                    closeSinglelineTag(field.getName());
                }

                closeMultilineTag(entityClassName.toLowerCase());
            }

            closeMultilineTag(entityClassName, "");
        }

        return xml.toString();
    }

    private void openMultilineTag(String tag) {
        xml.append(indent).append('<').append(tag).append(">\n");
        indent += TAB;
    }

    private void openSinglelineTag(String tag) {
        xml.append(indent).append('<').append(tag).append('>');
    }

    private void closeMultilineTag(String tag) {
        closeMultilineTag(tag, "\n");
    }

    private void closeMultilineTag(String tag, String end) {
        indent = indent.substring(0, indent.length() - TAB.length());
        xml.append(indent).append("</").append(tag).append(">").append(end);
    }

    private void closeSinglelineTag(String tag) {
        xml.append("</").append(tag).append(">\n");
    }

    private void addMultilineContent(String content) {
        xml.append(indent).append(content).append('\n');
    }

    private void addSinglelineContent(String content) {
        xml.append(content);
    }
}

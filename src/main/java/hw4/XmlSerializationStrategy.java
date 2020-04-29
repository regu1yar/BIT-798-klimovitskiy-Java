package hw4;

import java.lang.reflect.Field;

public class XmlSerializationStrategy implements SerializationStrategy {
    @Override
    public String initiateStart(Object o) {
        return "<" + o.getClass().getSimpleName() + ">";
    }

    @Override
    public String initiateEnd(Object o) {
        return "</" + o.getClass().getSimpleName() + ">";
    }

    @Override
    public String serializeComplexFieldStart(Field field) {
        return "<" + field.getName() + ">";
    }

    @Override
    public String serializeComplexFieldEnd(Field field) {
        return "</" + field.getName() + ">";
    }

    @Override
    public String serializeSimpleField(Field field, Object o) throws IllegalAccessException {
        field.setAccessible(true);
        return "<" + field.getName() + ">" +
                field.get(o) +
                "</" + field.getName() + ">";
    }

    @Override
    public String serializeCollectionStart(Field field) {
        return "<" + field.getName() + ">";
    }

    @Override
    public String serializeCollectionEnd(Field field) {
        return "</" + field.getName() + ">";
    }

    @Override
    public String serializeCollectionSimpleItem(Object item, int index) {
        return "<" + index + ">" +
                item +
                "</" + index + ">";
    }

    @Override
    public String getDelimiter() {
        return "";
    }
}

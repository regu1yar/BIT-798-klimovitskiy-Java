package hw4;

import java.lang.reflect.Field;

public class JsonSerializationStrategy implements SerializationStrategy {
    @Override
    public String initiateStart(Object o) {
        return "{";
    }

    @Override
    public String initiateEnd(Object o) {
        return "}";
    }

    @Override
    public String serializeComplexFieldStart(Field field) {
        return "\"" + field.getName() + "\": {";
    }

    @Override
    public String serializeComplexFieldEnd(Field field) {
        return "}";
    }

    @Override
    public String serializeSimpleField(Field field, Object o) throws IllegalAccessException {
        field.setAccessible(true);
        if (field.getType() == String.class) {
            return "\"" + field.getName() + "\": \"" + field.get(o) + "\"";
        } else {
            return "\"" + field.getName() + "\": " + field.get(o);
        }
    }

    @Override
    public String serializeCollectionStart(Field field) {
        return "\"" + field.getName() + "\": [";
    }

    @Override
    public String serializeCollectionEnd(Field field) {
        return "]";
    }

    @Override
    public String serializeCollectionSimpleItem(Object item, int index) {
        if (item.getClass() == String.class) {
            return "\"" + item + "\"";
        } else {
            return item.toString();
        }
    }

    @Override
    public String getDelimiter() {
        return ",";
    }
}

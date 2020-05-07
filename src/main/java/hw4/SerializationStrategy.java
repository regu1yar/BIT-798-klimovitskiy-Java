package hw4;

import java.lang.reflect.Field;

public interface SerializationStrategy {
    String initiateStart(Object o);
    String initiateEnd(Object o);
    String serializeComplexFieldStart(Field field);
    String serializeComplexFieldEnd(Field field);
    String serializeSimpleField(Field field, Object o) throws IllegalAccessException;
    String serializeCollectionStart(Field field);
    String serializeCollectionEnd(Field field);
    String serializeCollectionSimpleItem(Object item, int index);
    String getDelimiter();
}

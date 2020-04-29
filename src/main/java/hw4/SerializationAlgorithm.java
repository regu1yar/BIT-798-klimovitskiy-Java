package hw4;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

public class SerializationAlgorithm {
    private final SerializationStrategy strategy;
    private String indent = "";

    private static final String TAB = "    ";
    private static final Set<Class<?>> WRAPPER_CLASSES = Set.of(
            Double.class, Float.class,
            Long.class, Integer.class, Short.class,
            Character.class, Byte.class,
            Boolean.class
    );

    public SerializationAlgorithm(SerializationStrategy strategy) {
        this.strategy = strategy;
    }

    public String serialize(Object o) throws IllegalAccessException {
        indent = "";
        StringBuilder serializedString = new StringBuilder();

        addItemOpening(strategy.initiateStart(o), serializedString);
        addItemContent(serializeFields(o), serializedString);
        addItemClosing(strategy.initiateEnd(o), serializedString);

        return serializedString.append('\n').toString();
    }

    private String serializeFields(Object o) throws IllegalAccessException {
        Collection<String> serializedFields = new ArrayList<>();
        for (Field field : o.getClass().getDeclaredFields()) {
            serializedFields.add(serializeField(field, o));
        }
        return joinContent(serializedFields);
    }

    private String serializeField(Field field, Object o) throws IllegalAccessException {
        if (isSimple(field)) {
            return serializeSimpleField(field, o);
        } else if (Collection.class.isAssignableFrom(field.getType())) {
            return serializeCollectionField(field, o);
        } else if (field.getType().isArray()) {
            return serializeArrayField(field, o);
        } else {
            return serializeComplexField(field, o);
        }
    }

    private String serializeSimpleField(Field field, Object o) throws IllegalAccessException {
        return strategy.serializeSimpleField(field, o);
    }

    private String serializeComplexField(Field field, Object o) throws IllegalAccessException {
        StringBuilder serializedComplexField = new StringBuilder();
        addItemOpening(strategy.serializeComplexFieldStart(field), serializedComplexField);

        field.setAccessible(true);
        addItemContent(serializeFields(field.get(o)), serializedComplexField);

        addItemClosing(strategy.serializeComplexFieldEnd(field), serializedComplexField);
        return serializedComplexField.toString();
    }

    private String serializeArrayField(Field field, Object o) throws IllegalAccessException {
        StringBuilder serializedArrayField = new StringBuilder();
        addItemOpening(strategy.serializeCollectionStart(field), serializedArrayField);

        addItemContent(serializeArrayItems(field, o), serializedArrayField);

        addItemClosing(strategy.serializeCollectionEnd(field), serializedArrayField);
        return serializedArrayField.toString();
    }

    private String serializeArrayItems(Field field, Object o) throws IllegalAccessException {
        field.setAccessible(true);
        Object array = field.get(o);
        int length = Array.getLength(array);
        Collection<String> serializedValues = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            System.out.println();
            serializedValues.add(strategy.serializeCollectionSimpleItem(Array.get(array, i), i + 1));
        }
        return joinContent(serializedValues);
    }

    private String serializeCollectionField(Field field, Object o) throws IllegalAccessException {
        StringBuilder serializedCollectionField = new StringBuilder();
        addItemOpening(strategy.serializeCollectionStart(field), serializedCollectionField);

        addItemContent(serializeCollectionItems(field, o), serializedCollectionField);

        addItemClosing(strategy.serializeCollectionEnd(field), serializedCollectionField);
        return serializedCollectionField.toString();
    }

    private String serializeCollectionItems(Field field, Object o) throws IllegalAccessException {
        field.setAccessible(true);
        Collection<String> serializedValues = new ArrayList<>();
        int index = 1;
        for (Object value : (Collection<?>) field.get(o)) {
            serializedValues.add(strategy.serializeCollectionSimpleItem(value, index));
            ++index;
        }
        return joinContent(serializedValues);
    }

    private boolean isSimple(Field field) {
        Class<?> fieldType = field.getType();
        return fieldType == String.class ||
                fieldType.isPrimitive() ||
                WRAPPER_CLASSES.contains(fieldType);
    }

    private String joinContent(Collection<String> serializedFields) {
        return String.join(strategy.getDelimiter() + "\n" + indent, serializedFields);
    }

    private void addItemOpening(String opening, StringBuilder serializedString) {
        serializedString.append(opening).append('\n');
        increaseIndent();
    }

    private void addItemClosing(String closing, StringBuilder serializedString) {
        decreaseIndent();
        serializedString.append(indent).append(closing);
    }

    private void addItemContent(String content, StringBuilder serializedString) {
        serializedString.append(indent).append(content).append('\n');
    }

    private void increaseIndent() {
        indent += TAB;
    }

    private void decreaseIndent() {
        indent = indent.substring(0, indent.length() - TAB.length());
    }
}

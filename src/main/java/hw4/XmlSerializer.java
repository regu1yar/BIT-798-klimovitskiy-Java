package hw4;

public class XmlSerializer implements Serializer {
    private final SerializationAlgorithm algorithm = new SerializationAlgorithm(new XmlSerializationStrategy());

    @Override
    public String serialize(Object o) throws IllegalAccessException {
        return algorithm.serialize(o);
    }
}

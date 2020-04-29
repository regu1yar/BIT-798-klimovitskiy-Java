package hw4;

public class JsonSerializer implements Serializer {
    private final SerializationAlgorithm algorithm = new SerializationAlgorithm(new JsonSerializationStrategy());

    @Override
    public String serialize(Object o) throws IllegalAccessException {
        return algorithm.serialize(o);
    }
}

package hw4;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class JsonSerializerTest {
    Serializer serializer = new JsonSerializer();

    @Test
    public void serialize() throws IllegalAccessException {
        String result = serializer.serialize(new Person(
                "Ivan",
                "Ivanov",
                new Address("Moscow", "101101"),
                List.of("123-1234-523", "432-23-232-23"),
                25,
                new int[] {1, 2, 4}));

        assertEquals("{\n" +
                        "    \"firstName\": \"Ivan\",\n" +
                        "    \"lastName\": \"Ivanov\",\n" +
                        "    \"address\": {\n" +
                        "        \"city\": \"Moscow\",\n" +
                        "        \"postalCode\": \"101101\"\n" +
                        "    },\n" +
                        "    \"phoneNumbers\": [\n" +
                        "        \"123-1234-523\",\n" +
                        "        \"432-23-232-23\"\n" +
                        "    ],\n" +
                        "    \"age\": 25,\n" +
                        "    \"accountIds\": [\n" +
                        "        1,\n" +
                        "        2,\n" +
                        "        4\n" +
                        "    ]\n" +
                        "}\n",
                result
        );
    }

    private static class Person {
        private final String firstName;
        private final String lastName;
        private final Address address;
        private final List<String> phoneNumbers;
        private final int age;
        private final int[] accountIds;

        public Person(String firstName, String lastName, Address address, List<String> phoneNumbers, int age, int[] accountIds) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.address = address;
            this.phoneNumbers = phoneNumbers;
            this.age = age;
            this.accountIds = accountIds;
        }
    }

    private static class Address {
        private final String city;
        private final String postalCode;

        public Address(String city, String postalCode) {
            this.city = city;
            this.postalCode = postalCode;
        }
    }
}
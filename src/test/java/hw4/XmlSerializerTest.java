package hw4;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class XmlSerializerTest {
    Serializer serializer = new XmlSerializer();

    @Test
    public void serialize() throws IllegalAccessException {
        String result = serializer.serialize(new Person(
                "Ivan",
                "Ivanov",
                new Address("Moscow", "101101"),
                List.of("123-1234-523", "432-23-232-23"),
                25,
                new int[] {1, 2, 4}));

        assertEquals("<Person>\n" +
                        "    <firstName>Ivan</firstName>\n" +
                        "    <lastName>Ivanov</lastName>\n" +
                        "    <address>\n" +
                        "        <city>Moscow</city>\n" +
                        "        <postalCode>101101</postalCode>\n" +
                        "    </address>\n" +
                        "    <phoneNumbers>\n" +
                        "        <1>123-1234-523</1>\n" +
                        "        <2>432-23-232-23</2>\n" +
                        "    </phoneNumbers>\n" +
                        "    <age>25</age>\n" +
                        "    <accountIds>\n" +
                        "        <1>1</1>\n" +
                        "        <2>2</2>\n" +
                        "        <3>4</3>\n" +
                        "    </accountIds>\n" +
                        "</Person>\n",
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
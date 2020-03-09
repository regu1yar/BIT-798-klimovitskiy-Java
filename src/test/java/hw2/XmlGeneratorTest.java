package hw2;

import org.junit.Assert;
import org.junit.Test;
import hw2.reflection.ReflectionXmlGenerator;
import hw2.reflection.XmlGenerator;

import java.util.List;

public class XmlGeneratorTest extends Assert {
    @Test
    public void testToXml() throws IllegalAccessException {
        XmlGenerator xmlGenerator = new ReflectionXmlGenerator();
        String result = xmlGenerator.toXml(List.of(
                new Person("Alex", 20),
                new Person("Bob", 25)
        ));

        assertEquals(
                "<Person>\n" +
                        "  <person>\n" +
                        "    <name>Alex</name>\n" +
                        "    <age>20</age>\n" +
                        "  </person>\n" +
                        "  <person>\n" +
                        "    <name>Bob</name>\n" +
                        "    <age>25</age>\n" +
                        "  </person>\n" +
                        "</Person>",
                result
        );
    }

    private static class Person {
        private final String name;
        private final int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
}

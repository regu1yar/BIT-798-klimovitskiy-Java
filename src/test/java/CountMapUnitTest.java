import countmap.CountMap;
import countmap.CountMapImpl;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class CountMapUnitTest extends Assert {
    @Test
    public void ExampleTest() {
        CountMap<Integer> map = new CountMapImpl<>();

        map.add(10);
        map.add(10);
        map.add(5);
        map.add(6);
        map.add(5);
        map.add(10);

        assertEquals(map.getCount(5), 2);
        assertEquals(map.getCount(6), 1);
        assertEquals(map.getCount(10), 3);
    }

    @Test
    public void addDifferentTypesTest() {
        CountMap<Number> map = new CountMapImpl<>();

        map.add(42);
        map.add(3.14);

        assertEquals(map.getCount(42), 1);
        assertEquals(map.getCount(3.14), 1);
    }

    @Test
    public void addAllSameTypesTest() {
        CountMap<Integer> countMap1 = new CountMapImpl<>();

        countMap1.add(0);
        countMap1.add(3);
        countMap1.add(0);
        countMap1.add(-2);
        countMap1.add(-2);
        countMap1.add(0);

        CountMap<Integer> countMap2 = new CountMapImpl<>();

        countMap2.add(1);
        countMap2.add(-2);
        countMap2.add(3);
        countMap2.add(1);
        countMap2.add(-2);

        countMap1.addAll(countMap2);

        assertEquals(countMap1.getCount(-2), 4);
        assertEquals(countMap1.getCount(0), 3);
        assertEquals(countMap1.getCount(1), 2);
        assertEquals(countMap1.getCount(3), 2);
    }

    @Test
    public void addAllDifferentTypesTest() {
        CountMap<Number> countMap1 = new CountMapImpl<>();
        countMap1.add(0);
        countMap1.add(3.14);
        countMap1.add(0);
        countMap1.add(-2.71);
        countMap1.add(-2.71);
        countMap1.add(0F);

        CountMap<Integer> countMap2 = new CountMapImpl<>();
        countMap2.add(1);
        countMap2.add(-2);
        countMap2.add(3);
        countMap2.add(1);
        countMap2.add(-2);
        countMap2.add(0);
        countMap1.addAll(countMap2);

        CountMap<Double> countMap3 = new CountMapImpl<>();
        countMap3.add(1.21);
        countMap3.add(-2.71);
        countMap3.add(3.14);
        countMap3.add(1D);
        countMap3.add(0D);
        countMap1.addAll(countMap3);

        assertEquals(countMap1.getCount(0), 3);
        assertEquals(countMap1.getCount(3.14), 2);
        assertEquals(countMap1.getCount(-2.71), 3);
        assertEquals(countMap1.getCount(0F), 1);
        assertEquals(countMap1.getCount(1), 2);
        assertEquals(countMap1.getCount(-2), 2);
        assertEquals(countMap1.getCount(3), 1);
        assertEquals(countMap1.getCount(1.21), 1);
        assertEquals(countMap1.getCount(1D), 1);
        assertEquals(countMap1.getCount(0D), 1);
    }

    @Test
    public void copyToSameTypesTest() {
        CountMap<Integer> countMap1 = new CountMapImpl<>();

        countMap1.add(0);
        countMap1.add(3);
        countMap1.add(0);
        countMap1.add(-2);
        countMap1.add(-2);
        countMap1.add(0);

        CountMap<Integer> countMap2 = new CountMapImpl<>();

        countMap2.add(1);
        countMap2.add(-2);
        countMap2.add(3);
        countMap2.add(1);
        countMap2.add(-2);

        countMap1.copyTo(countMap2);

        assertEquals(countMap2.getCount(-2), 4);
        assertEquals(countMap2.getCount(0), 3);
        assertEquals(countMap2.getCount(1), 2);
        assertEquals(countMap2.getCount(3), 2);
    }

    @Test
    public void copyToDifferentTypesTest() {
        CountMap<Number> countMap1 = new CountMapImpl<>();
        countMap1.add(0);
        countMap1.add(3.14);
        countMap1.add(0);
        countMap1.add(-2.71);
        countMap1.add(-2.71);
        countMap1.add(0F);

        CountMap<Integer> countMap2 = new CountMapImpl<>();
        countMap2.add(1);
        countMap2.add(-2);
        countMap2.add(3);
        countMap2.add(1);
        countMap2.add(-2);
        countMap2.add(0);
        countMap2.copyTo(countMap1);

        CountMap<Double> countMap3 = new CountMapImpl<>();
        countMap3.add(1.21);
        countMap3.add(-2.71);
        countMap3.add(3.14);
        countMap3.add(1D);
        countMap3.add(0D);
        countMap3.copyTo(countMap1);

        assertEquals(countMap1.getCount(0), 3);
        assertEquals(countMap1.getCount(3.14), 2);
        assertEquals(countMap1.getCount(-2.71), 3);
        assertEquals(countMap1.getCount(0F), 1);
        assertEquals(countMap1.getCount(1), 2);
        assertEquals(countMap1.getCount(-2), 2);
        assertEquals(countMap1.getCount(3), 1);
        assertEquals(countMap1.getCount(1.21), 1);
        assertEquals(countMap1.getCount(1D), 1);
        assertEquals(countMap1.getCount(0D), 1);
    }

    @Test
    public void toMapTest() {
        CountMap<Integer> countMap = new CountMapImpl<>();

        countMap.add(10);
        countMap.add(10);
        countMap.add(5);
        countMap.add(6);
        countMap.add(5);
        countMap.add(10);

        Map<Integer, Integer> map = new HashMap<>();
        countMap.toMap(map);

        assertEquals(map.get(5).intValue(), 2);
        assertEquals(map.get(6).intValue(), 1);
        assertEquals(map.get(10).intValue(), 3);
    }
}

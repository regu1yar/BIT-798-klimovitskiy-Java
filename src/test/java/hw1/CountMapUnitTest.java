package hw1;

import hw1.countmap.CountMap;
import hw1.countmap.CountMapImpl;
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

        assertEquals(2, map.getCount(5));
        assertEquals(1, map.getCount(6));
        assertEquals(3, map.getCount(10));
    }

    @Test
    public void addDifferentTypesTest() {
        CountMap<Number> map = new CountMapImpl<>();

        map.add(42);
        map.add(3.14);

        assertEquals(1, map.getCount(42));
        assertEquals(1, map.getCount(3.14));
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

        assertEquals(4, countMap1.getCount(-2));
        assertEquals(3, countMap1.getCount(0));
        assertEquals(2, countMap1.getCount(1));
        assertEquals(2, countMap1.getCount(3));
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

        assertEquals(3, countMap1.getCount(0));
        assertEquals(2, countMap1.getCount(3.14));
        assertEquals(3, countMap1.getCount(-2.71));
        assertEquals(1, countMap1.getCount(0F));
        assertEquals(2, countMap1.getCount(1));
        assertEquals(2, countMap1.getCount(-2));
        assertEquals(1, countMap1.getCount(3));
        assertEquals(1, countMap1.getCount(1.21));
        assertEquals(1, countMap1.getCount(1D));
        assertEquals(1, countMap1.getCount(0D));
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

        assertEquals(4, countMap2.getCount(-2));
        assertEquals(3, countMap2.getCount(0));
        assertEquals(2, countMap2.getCount(1));
        assertEquals(2, countMap2.getCount(3));
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

        assertEquals(3, countMap1.getCount(0));
        assertEquals(2, countMap1.getCount(3.14));
        assertEquals(3, countMap1.getCount(-2.71));
        assertEquals(1, countMap1.getCount(0F));
        assertEquals(2, countMap1.getCount(1));
        assertEquals(2, countMap1.getCount(-2));
        assertEquals(1, countMap1.getCount(3));
        assertEquals(1, countMap1.getCount(1.21));
        assertEquals(1, countMap1.getCount(1D));
        assertEquals(1, countMap1.getCount(0D));
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

        assertEquals(2, map.get(5).intValue());
        assertEquals(1, map.get(6).intValue());
        assertEquals(3, map.get(10).intValue());
    }
}

package hw1;

import hw1.countmap.collectionutils.CollectionUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class CollectionUtilsUnitTest extends Assert {
    @Test
    public void addAllTest() {
        List<Integer> list1 = new ArrayList<>(Arrays.asList(1, 3, 0, -1, 3));
        List<Integer> list2 = new ArrayList<>(Arrays.asList(-1, 0, 2, 1));

        CollectionUtils.addAll(list2, list1);

        List<Integer> result = new ArrayList<>(Arrays.asList(1, 3, 0, -1, 3, -1, 0, 2, 1));

        for (int i = 0; i < result.size(); i++) {
            assertEquals(result.get(i), list1.get(i));
        }
    }

    @Test
    public void indexOfTest() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 3, 0, -1, 3));

        assertEquals(0, CollectionUtils.indexOf(list, 1));
        assertEquals(1, CollectionUtils.indexOf(list, 3));
        assertEquals(2, CollectionUtils.indexOf(list, 0));
        assertEquals(3, CollectionUtils.indexOf(list, -1));
        assertEquals(-1, CollectionUtils.indexOf(list, 42));
    }

    @Test
    public void limitTest() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 3, 0, -1, 3));

        List<Integer> newList = CollectionUtils.limit(list, 3);

        assertEquals(3, newList.size());
        assertEquals(1, newList.get(0).intValue());
        assertEquals(3, newList.get(1).intValue());
        assertEquals(0, newList.get(2).intValue());
    }

    @Test
    public void addTest() {
        List<Number> list = new ArrayList<>();

        CollectionUtils.add(list, 42);
        assertEquals(42, list.get(0));

        CollectionUtils.add(list, 3.14);
        assertEquals(3.14, list.get(1));
    }

    @Test
    public void removeAllTest() {
        List<Number> from = new ArrayList<>();
        from.add(0);
        from.add(42);
        from.add(2.71);
        from.add(42.0);
        from.add(0);
        from.add(-1);


        List<Integer> list1 = new ArrayList<>();
        list1.add(0);
        list1.add(42);
        list1.add(111);

        CollectionUtils.removeAll(from, list1);

        assertEquals(3, from.size());
        assertEquals(2.71, from.get(0));
        assertEquals(42.0, from.get(1));
        assertEquals(-1, from.get(2));


        List<Double> list2 = new ArrayList<>();
        list2.add(2.71);
        list2.add(42.0);
        list2.add(3.14);

        CollectionUtils.removeAll(from, list2);

        assertEquals(1, from.size());
        assertEquals(-1, from.get(0));
    }

    @Test
    public void containsAllTest() {
        List<Number> from = new ArrayList<>();
        from.add(0);
        from.add(42);
        from.add(2.71);
        from.add(42.0);
        from.add(0);
        from.add(-1);


        List<Integer> list1 = new ArrayList<>();
        list1.add(0);
        list1.add(42);

        assertTrue(CollectionUtils.containsAll(from, list1));

        list1.add(111);

        assertFalse(CollectionUtils.containsAll(from, list1));


        List<Double> list2 = new ArrayList<>();
        list2.add(2.71);
        list2.add(42.0);

        assertTrue(CollectionUtils.containsAll(from, list2));

        list2.add(3.14);

        assertFalse(CollectionUtils.containsAll(from, list2));
    }

    @Test
    public void containsAnyTest() {
        List<Number> from = new ArrayList<>();
        from.add(0);
        from.add(42);
        from.add(2.71);
        from.add(42.0);
        from.add(0);
        from.add(-1);

        List<Integer> list1 = new ArrayList<>();
        assertFalse(CollectionUtils.containsAny(from, list1));
        list1.add(111);
        assertFalse(CollectionUtils.containsAny(from, list1));
        list1.add(0);
        assertTrue(CollectionUtils.containsAny(from, list1));
        list1.add(42);
        assertTrue(CollectionUtils.containsAny(from, list1));


        List<Double> list2 = new ArrayList<>();
        assertFalse(CollectionUtils.containsAny(from, list2));
        list2.add(3.14);
        assertFalse(CollectionUtils.containsAny(from, list2));
        list2.add(2.71);
        assertTrue(CollectionUtils.containsAny(from, list2));
        list2.add(42.0);
        assertTrue(CollectionUtils.containsAny(from, list2));
    }

    @Test
    public void rangeComparableTest() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 3, 0, -3, -1, 3, 5, 2, 4, -2));

        int min = -2;
        int max = 4;
        List<Integer> range = CollectionUtils.range(list, min, max);

        assertEquals(8, range.size());
        for (Integer integer : range) {
            assertTrue(min <= integer && integer <= max);
        }
    }

    @Test
    public void rangeComparatorTest() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 3, 0, -3, -1, 3, 5, 2, 4, -2, -4, 2));

        int min = 2;
        int max = -4;
        List<Integer> range = CollectionUtils.range(list, min, max, Comparator.reverseOrder());

        assertEquals(8, range.size());
        for (Integer integer : range) {
            assertTrue(Comparator.<Integer>reverseOrder().compare(min, integer) <= 0 &&
                    Comparator.<Integer>reverseOrder().compare(integer, max) <= 0);
        }
    }
}

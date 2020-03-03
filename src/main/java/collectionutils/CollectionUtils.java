package collectionutils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CollectionUtils {
    public static<E> void addAll(List<? extends E> source, List<? super E> destination) {
        destination.addAll(source);
    }

    public static<E> List<E> newArrayList() {
        return new ArrayList<>();
    }

    public static<E> int indexOf(List<? super E> source, E o) {
        return source.indexOf(o);
    }

    public static<E> List<E> limit(List<E> source, int size) {
        List<E> newList = newArrayList();
        for (E e : source) {
            if (newList.size() >= size) {
                return newList;
            }

            newList.add(e);
        }

        return newList;
    }

    public static<E> void add(List<? super E> source, E o) {
        source.add(o);
    }

    public static<E> void removeAll(List<E> removeFrom, List<? extends E> c2) {
        removeFrom.removeAll(c2);
    }

    public static<E> boolean containsAll(List<E> c1, List<? extends E> c2) {
        return c1.containsAll(c2);
    }

    public static<E> boolean containsAny(List<? super E> c1, List<E> c2) {
        for (E e : c2) {
            if (c1.contains(c2)) {
                return true;
            }
        }

        return false;
    }

    public static<E extends Comparable<E>> List<E> range(List<E> list, E min, E max) {
        List<E> newList = newArrayList();
        for (E e : list) {
            if (min.compareTo(e) <= 0 && e.compareTo(max) <= 0) {
                newList.add(e);
            }
        }

        return newList;
    }

    public static<E> List<E> range(List<E> list, E min, E max, Comparator<? super E> comparator) {
        List<E> newList = newArrayList();
        for (E e : list) {
            if (comparator.compare(min, e) <= 0 && comparator.compare(e, max) <= 0) {
                newList.add(e);
            }
        }

        return newList;
    }
}

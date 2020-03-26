package hw1.countmap;

import java.util.Map;

public interface CountMap<E> {
    void add(E key);
    int getCount(E key);
    <E1 extends E> void addAll(CountMap<E1> map);
    void copyTo(CountMap<? super E> destination);
    Map<E, Integer> asMap();
    void toMap(Map<? super E, Integer> destination);
}

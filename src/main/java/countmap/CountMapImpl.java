package countmap;

import java.util.HashMap;
import java.util.Map;

public class CountMapImpl<E> implements CountMap<E> {
    private HashMap<E, Integer> countMap;

    public CountMapImpl() {
        this.countMap = new HashMap<>();
    }

    @Override
    public void add(E key) {
        if (countMap.containsKey(key)) {
            countMap.put(key, countMap.get(key) + 1);
        } else {
            countMap.put(key, 1);
        }
    }

    @Override
    public int getCount(E key) {
        return countMap.get(key);
    }

    @Override
    public <E1 extends E> void addAll(CountMap<E1> map) {
        for (Map.Entry<E1, Integer> e : map.asMap().entrySet()) {
            if (countMap.containsKey(e.getKey())) {
                countMap.put(e.getKey(), countMap.get(e.getKey()) + e.getValue());
            } else {
                countMap.put(e.getKey(), e.getValue());
            }
        }
    }

    @Override
    public void copyTo(CountMap<? super E> destination) {
        for (Map.Entry<E, Integer> entry : countMap.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                destination.add(entry.getKey());
            }
        }
    }

    @Override
    public Map<E, Integer> asMap() {
        return countMap;
    }

    @Override
    public void toMap(Map<? super E, Integer> destination) {
        destination.clear();
        destination.putAll(countMap);
    }
}

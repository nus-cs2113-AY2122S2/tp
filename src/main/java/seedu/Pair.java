package seedu;

/**
 * Implementation of Pair which is not available in Java 11. Referenced from https://stackoverflow.com/a/59945161
 * @param <K> the class of the key
 * @param <V> the class of the value
 */
public class Pair<K, V>{

    K key;
    V value;

    public Pair(K key, V  value) {
        this.key = key;
        this.value = value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public V getValue() {
        return value;
    }

    public void setKey(K key) {
        this.key = key;
    }
    public K getKey() {
        return key;
    }
}

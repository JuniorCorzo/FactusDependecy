package io.github.juniorcorzo.context.interfaces;

import java.util.Map;

public interface DataContext<K, T> {
    boolean containsKey(K key);

    Map<K, T> getContext();

    T getValue(K key);

    void refreshContext();
}

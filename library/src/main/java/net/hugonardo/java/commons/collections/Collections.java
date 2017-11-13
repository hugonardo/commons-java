package net.hugonardo.java.commons.collections;

import java.util.Collection;

public final class Collections {

    public static <T> boolean contains(T[] array, T item) {
        if (array == null) {
            return false;
        }
        for (T t : array) {
            if (t == null && item == null) {
                return true;
            }
            if (t != null && t.equals(item)) {
                return true;
            }
        }
        return false;
    }

    public static <T> boolean empty(T[] array) {
        return array == null || array.length == 0;
    }

    public static boolean empty(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    public static <T> boolean nonEmpty(T[] array) {
        return !empty(array);
    }

    public static boolean nonEmpty(Collection collection) {
        return !empty(collection);
    }
}

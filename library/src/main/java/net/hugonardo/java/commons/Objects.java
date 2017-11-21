package net.hugonardo.java.commons;

import java.util.Arrays;
import java.util.Comparator;
import javax.annotation.Nullable;

@SuppressWarnings("unused")
public class Objects {

    public static boolean areEquals(@Nullable Object var0, @Nullable Object var1) {
        return var0 == var1 || var0 != null && var0.equals(var1);
    }

    public static <T> int compare(T var0, T var1, Comparator<? super T> var2) {
        return var0 == var1 ? 0 : var2.compare(var0, var1);
    }

    public static int hash(Object... var0) {
        return Arrays.hashCode(var0);
    }

    public static int hashCode(@Nullable Object var0) {
        return var0 != null ? var0.hashCode() : 0;
    }

    public static boolean isNull(@Nullable Object value) {
        return value == null;
    }

    public static boolean nonEquals(@Nullable Object var0, @Nullable Object var1) {
        return !areEquals(var0, var1);
    }

    public static boolean nonNull(@Nullable Object value) {
        return value != null;
    }

    public static boolean not(boolean exp) {
        return !exp;
    }

    public static <T> T requireNonNull(T obj) {
        if (obj == null) {
            throw new NullPointerException();
        } else {
            return obj;
        }
    }

    public static <T> T requireNonNull(T obj, String message) {
        if (obj == null) {
            throw new NullPointerException(message);
        } else {
            return obj;
        }
    }

    public static String toString(Object var0) {
        return String.valueOf(var0);
    }

    public static String toString(Object var0, String var1) {
        return var0 != null ? var0.toString() : var1;
    }

    private Objects() {
        throw new AssertionError("No net.hugonardo.java.commons.Objects instances for you!");
    }
}

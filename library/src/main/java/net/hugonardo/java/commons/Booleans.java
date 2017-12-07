package net.hugonardo.java.commons;

import static net.hugonardo.java.commons.Objects.nonNull;
import static net.hugonardo.java.commons.Objects.not;

import javax.annotation.Nullable;

public class Booleans {

    public static boolean isFalse(@Nullable Boolean exp) {
        return nonNull(exp) && not(exp);
    }

    public static boolean isTrue(@Nullable Boolean exp) {
        return nonNull(exp) && exp;
    }

    private Booleans() {
        throw new AssertionError("No net.hugonardo.java.commons.Booleans instances for you!");
    }
}

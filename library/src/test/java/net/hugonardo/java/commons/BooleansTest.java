package net.hugonardo.java.commons;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.*;

public class BooleansTest {

    @Test
    public void isFalse() throws Exception {
        assertThat(Booleans.isFalse(Boolean.FALSE), is(true));
        assertThat(Booleans.isFalse(Boolean.TRUE), is(false));
        assertThat(Booleans.isFalse(null), is(false));
    }

    @Test
    public void isTrue() throws Exception {
        assertThat(Booleans.isTrue(Boolean.TRUE), is(true));
        assertThat(Booleans.isTrue(Boolean.FALSE), is(false));
        assertThat(Booleans.isTrue(null), is(false));
    }
}
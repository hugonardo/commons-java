package net.hugonardo.java.commons.text;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.*;

public class CypherTest {

    private Cypher mCypher;

    @Test
    public void checkMd5() throws Exception {
        String md5 = mCypher.toMd5("022065");
        assertThat(md5, is("c7ec05991cc00f7c023b196b7be34d0b"));
    }

    @Test
    public void checkSha1() throws Exception {
        String sha1 = mCypher.toSha1("022065");
        assertThat(sha1, is("2a55ee5b6b57f319bba05ee2d480bace55ecc73e"));
    }

    @Before
    public void setUp() throws Exception {
        mCypher = new Cypher();
    }
}
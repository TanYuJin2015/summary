package common;

import org.junit.Assert;
import org.junit.Test;

public class PowTest {
    @Test
    public void testPow() {
        Assert.assertEquals(8, Pow.pow(2,3));
        Assert.assertEquals(256, Pow.pow(4,4));
    }
}

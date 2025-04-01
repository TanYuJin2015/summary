package common;

import org.junit.Assert;
import org.junit.Test;

public class GcdTest {
    @Test
    public void testGCD() {
        Assert.assertEquals(10, Gcd.gcd(50, 10));
        Assert.assertEquals(16, Gcd.gcd(64, 48));
    }
}

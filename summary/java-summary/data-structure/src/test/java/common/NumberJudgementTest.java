package common;

import org.junit.Assert;
import org.junit.Test;

public class NumberJudgementTest {
    @Test
    public void testNumberJudgement() {
        Assert.assertTrue(NumberJudgement.isEven(406));
        Assert.assertFalse(NumberJudgement.isEven(333));

        Assert.assertTrue(NumberJudgement.isOdd(333));
        Assert.assertFalse(NumberJudgement.isOdd(406));

        Assert.assertTrue(NumberJudgement.isPrime(11));
        Assert.assertFalse(NumberJudgement.isPrime(406));

        Assert.assertEquals(17, NumberJudgement.nextPrime(14));
    }
}

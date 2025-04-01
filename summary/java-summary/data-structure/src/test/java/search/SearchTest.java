package search;

import org.junit.Assert;
import org.junit.Test;

public class SearchTest {
    @Test
    public void testBinarySearch() {
        Integer[] items1 = new Integer[]{1, 2, 4, 8, 16, 32, 64, 128, 256, 512};
        Integer[] items2 = new Integer[]{512, 256, 128, 64, 32, 16, 8, 4, 2, 1};

        Assert.assertEquals(1, Search.binaryAscSearch(items1, 2));
        Assert.assertEquals(2, Search.binaryDescSearch(items2, 128));
    }

    @Test
    public void testQuickSearch() {
        Integer[] array = new Integer[]{4, 19, 16, 8, 14, 15, 6, 20, 18, 5, 1, 9, 11, 10, 2, 7, 3, 13, 12, 17};

        Assert.assertEquals(5, (int)Search.quickMinSelect(array, 5));

        Assert.assertEquals(16, (int)Search.quickMaxSelect(array, 5));
    }
}

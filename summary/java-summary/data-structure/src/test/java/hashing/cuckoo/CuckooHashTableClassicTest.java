package hashing.cuckoo;

import hashing.cuckoo.family.StringHashFamily;
import org.junit.Assert;
import org.junit.Test;

public class CuckooHashTableClassicTest {
    @Test
    public void testCuckooHashTableClassic() {
        CuckooHashTableClassic<String> cuckooHashTable = new CuckooHashTableClassic(new StringHashFamily(2));
        cuckooHashTable.insert("TanYuJin");
        Assert.assertTrue(cuckooHashTable.contains("TanYuJin"));

        cuckooHashTable.remove("TanYuJin");
        Assert.assertFalse(cuckooHashTable.contains("TanYuJin"));
    }
}

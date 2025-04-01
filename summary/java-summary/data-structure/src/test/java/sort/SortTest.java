package sort;

import org.junit.Assert;
import org.junit.Test;

public class SortTest {
    @Test
    public void testSelectSort() {
        Integer[] array = new Integer[]{4, 19, 16, 8, 14, 15, 6, 20, 18, 5, 1, 9, 11, 10, 2, 7, 3, 13, 12, 17};

        Integer[] ascResult = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        Integer[] descResult = new Integer[]{20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};

        Integer[] ascResult02 = new Integer[]{11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Integer[] descResult02 = new Integer[]{11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        Sort.selectAscSort(array);
        Assert.assertArrayEquals(ascResult, array);

        Sort.selectDescSort(array);
        Assert.assertArrayEquals(descResult, array);

        Sort.selectAscSort(array, 0, 9);
        Sort.selectAscSort(array, 10, 19);
        Assert.assertArrayEquals(ascResult02, array);

        Sort.selectDescSort(array, 10, 19);
        Assert.assertArrayEquals(descResult02, array);
    }

    @Test
    public void testBubbleSort() {
        Integer[] array = new Integer[]{4, 19, 16, 8, 14, 15, 6, 20, 18, 5, 1, 9, 11, 10, 2, 7, 3, 13, 12, 17};

        Integer[] ascResult = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        Integer[] descResult = new Integer[]{20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};

        Integer[] ascResult02 = new Integer[]{11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Integer[] descResult02 = new Integer[]{11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};

        Sort.bubbleAscSort(array);
        Assert.assertArrayEquals(ascResult, array);

        Sort.bubbleDescSort(array);
        Assert.assertArrayEquals(descResult, array);

        Sort.bubbleAscSort(array, 0, 9);
        Sort.bubbleAscSort(array, 10, 19);
        Assert.assertArrayEquals(ascResult02, array);

        Sort.bubbleDescSort(array, 10, 19);
        Assert.assertArrayEquals(descResult02, array);
    }

    @Test
    public void testInsertionSort() {
        Integer[] array = new Integer[]{4, 19, 16, 8, 14, 15, 6, 20, 18, 5, 1, 9, 11, 10, 2, 7, 3, 13, 12, 17};

        Integer[] ascResult = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        Integer[] descResult = new Integer[]{20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};

        Integer[] ascResult02 = new Integer[]{11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Integer[] descResult02 = new Integer[]{11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};

        Sort.insertionAscSort(array);
        Assert.assertArrayEquals(ascResult, array);

        Sort.insertionDescSort(array);
        Assert.assertArrayEquals(descResult, array);

        Sort.insertionAscSort(array, 0, 9);
        Sort.insertionAscSort(array, 10, 19);
        Assert.assertArrayEquals(ascResult02, array);

        Sort.insertionDescSort(array, 10, 19);
        Assert.assertArrayEquals(descResult02, array);
    }

    @Test
    public void testShellSort() {
        Integer[] array = new Integer[]{4, 19, 16, 8, 14, 15, 6, 20, 18, 5, 1, 9, 11, 10, 2, 7, 3, 13, 12, 17};

        Integer[] ascResult = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        Integer[] descResult = new Integer[]{20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};

        Integer[] ascResult02 = new Integer[]{11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Integer[] descResult02 = new Integer[]{11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};

        Sort.shellAscSort(array);
        Assert.assertArrayEquals(ascResult, array);

        Sort.shellDescSort(array);
        Assert.assertArrayEquals(descResult, array);

        Sort.shellAscSort(array, 0, 9);
        Sort.shellAscSort(array, 10, 19);
        Assert.assertArrayEquals(ascResult02, array);

        Sort.shellDescSort(array, 10, 19);
        Assert.assertArrayEquals(descResult02, array);
    }

    @Test
    public void testHeapSort() {
        Integer[] array = new Integer[]{4, 19, 16, 8, 14, 15, 6, 20, 18, 5, 1, 9, 11, 10, 2, 7, 3, 13, 12, 17};

        Integer[] ascResult = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        Integer[] descResult = new Integer[]{20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        Sort.heapAscSort(array);
        Assert.assertArrayEquals(ascResult, array);

        Sort.heapDescSort(array);
        Assert.assertArrayEquals(descResult, array);
    }

    @Test
    public void testMergeSort() {
        Integer[] array = new Integer[]{4, 19, 16, 8, 14, 15, 6, 20, 18, 5, 1, 9, 11, 10, 2, 7, 3, 13, 12, 17};

        Integer[] ascResult = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        Integer[] descResult = new Integer[]{20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};

        Integer[] ascResult02 = new Integer[]{11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Integer[] descResult02 = new Integer[]{11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};

        Sort.mergeAscSort(array);
        Assert.assertArrayEquals(ascResult, array);

        Sort.mergeDescSort(array);
        Assert.assertArrayEquals(descResult, array);

        Sort.mergeAscSort(array, 0, 9);
        Sort.mergeAscSort(array, 10, 19);
        Assert.assertArrayEquals(ascResult02, array);

        Sort.mergeDescSort(array, 10, 19);
        Assert.assertArrayEquals(descResult02, array);
    }

    @Test
    public void testQuickSort() {
        Integer[] array = new Integer[]{4, 19, 16, 8, 14, 15, 6, 20, 18, 5, 1, 9, 11, 10, 2, 7, 3, 13, 12, 17};

        Integer[] ascResult = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        Integer[] descResult = new Integer[]{20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};

        Integer[] ascResult02 = new Integer[]{11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Integer[] descResult02 = new Integer[]{11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};

        Sort.quickAscSort(array);
        Assert.assertArrayEquals(ascResult, array);

        Sort.quickDescSort(array);
        Assert.assertArrayEquals(descResult, array);

        Sort.quickAscSort(array, 0, 9);
        Sort.quickAscSort(array, 10, 19);
        Assert.assertArrayEquals(ascResult02, array);

        Sort.quickDescSort(array, 10, 19);
        Assert.assertArrayEquals(descResult02, array);
    }

    @Test
    public void testRadixSort() {
        String[] strLen4_1 = {"term", "fast", "deal", "duty", "face", "chew", "chat",
                            "mess", "obey", "race", "care", "exam", "rest", "deep",
                            "burn", "knee", "pain", "hurt", "drop", "wind", "bark",
                            "lift", "sink", "miss", "okay", "suit", "type", "risk",
                            "main", "trek", "fall", "wine", "firm", "pack", "spot"};
        String[] strLen4_2 = {"term", "fast", "deal", "duty", "face", "chew", "chat",
                            "mess", "obey", "race", "care", "exam", "rest", "deep",
                            "burn", "knee", "pain", "hurt", "drop", "wind", "bark",
                            "lift", "sink", "miss", "okay", "suit", "type", "risk",
                            "main", "trek", "fall", "wine", "firm", "pack", "spot"};
        String[] strLen4_result = {"bark", "burn", "care", "chat", "chew", "deal", "deep",
                                "drop", "duty", "exam", "face", "fall", "fast", "firm",
                                "hurt", "knee", "lift", "main", "mess", "miss", "obey",
                                "okay", "pack", "pain", "race", "rest", "risk", "sink",
                                "spot", "suit", "term", "trek", "type", "wind", "wine"};

        String[] strUnequalLen = {"disrupt", "splash", "adverse", "extinct", "priest", "marsh", "graze",
                                "batch", "afflict", "zero", "incur", "cereal", "commute", "wedge",
                                "diffuse", "lick", "dwell", "entity", "assert", "web", "amends", "flap",
                                "heir", "insame", "veto", "asset", "mutter", "brittle", "naive", "offset",
                                "perish", "ple", "prey", "toss", "ego", "virus", "concede", "yell",
                                "brace", "acquisition", "configuration"};
        String[] strUnequalLen_result = {"acquisition", "adverse", "afflict", "amends", "assert", "asset",
                                        "batch", "brace", "brittle", "cereal", "commute", "concede", "configuration",
                                        "diffuse", "disrupt", "dwell", "ego", "entity", "extinct", "flap", "graze",
                                        "heir", "incur", "insame", "lick", "marsh", "mutter", "naive", "offset",
                                        "perish", "ple", "prey", "priest", "splash", "toss", "veto", "virus", "web",
                                        "wedge", "yell", "zero"};

        // 定长字符串的基数排序（ArrayList的简单实现）
        Sort.radixSortA(strLen4_1, strLen4_1[0].length());
        // 定长字符串的计数基数排序
        Sort.countingRadixSort(strLen4_2, strLen4_2[0].length());
        // 变长字符串的基数排序
        Sort.radixSort(strUnequalLen, 13);

        Assert.assertArrayEquals(strLen4_result, strLen4_1);
        Assert.assertArrayEquals(strLen4_result, strLen4_2);
        Assert.assertArrayEquals(strUnequalLen, strUnequalLen_result);
    }
}

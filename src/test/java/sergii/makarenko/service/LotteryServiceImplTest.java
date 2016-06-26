package sergii.makarenko.service;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LotteryServiceImplTest {

    private LotteryServiceImpl lotteryServiceImpl;

    @Before
    public void initialization() {
        lotteryServiceImpl = new LotteryServiceImpl();
    }

    @Test
    public void findLongestCommonSubsequence() {
        assertEquals(4, lotteryServiceImpl.findLongestCommonSubsequence("1234567890", "13153137531"));
        assertEquals(4, lotteryServiceImpl.findLongestCommonSubsequence("456000123", "1234567890"));
        assertEquals(2, lotteryServiceImpl.findLongestCommonSubsequence("456000123", "0987654321"));
        assertEquals(2, lotteryServiceImpl.findLongestCommonSubsequence("456000123", "9988776650"));
        assertEquals(1, lotteryServiceImpl.findLongestCommonSubsequence("456000123", "1111999911"));
        assertEquals(3, lotteryServiceImpl.findLongestCommonSubsequence("456000123", "1122334455"));
        assertEquals(6, lotteryServiceImpl.findLongestCommonSubsequence("456000123", "4680468023"));
        assertEquals(3, lotteryServiceImpl.findLongestCommonSubsequence("456000123", "0000000000"));
        assertEquals(0, lotteryServiceImpl.findLongestCommonSubsequence("456000123", "9988778899"));
        assertEquals(1, lotteryServiceImpl.findLongestCommonSubsequence("456000123", "9988776655"));
        assertEquals(3, lotteryServiceImpl.findLongestCommonSubsequence("abcde", "ebcda"));
    }

    @Test
    public void findLongestCommonSubsequenceWithNullParam() {
        assertEquals(0, lotteryServiceImpl.findLongestCommonSubsequence(null, "13153137531"));
    }

    @Test
    public void findLongestCommonSubsequenceWithEmptyParam() {
        assertEquals(0, lotteryServiceImpl.findLongestCommonSubsequence(null, "13153137531"));
    }

}
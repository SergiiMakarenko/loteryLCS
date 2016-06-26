package sergii.makarenko.service;

import sergii.makarenko.domain.LotteryPlayer;
import sergii.makarenko.domain.LotteryTicket;

import java.io.IOException;
import java.util.List;
import java.util.SortedMap;

/**
 * @author serg
 */
public interface LotteryService {

    void checkCommandLineParameters(String[] args);

    int findLongestCommonSubsequence(String firstString, String secondString);

    List<LotteryTicket> readLotteryTicketsFromFile(String filePath, int bufferSize) throws IOException;

    LotteryTicket createLotteryTicketFromLine(String line);

    SortedMap<LotteryPlayer, Integer> checkLotteryTicket(List<LotteryTicket> lotteryTicketList,
                                                         String winningLotteryNumber);
}

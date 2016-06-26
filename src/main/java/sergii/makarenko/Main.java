package sergii.makarenko;

import sergii.makarenko.domain.LotteryPlayer;
import sergii.makarenko.domain.LotteryTicket;
import sergii.makarenko.service.LotteryService;
import sergii.makarenko.service.LotteryServiceImpl;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

/**
 * the main class for program run
 *
 * @author serg
 */
public class Main {

    private static LotteryService lotteryService = new LotteryServiceImpl();
    private static final int BUFFER_SIZE = 1024;

    public static void main(String[] args) {
        lotteryService.checkCommandLineParameters(args);
        List<LotteryTicket> lotteryTicketList = null;
        try {
            lotteryTicketList = lotteryService.readLotteryTicketsFromFile(args[0], BUFFER_SIZE);
        } catch (IOException e) {
            System.out.println("Error: " + (e.getCause() == null ? e.getMessage() : e.getCause().getMessage()));
            e.printStackTrace();
            System.exit(0);
        }
        SortedMap<LotteryPlayer, Integer> winners = lotteryService.checkLotteryTicket(lotteryTicketList, args[1]);
        System.out.println("Winners: ");
        if (winners.isEmpty()) {
            System.out.println("No winners");
        } else {
            Iterator<Map.Entry<LotteryPlayer, Integer>> iterator = winners.entrySet().iterator();
            Map.Entry<LotteryPlayer, Integer> lotteryPlayerIntegerEntry;
            while (iterator.hasNext()) {
                lotteryPlayerIntegerEntry = iterator.next();
                System.out.println(lotteryPlayerIntegerEntry.getKey() + "," + lotteryPlayerIntegerEntry.getValue());
            }
        }
    }
}

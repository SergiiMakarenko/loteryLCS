package sergii.makarenko.service;

import sergii.makarenko.domain.LotteryPlayer;
import sergii.makarenko.domain.LotteryTicket;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.*;

/**
 * @author serg
 */
public class LotteryServiceImpl implements LotteryService {

    /**
     * Expect 2 params - file path and winner number.
     *
     * @param args - string param from command line
     */
    @Override
    public void checkCommandLineParameters(String[] args) {
        if (args.length != 2)
            throw new IllegalArgumentException("Wrong parameters number: expected 2, founded " + args.length);
    }

    /**
     * method for calculating the size of longest common subsequence from 2 strings
     *
     * @param firstString - first string
     * @param secondString - second string
     * @return size of longest common subsequence from 2 strings
     */
    @Override
    public int findLongestCommonSubsequence(String firstString, String secondString) {
        if (firstString == null || firstString.isEmpty() || secondString == null || secondString.isEmpty())
            return 0;
        int firstStringLength = firstString.length();
        int secondStringLength = secondString.length();
        int currentCharPosition = 0;
        int LCSCurrent = 0;
        int LCSResult = 0;

        for (int i = 0; i < firstStringLength; i++) {
            for (int j = i; j < firstStringLength; j++) {
                for (int k = currentCharPosition; k < secondStringLength; k++) {
                    if (firstString.charAt(j) == secondString.charAt(k)) {
                        LCSCurrent++;
                        currentCharPosition = k + 1;
                        break;
                    }
                }
            }
            currentCharPosition = 0;
            LCSResult = LCSCurrent > LCSResult ? LCSCurrent : LCSResult;
            LCSCurrent = 0;
        }
        return LCSResult;
    }

    /**
     *
     * @param filePath - path to file
     * @param bufferSize - size of buffer
     * @return the list of LotteryTickets from file
     * @throws IOException
     */
    @Override
    public List<LotteryTicket> readLotteryTicketsFromFile(String filePath, int bufferSize) throws IOException {
        List<LotteryTicket> lotteryTicketList = new ArrayList<>();
        RandomAccessFile fileTickets = new RandomAccessFile(filePath, "r");
        FileChannel channelTickets = fileTickets.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(bufferSize);
        StringBuilder stringBuilder = new StringBuilder();
        while (channelTickets.read(buffer) > 0) {
            buffer.flip();
            for (int i = 0; i < buffer.limit(); i++) {
                char ch = (char) buffer.get();
                if (ch == '\n' || ch == '\r') {
                    lotteryTicketList.add(createLotteryTicketFromLine(stringBuilder.toString()));
                    stringBuilder.delete(0, stringBuilder.length());
                } else {
                    stringBuilder.append(ch);
                }
            }
            buffer.clear();
        }
        channelTickets.close();
        fileTickets.close();
        return lotteryTicketList;
    }

    /**
     * Assumption - sting has contain 4 fields separated by comma:
     * last name, first name, country, ticket number
     *
     * @param line - string tickets
     * @return LotteryTicket from line
     */
    @Override
    public LotteryTicket createLotteryTicketFromLine(String line) {
        List<String> ticketFields;
        ticketFields = Arrays.asList(line.split(","));
        if (ticketFields.size() != NUMBER_FIELDS_IN_LINE) {
            throw new IllegalArgumentException("Incorrect line in file: " + line + ".\n" +
                    "Must be 4 fields in line, but in fact founded " + ticketFields.size() + " field");
        }
        return new LotteryTicket(new LotteryPlayer(ticketFields.get(0), ticketFields.get(1), ticketFields.get(2)),
                ticketFields.get(3));
    }

    /**
     * Method for creating SortedMap<LotteryPlayer, Integer> from list of LotteryTicket and winning number
     *
     * @param lotteryTicketList - list of tickets
     * @param winningLotteryNumber - winning number
     * @return map this winners and their credits
     */
    @Override
    public SortedMap<LotteryPlayer, Integer> checkLotteryTicket(List<LotteryTicket> lotteryTicketList,
                                                                String winningLotteryNumber) {
        SortedMap<LotteryPlayer, Integer> lotteryWinners = new TreeMap<>();
        if (lotteryTicketList == null || lotteryTicketList.isEmpty() ||
                winningLotteryNumber == null || winningLotteryNumber.isEmpty())
            return lotteryWinners;
        int currentTicketCredits;
        Integer currentPlayerCredits;
        for (LotteryTicket lotteryTicket : lotteryTicketList) {
            currentTicketCredits = findLongestCommonSubsequence(winningLotteryNumber, lotteryTicket.getTicketNumber());
            if (currentTicketCredits > 0) {
                currentPlayerCredits = lotteryWinners.get(lotteryTicket.getLotteryPlayer());
                lotteryWinners.put(lotteryTicket.getLotteryPlayer(), currentPlayerCredits == null ?
                        currentTicketCredits : currentPlayerCredits + currentTicketCredits);
            }
        }
        return lotteryWinners;
    }

    private static final int NUMBER_FIELDS_IN_LINE = 4;
}

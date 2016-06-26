package sergii.makarenko.domain;

/**
 * Class for lottery tickets. Properties - LotteryPlayers and ticket number
 *
 * @author serg
 */
public class LotteryTicket {

    private LotteryPlayer lotteryPlayer;
    private String ticketNumber;


    public LotteryTicket(LotteryPlayer lotteryPlayer, String ticketNumber) {
        this.lotteryPlayer = lotteryPlayer;
        this.ticketNumber = ticketNumber;
    }

    public LotteryPlayer getLotteryPlayer() {
        return lotteryPlayer;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }
}

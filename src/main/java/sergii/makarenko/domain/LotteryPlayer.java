package sergii.makarenko.domain;

/**
 * Class for lottery players.
 *
 * @author serg
 */
public class LotteryPlayer implements Comparable<LotteryPlayer> {

    private String lastName;
    private String firstName;
    private String country;

    public LotteryPlayer(String lastName, String firstName, String country) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.country = country;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getCountry() {
        return country;
    }

    /**
     * @param o - another LotteryPlayer
     * @return - true - if lastName,firstName and country are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LotteryPlayer)) return false;

        LotteryPlayer that = (LotteryPlayer) o;

        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = lastName != null ? lastName.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }

    /**
     * Overriding toString method for result output
     *
     * @return string - lastName,firstName,Country
     */
    @Override
    public String toString() {
        return lastName + "," + firstName + "," + country;
    }

    /**
     * Overriding compareTo method for comparing LotteryPlayers in SortedMap.
     * Firstly compare by lastName, then by firstName and country.
     *
     * @param lotteryPlayer another LotteryPlayer
     * @return int result of comparing
     */
    @Override
    public int compareTo(LotteryPlayer lotteryPlayer) {
        int compareLastName = this.getLastName().compareTo(lotteryPlayer.getLastName());
        if (compareLastName == 0) {
            int compareFirstName = this.getFirstName().compareTo(lotteryPlayer.getFirstName());
            if (compareFirstName == 0) {
                return this.getCountry().compareTo(lotteryPlayer.getCountry());
            } else {
                return compareFirstName;
            }
        } else {
            return compareLastName;
        }
    }
}

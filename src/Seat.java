public class Seat implements Comparable<Seat> {
    private String seatNum;
    private boolean reserved;

    public Seat(char rowChar, int seatNo) {
        this.seatNum="%c%03d".formatted(rowChar,seatNo).toUpperCase();
    }

    public String getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(String seatNum) {
        this.seatNum = seatNum;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    @Override
    public String toString() {
        return seatNum;
    }

    @Override
    public int compareTo(Seat seat) {
        return seatNum.compareTo(seat.seatNum);
    }
}

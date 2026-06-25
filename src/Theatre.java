import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

public class Theatre {
    private String theatreName;
    private int seatsPerRow;
    //Collection for the Theatre Seat
    private NavigableSet<Seat> seats;

    public Theatre(String theatreName, int rows,int totalSeats) {
        this.theatreName = theatreName;
        this.seatsPerRow = totalSeats/rows;

        seats=new TreeSet<>();
        for (int i = 0; i < totalSeats; i++) {
            char rowChar = (char) (i/seatsPerRow+ (int)'A');//to get the first character of each row
            int seatInRow= i % seatsPerRow + 1;
            seats.add(new Seat(rowChar,seatInRow));
        }
    }
    public void printSeatMap(){
        String separatorLine = "-".repeat(90);
        System.out.printf("%1$s%n%2$s Seat Map%n%1$s%n",separatorLine,theatreName);

        int index=0;
        for (Seat seat:seats) {
            System.out.printf("%-8s%s",seat.getSeatNum() + (seat.isReserved()?"(●)":""),
                    ((index++ + 1)%seatsPerRow==0)?"\n":"");
        }
        System.out.println(separatorLine);

    }
    public String reserveSeat(char row,int seat){
        Seat requestedSeat = new Seat(row,seat);
//        Seat requested = seats.floor(requestedSeat);
        Seat requested = seats.ceiling(requestedSeat);


        if (requested==null||(!requested.getSeatNum().equals(requestedSeat.getSeatNum()))){
            System.out.print("--> No such seat: "+requestedSeat);
            System.out.printf(": Seat must be between %s and %s%n",seats.first().getSeatNum(),seats.last().getSeatNum());
        }else {
            if (!requested.isReserved()){
                requested.setReserved(true);
                return requested.getSeatNum();
            }else {
                System.out.println("Seat is already reserved");
            }
        }
        return null;
    }

    public boolean validate(int numOfSeats,char first,char last, int min,int max){
        boolean result=( min>0 || seatsPerRow >= numOfSeats|| (max-min+1) >= numOfSeats);
        result = result && seats.contains(new Seat(first,min));
        if (!result){
            System.out.printf("Invalid! %1$d seats between "+"%2$c[%3$d-%4$d]-%5$c[%3$d-%4$d] Try again",numOfSeats,first,min,max,last);
            System.out.printf(": Seat must be between %s and %s%n",seats.first().getSeatNum(),seats.last().getSeatNum());
        }
        return result;
    }
    public Set<Seat> reserveSeats(int numOfSeats, char minRow, char maxRow, int minSeat, int maxSeat){
        char lastValid=seats.last().getSeatNum().charAt(0);// to get the last alphabet of the last seat
        maxRow=(maxRow<lastValid)?maxRow:lastValid;
        if (!validate(numOfSeats,minRow,maxRow,minSeat,maxSeat)){
            return null;
        }
        NavigableSet<Seat> selected=null;
        for (char letter=minRow; letter<=maxRow;letter++){
            NavigableSet<Seat> contiguous = seats.subSet(new Seat(letter,minSeat),true,new Seat(letter,maxSeat),true);
            int index=0;
            Seat first=null;
            for (Seat currentSeat:contiguous) {
                if (currentSeat.isReserved()){
                    index=0;
                    continue;
                }
                first=(index==0)?currentSeat:first;
                if (++index==numOfSeats){
                    selected=contiguous.subSet(first,true,currentSeat,true);
                    break;
                }
            }
        if (selected!=null){
            break;
        }
        }
        Set<Seat> reservedSeats=null;
        if (selected!=null){
            selected.forEach(seat -> seat.setReserved(true));
            reservedSeats=new TreeSet<>(selected);
        }
        return reservedSeats;
    }
}

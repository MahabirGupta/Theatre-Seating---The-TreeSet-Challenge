import java.util.NavigableSet;
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
    public static void bookSeat(Theatre theatre, char row, int seatNo){
        String seat = theatre.reserveSeat(row,seatNo);
        if (seat!=null){
            System.out.println("Congratulations! Your reserve seat is "+seat);
            theatre.printSeatMap();
        }else {
            System.out.println("Sorry! Unable to reserve "+row+seatNo);
        }
    }
}

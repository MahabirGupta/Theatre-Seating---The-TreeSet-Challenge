import java.util.Set;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class BookingAgent {
    public static void main(String[] args) {
        int rows=10;
        int totalSeats=100;
        Theatre citySquareMall = new Theatre("City Square Mall",rows,totalSeats);
        citySquareMall.printSeatMap();
        bookSeat(citySquareMall,'A',3);
        bookSeat(citySquareMall,'A',3);
        bookSeat(citySquareMall,'B',1);
        bookSeat(citySquareMall,'B',11);
        bookSeat(citySquareMall,'M',1);
        System.out.println();
        bookSeats(citySquareMall,4,'B',3,10);
        bookSeats(citySquareMall,6,'B','C',3,10);
        bookSeats(citySquareMall,4,'B',1,10);
        bookSeats(citySquareMall,4,'B','C',1,10);
        bookSeats(citySquareMall,1,'B','C',1,10);
        bookSeats(citySquareMall,4,'M','Z',1,10);
        bookSeats(citySquareMall,10,'A','E',1,10);



    }

    private static void bookSeat(Theatre theatre, char row, int seatNo){
        String seat = theatre.reserveSeat(row,seatNo);
        if (seat!=null){
            System.out.println("Congratulations! Your reserve seat is "+seat);
            theatre.printSeatMap();
        }else {
            System.out.println("Sorry! Unable to reserve "+row+seatNo);
        }
    }

    private static void bookSeats(Theatre theatre,int tickets,char minRow,int minSeat,int maxSeat){
        bookSeats(theatre,tickets,minRow,minRow,minSeat,maxSeat);
    }
    private static void bookSeats(Theatre theatre,int tickets,char minRow,char maxRow,int minSeat,int maxSeat){
        Set<Seat> seats=theatre.reserveSeats(tickets,minRow,maxRow,minSeat,maxSeat);
        if (seats!=null){
            System.out.println("Congratulations! Your reserved seats are " + seats);
            theatre.printSeatMap();
        }else {
            System.out.println("Sorry! No matching contiguous seats in rows: "+minRow+" - "+maxRow);
        }
    }
}

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class BookingAgent {
    public static void main(String[] args) {
        int rows=10;
        int totalSeats=100;
        Theatre citySquareMall = new Theatre("City Square Mall",rows,totalSeats);
        citySquareMall.printSeatMap();
        citySquareMall.bookSeat(citySquareMall,'A',3);
        citySquareMall.bookSeat(citySquareMall,'A',3);
        citySquareMall.bookSeat(citySquareMall,'B',1);
        citySquareMall.bookSeat(citySquareMall,'B',11);
        citySquareMall.bookSeat(citySquareMall,'M',1);


//        citySquareMall.reserveSeat('A',8);
//        System.out.println();
//        citySquareMall.reserveSeat('B',1);
//        System.out.println();


    }
}

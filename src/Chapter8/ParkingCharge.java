package Chapter8;

import java.time.LocalDate;
import java.util.Scanner;

public class ParkingCharge {
    Scanner scanner = new Scanner(System.in);
    int[] clients = new int[10];
    int[] hoursYesterday = new int[10];
    double[] parkingFee = new double[10];
    double totalParkingFee = 0;
    LocalDate yesterday = LocalDate.now().minusDays(1);

    public static void main(String[] args) {

        ParkingCharge charge = new ParkingCharge();
        charge.parkedHoursCount();
        charge.calculateParkingCharges();
    }

    public void parkedHoursCount() {
        for (int i = 0; i < clients.length; i++) {
            clients[i] = i + 1;
            {
                System.out.println("Please enter the number of parking hours for " + yesterday + " for client # " + clients[i] + ": ");
                hoursYesterday[i] = scanner.nextInt();
                if (hoursYesterday[i] > 24) {
                    System.out.println("You can not park for more than 24 h! Please enter again a value equal or less than 24 h.");
                }
            }

        }
        scanner.close();
    }

    public void calculateParkingCharges() {
        for (int i = 0; i < clients.length; i++) {
            clients[i] = i + 1;
            if (hoursYesterday[i] < 24) {
                parkingFee[i] = applyCharges(hoursYesterday[i]);
                totalParkingFee += parkingFee[i];
            }
            System.out.println("Parking charge fee for client " + clients[i] + " = " + parkingFee[i] + " USD ");
        }
        System.out.println("Total parking charge fee for " + yesterday + " : " + totalParkingFee + " USD ");

    }

    public static double applyCharges(int hoursParked) {
        double parkingFee;

        if ((hoursParked >= 0) && (hoursParked <= 3)) {
            parkingFee = 3.0;
        } else if (hoursParked == 24) {
            System.out.println("Reached parking time limit, please free out the parking place");
            parkingFee = 10.0;
        } else {
            parkingFee = 3.0 + 0.5 * (hoursParked - 3);
        }

        return parkingFee;
    }

}

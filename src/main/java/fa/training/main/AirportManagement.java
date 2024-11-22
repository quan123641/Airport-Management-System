package fa.training.main;

import fa.training.services.AirplaneService;
import fa.training.services.AirportService;
import fa.training.services.FixedwingService;
import fa.training.services.HelicopterService;

import java.io.IOException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class AirportManagement {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        AirportService airportService = new AirportService();
        AirplaneService airplaneService = new AirplaneService();
        FixedwingService fixedwingService = new FixedwingService();
        HelicopterService helicopterService = new HelicopterService();

        airportService.readAirportsFile("airports.txt");
        airplaneService.readAirplanesFile("airplanes.txt");

        do {
            System.out.println("*************************************");
            System.out.println("------AIRPORT MANAGEMENT SYSTEM------");
            System.out.println("1. Airport Management");
            System.out.println("2. Fixedwing Management");
            System.out.println("3. Helicopter Management");
            System.out.println("4. Save and Exit");
            System.out.print("Enter your choice: ");
            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException ignored) {
            }
            switch (choice) {
                case 1:
                    airportService.airportMenu(scanner, airplaneService);
                    break;
                case 2:
                    fixedwingService.fixedwingMenu(scanner, airplaneService);
                    break;
                case 3:
                    helicopterService.helicopterMenu(scanner, airplaneService);
                    break;
                case 4:
                    airportService.writeAirportsFile("airports.txt");
                    airplaneService.writeAirplanesFile("airplanes.txt");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please enter a valid choice");
                    break;
            }
        } while (true);

    }
}
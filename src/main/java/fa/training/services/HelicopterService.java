package fa.training.services;

import fa.training.entities.Airplane;
import fa.training.entities.Helicopter;

import java.util.Scanner;

public class HelicopterService {
    public void helicopterMenu(Scanner scanner, AirplaneService airplaneService) {
        boolean exit = false;
        do {
            System.out.println("+++++++++++++++++++++++++++++++++");
            System.out.println("------HELICOPTER MANAGEMENT------");
            System.out.println("1. Add helicopter plane");
            System.out.println("2. Display all helicopter");
            System.out.println("3. Search helicopter");
            System.out.println("4. Back to main menu");
            System.out.print("Enter your choice: ");
            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException ignored) {
            }
            switch (choice) {
                case 1:
                    Airplane helicopter = new Helicopter();
                    helicopter.input(scanner);
                    airplaneService.add(helicopter);
                    System.out.println(airplaneService.add(helicopter)
                            ? "Helicopter added successfully"
                            : "Helicopter added failed");
                    break;
                case 2:
                    airplaneService.displayOfOneType("helicopter");
                    break;
                case 3:
                    System.out.print("Enter helicopter ID (RW#####): ");
                    String searchHelicopterId = scanner.nextLine();
                    Airplane searchHelicopter = airplaneService.searchById(searchHelicopterId);
                    System.out.println(searchHelicopter != null
                            ? searchHelicopter.toString()
                            : "Helicopter not found!");
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Please enter a valid choice");
                    break;
            }
        } while (!exit);
    }

}

package fa.training.services;

import fa.training.entities.Airplane;
import fa.training.entities.Fixedwing;

import java.util.Scanner;

public class FixedwingService {
    public void fixedwingMenu(Scanner scanner, AirplaneService airplaneService) {
        boolean exit = false;
        do {
            System.out.println("++++++++++++++++++++++++++++++++");
            System.out.println("------FIXEDWING MANAGEMENT------");
            System.out.println("1. Add fixedwing plane");
            System.out.println("2. Display all fixedwing");
            System.out.println("3. Search fixedwing");
            System.out.println("4. Change plane type");
            System.out.println("5. Back to main menu");
            System.out.print("Enter your choice: ");
            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException ignored) {
            }
            switch (choice) {
                case 1:
                    Airplane fixedwing = new Fixedwing();
                    fixedwing.input(scanner);
                    airplaneService.add(fixedwing);
                    System.out.println(airplaneService.add(fixedwing)
                            ? "Fixedwing added successfully"
                            : "Fixedwing added failed");
                    break;
                case 2:
                    airplaneService.displayOfOneType("fixedwing");
                    break;
                case 3:
                    System.out.print("Enter fixedwing ID (FW#####): ");
                    String fixedwingId = scanner.nextLine();
                    Airplane searchFixedwing = airplaneService.searchById(fixedwingId);
                    System.out.println(searchFixedwing != null
                            ? searchFixedwing.toString()
                            : "Fixedwing not found!");
                    break;
                case 4:
                    System.out.print("Enter fixedwing ID (FW#####): ");
                    String fixedwingIdChangeType = scanner.nextLine();
                    System.out.print("Enter new fixedwing type (CAG/LGR/PRV): ");
                    String newFixedwingType = scanner.nextLine();
                    System.out.println(airplaneService.changePlaneType(fixedwingIdChangeType, newFixedwingType)
                            ? "Change successfully!"
                            : "Change failed!");
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Please enter a valid choice");
                    break;
            }
        } while (!exit);
    }

}

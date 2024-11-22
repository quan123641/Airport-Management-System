package fa.training.services;

import fa.training.entities.Airport;
import fa.training.entities.Fixedwing;
import fa.training.entities.Helicopter;
import fa.training.utils.IOMethod;
import fa.training.utils.Validation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class AirportService {
    private Set<Airport> airports;

    public AirportService() {
        airports = new HashSet<>();
    }

    public AirportService(Set<Airport> airports) {
        this.airports = airports;
    }

    public Set<Airport> getAirports() {
        return airports;
    }

    //write airports file
    public boolean writeAirportsFile(String fileName) throws IOException {
        return IOMethod.writeFile(fileName, airports);
    }

    //read airports file
    public void readAirportsFile(String fileName) throws IOException, ClassNotFoundException {
        airports = IOMethod.readFile(fileName, Airport.class);
    }

    //add airport
    public boolean add(Airport airport) {
        return airports.add(airport);
    }

    //seach airport by id
    public Airport searchById(String id) {
        return airports.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    //check existed
    private boolean checkExisted(Airport airport) {
        return airport == null;
    }

    //add airplane to airport, return list of ids were added successful
    public List<String> addAirplane(AirplaneService airplaneService, String id, String type, String... airplaneIds) {
        Airport airport = searchById(id);
        if (checkExisted(airport)) {
            System.out.println("Airport not found!");
            return new ArrayList<>();
        }

        List<String> successId = new ArrayList<>();
        switch (type.toLowerCase()) {
            case "fixedwing":
                for (String airplaneId : airplaneIds) {
                    if (airplaneService.searchById(airplaneId) instanceof Fixedwing &&
                            airplaneService.searchById(airplaneId).getStatus().equals("Free")) {
                        //check min needed runway size
                        if (Validation.validateAddingFixedwingToAirport(airport.getRunwaySize(),
                                ((Fixedwing) airplaneService.searchById(airplaneId)).getMinNeededRunwaySize())) {
                            if (airport.getListOfFixedwingId().add(airplaneId)) {
                                airplaneService.searchById(airplaneId).setStatus("Parking");
                                successId.add(airplaneId);
                            }
                        }
                    }
                }
                break;
            case "helicopter":
                for (String airplaneId : airplaneIds) {
                    if (airplaneService.searchById(airplaneId) instanceof Helicopter &&
                            airplaneService.searchById(airplaneId).getStatus().equals("Free")) {
                        if (airport.getListOfHelicopterId().add(airplaneId)) {
                            airplaneService.searchById(airplaneId).setStatus("Parking");
                            successId.add(airplaneId);
                        }
                    }
                }
                break;
            default:
                System.out.println("Unknown type: " + type);
                break;
        }

        return successId;
    }

    //remove airplane, return list of ids were removed successfully
    public List<String> removeAirplane(AirplaneService airplaneService, String id, String type, String... airplaneIds) {
        Airport airport = searchById(id);
        if (checkExisted(airport)) {
            System.out.println("Airport not found!");
            return new ArrayList<>();
        }

        List<String> successId = new ArrayList<>();
        switch (type.toLowerCase()) {
            case "fixedwing":
                for (String airplaneId : airplaneIds) {
                    if (airport.getListOfFixedwingId().contains(airplaneId)) {
                        airport.getListOfFixedwingId().remove(airplaneId);
                        successId.add(airplaneId);
                    }
                }
                break;
            case "helicopter":
                for (String airplaneId : airplaneIds) {
                    if (airport.getListOfHelicopterId().contains(airplaneId)) {
                        airport.getListOfHelicopterId().remove(airplaneId);
                        successId.add(airplaneId);
                    }
                }
                break;
            default:
                System.out.println("Unknown type: " + type);
                break;
        }

        return successId;
    }

    public void airportMenu(Scanner scanner, AirplaneService airplaneService) {
        boolean exit = false;
        do {
            System.out.println("++++++++++++++++++++++++++++++");
            System.out.println("------AIRPORT MANAGEMENT------");
            System.out.println("1. Add airport");
            System.out.println("2. Display all airport");
            System.out.println("3. Display all status of one airport");
            System.out.println("4. Add airplane to airport");
            System.out.println("5. Remove airplane from airport");
            System.out.println("6. Back to main menu");
            System.out.print("Enter your choice: ");
            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException ignored) {
            }
            switch (choice) {
                case 1:
                    Airport airport = new Airport();
                    airport.input(scanner);
                    System.out.println(add(airport)
                            ? "Airport added successfully!"
                            : "Airport added failed!");
                    break;
                case 2:
                    List<Airport> airportList = new ArrayList<>(airports);
                    Collections.sort(airportList, Comparator.comparing(Airport::getId));
                    airportList.forEach(System.out::println);
                    break;
                case 3:
                    System.out.print("Input Airport Id (AP#####): ");
                    String id = scanner.nextLine();
                    Airport searchAirport = searchById(id);
                    System.out.println(searchAirport != null ? searchAirport.toString() : "Airport not found!");
                    break;
                case 4:
                    System.out.print("Input Airport Id (AP#####): ");
                    String idAdd = scanner.nextLine();
                    System.out.print("Choose airplane type to add (Fixedwing / Helicopter): ");
                    String typeAdd = scanner.nextLine();
                    System.out.printf("Enter id(s) of %s to add (id1, id2,...): ", typeAdd);
                    String idStringAdd = scanner.nextLine();
                    String[] idListAdd = idStringAdd.split(",\\s*");

                    List<String> successIdsAdded = addAirplane(airplaneService, idAdd, typeAdd, idListAdd);
                    System.out.println("List of ids were added successfully: " + successIdsAdded.toString());

                    break;
                case 5:
                    System.out.print("Input Airport Id (AP#####): ");
                    String idRemove = scanner.nextLine();
                    System.out.print("Choose airplane type to remove (Fixedwing / Helicopter): ");
                    String typeRemove = scanner.nextLine();
                    System.out.printf("Enter id(s) of %s to add (id1, id2,...): ", typeRemove);
                    String idStringRemove = scanner.nextLine();
                    String[] idListRemove = idStringRemove.split(",\\s*");

                    List<String> successIdsRemoved = removeAirplane(airplaneService, idRemove, typeRemove, idListRemove);
                    System.out.println("List of ids were removed successfully: " + successIdsRemoved.toString());

                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("Please enter a valid choice");
                    break;
            }
        } while (!exit);
    }
}

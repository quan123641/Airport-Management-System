package fa.training.entities;

import fa.training.utils.Validation;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Airport implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private double runwaySize;
    private int maxFixedwingParkingPlace;
    private Set<String> listOfFixedwingId = new HashSet<>();
    private int maxHelicopterParkingPlace;
    private Set<String> listOfHelicopterId = new HashSet<>();
    private Set<String> listOfAirplaneId;

    public Airport() {}

    public Airport(String id, String name, double runwaySize, int maxFixedwingParkingPlace, Set<String> listOfFixedwingId, int maxHelicopterParkingPlace, Set<String> listOfHelicopterId) {
        this.id = id;
        this.name = name;
        this.runwaySize = runwaySize;
        this.maxFixedwingParkingPlace = maxFixedwingParkingPlace;
        this.listOfFixedwingId = listOfFixedwingId;
        this.maxHelicopterParkingPlace = maxHelicopterParkingPlace;
        this.listOfHelicopterId = listOfHelicopterId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRunwaySize() {
        return runwaySize;
    }

    public void setRunwaySize(double runwaySize) {
        this.runwaySize = runwaySize;
    }

    public int getMaxFixedwingParkingPlace() {
        return maxFixedwingParkingPlace;
    }

    public void setMaxFixedwingParkingPlace(int maxFixedwingParkingPlace) {
        this.maxFixedwingParkingPlace = maxFixedwingParkingPlace;
    }

    public Set<String> getListOfFixedwingId() {
        return listOfFixedwingId;
    }

    public void setListOfFixedwingId(Set<String> listOfFixedwingId) {
        this.listOfFixedwingId = listOfFixedwingId;
    }

    public int getMaxHelicopterParkingPlace() {
        return maxHelicopterParkingPlace;
    }

    public void setMaxHelicopterParkingPlace(int maxHelicopterParkingPlace) {
        this.maxHelicopterParkingPlace = maxHelicopterParkingPlace;
    }

    public Set<String> getListOfHelicopterId() {
        return listOfHelicopterId;
    }

    public void setListOfHelicopterId(Set<String> listOfHelicopterId) {
        this.listOfHelicopterId = listOfHelicopterId;
    }

    public Set<String> getListOfAirplaneId() {
        return listOfAirplaneId;
    }

    public void setListOfAirplaneId(Set<String> listOfAirplaneId) {
        this.listOfAirplaneId.addAll(listOfFixedwingId);
        this.listOfAirplaneId.addAll(listOfHelicopterId);
    }

    public Airport input(Scanner scanner) {
        String id;
        do {
            System.out.print("Enter Airport id (AP#####): ");
            id = scanner.nextLine();
        } while (!Validation.validateId(id, Airport.class));
        this.setId(id);

        System.out.print("Enter Airport Name: ");
        String name = scanner.nextLine();
        this.setName(name);

        double runwaySize = 0;
        while (runwaySize <= 0) {
            try {
                System.out.print("Enter Runway Size (>0): ");
                runwaySize = Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid Runway Size");
            }
        }
        this.setRunwaySize(runwaySize);

        int maxFixedwingParkingPlace = 0;
        while (maxFixedwingParkingPlace <= 0) {
            try {
                System.out.print("Enter Max Fixedwing Parking Place (>0): ");
                maxFixedwingParkingPlace = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid Max Fixedwing Parking Place");
            }
        }
        this.setMaxFixedwingParkingPlace(maxFixedwingParkingPlace);

        int maxHelicopterParkingPlace = 0;
        while (maxHelicopterParkingPlace <= 0) {
            try {
                System.out.print("Enter Max Helicopter Parking Place (>0): ");
                maxHelicopterParkingPlace = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid Max Helicopter Parking Place");
            }
        }

        return this;
    }

    @Override
    public String toString() {
        return "Airport: ID=" + id + ", name=" + name + ", runway=" + runwaySize +
                ", maxFixedwingParking=" + maxFixedwingParkingPlace + ", listOfFixedwingAirplaneID=" + listOfFixedwingId +
                ", maxHelicopterParkingPlace=" + maxHelicopterParkingPlace + ", listOfHelicopterID=" + listOfHelicopterId;
    }
}

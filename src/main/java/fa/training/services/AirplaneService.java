package fa.training.services;

import fa.training.entities.Airplane;
import fa.training.entities.Airport;
import fa.training.entities.Fixedwing;
import fa.training.entities.Helicopter;
import fa.training.utils.IOMethod;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class AirplaneService {
    private Set<Airplane> airplanes;

    public AirplaneService() {
        airplanes = new HashSet<>();
    }

    public void writeAirplanesFile(String fileName) throws IOException {
        IOMethod.writeFile(fileName, airplanes);
    }

    public void readAirplanesFile(String fileName) throws IOException, ClassNotFoundException {
        airplanes = IOMethod.readFile(fileName, Airplane.class);
    }

    //add airplane
    public boolean add(Airplane airplane) {
        return airplanes.add(airplane);
    }

    //display airplane with its parking airport Id and name
    public void display(Set<Airplane> airplanes) {
        AirportService airportService = new AirportService();
        Set<Airport> airports = airportService.getAirports();

        airplanes.forEach(airplane -> {
            StringBuilder output = new StringBuilder(airplane.toString());
            airports.stream()
                    .filter(airport -> airport.getListOfAirplaneId().equals(airplane.getId()))
                    .forEach(airport -> output.append(", airportID=")
                            .append(airport.getId())
                            .append(", airportName=")
                            .append(airport.getName()));
            System.out.println(output);
        });
    }

    public void displayOfOneType(String type) {
        switch (type.toLowerCase()) {
            case "all":
                display(airplanes);
                break;
            case "fixedwing":
                Set<Airplane> fixedwings = new HashSet<>();
                airplanes.forEach(airplane -> {
                    if (airplane instanceof Fixedwing) {
                        fixedwings.add(airplane);
                    }
                });
                display(fixedwings);
                break;
            case "helicopter":
                Set<Airplane> helicopters = new HashSet<>();
                airplanes.forEach(airplane -> {
                    if (airplane instanceof Helicopter) {
                        helicopters.add(airplane);
                    }
                });
                display(helicopters);
                break;
        }

    }

    //search by Id
    public Airplane searchById(String id) {
        return airplanes.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    //change plane type
    public boolean changePlaneType(String id, String planeType) {
        Airplane airplane = searchById(id);
        if (airplane == null || airplane instanceof Helicopter) {
            System.out.println("Fixedwing not found");
            return false;
        }
        Fixedwing fixedwing = (Fixedwing) airplane;
        fixedwing.setPlaneType(planeType);

        return true;
    }

    //change min needed runway size
    public boolean changeMinNeededRunwaySize(String id, double minNeededRunwaySize) {
        Airplane airplane = searchById(id);
        if (airplane == null || airplane instanceof Helicopter) {
            System.out.println("Fixedwing not found");
            return false;
        }
        Fixedwing fixedwing = (Fixedwing) airplane;
        fixedwing.setMinNeededRunwaySize(minNeededRunwaySize);

        return true;
    }

}

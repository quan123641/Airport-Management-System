package fa.training.entities;

import fa.training.utils.Validation;

import java.util.Scanner;

public class Fixedwing extends Airplane{
    private String planeType;
    private double minNeededRunwaySize;

    public Fixedwing() {
    }

    public Fixedwing(String id, String model, double cruiseSpeed, double emptyWeight, double maxTakeoffWeight, String status, String planeType, double minNeededRunwaySize) {
        super(id, model, cruiseSpeed, emptyWeight, maxTakeoffWeight, status);
        this.planeType = planeType;
        this.minNeededRunwaySize = minNeededRunwaySize;
    }

    public String getPlaneType() {
        return planeType;
    }

    public void setPlaneType(String planeType) {
        this.planeType = planeType;
    }

    public double getMinNeededRunwaySize() {
        return minNeededRunwaySize;
    }

    public void setMinNeededRunwaySize(double minNeededRunwaySize) {
        this.minNeededRunwaySize = minNeededRunwaySize;
    }

    @Override
    public void flyMethod() {
        System.out.println("fixed wing");
    }

    @Override
    public Fixedwing input(Scanner scanner) {
        String id;
        do {
            System.out.print("Enter fixedwing id (FW#####): ");
            id = scanner.nextLine();
        } while (!Validation.validateId(id, Fixedwing.class));
        this.setId(id);

        super.input(scanner);

        double maxTakeOffWeight = 0;
        while (maxTakeOffWeight <= 0) {
            try {
                System.out.print("Enter max takeoff weight (>0): ");
                maxTakeOffWeight = Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid max takeoff weight!!!");
            }
        }
        this.setMaxTakeoffWeight(maxTakeOffWeight);

        String planType;
        do {
            System.out.print("Enter plane type (CAG/LGR/PRV): ");
            planType = scanner.nextLine();
        } while (!Validation.validateFixedwingPlaneType(planType));
        this.setPlaneType(planType);

        double minNeededRunwaySize = 0;
        while (minNeededRunwaySize <= 0) {
            try {
                System.out.print("Enter minimum runway size (>0): ");
                minNeededRunwaySize = Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid inimum runway size!!!");
            }
        }
        this.setMinNeededRunwaySize(minNeededRunwaySize);

        return this;
    }

    @Override
    public String toString() {
        return "Fixedwing: " + super.toString() + ", planType=" + planeType + ", minNeededRunwaySize=" + minNeededRunwaySize;

    }
}

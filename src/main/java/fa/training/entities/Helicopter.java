package fa.training.entities;

import fa.training.utils.Validation;

import java.util.Scanner;

public class Helicopter extends Airplane{
    private double range;

    public Helicopter() {
    }

    public Helicopter(String id, String model, double cruiseSpeed, double emptyWeight, double maxTakeoffWeight, String status, double range) {
        super(id, model, cruiseSpeed, emptyWeight, maxTakeoffWeight, status);
        this.range = range;
    }

    public double getRange() {
        return range;
    }

    public void setRange(double range) {
        this.range = range;
    }

    @Override
    public Helicopter input(Scanner scanner) {
        String id;
        do {
            System.out.print("Enter fixedwing id (RW#####): ");
            id = scanner.nextLine();
        } while (!Validation.validateId(id, Helicopter.class));
        this.setId(id);

        super.input(scanner);

        double maxTakeOffWeight = 0;
        do {
            System.out.print("Enter max takeoff weight (>0 & <= 1.5 times of empty weight): ");
            maxTakeOffWeight = Double.parseDouble(scanner.nextLine());
        } while (!Validation.validateMaxTakeOffWeightOfHelicopter(getEmptyWeight(), maxTakeOffWeight));
        this.setMaxTakeoffWeight(maxTakeOffWeight);

        double range = 0;
        while (range <= 0) {
            try {
                System.out.print("Enter range (>0): ");
                range = Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid range!!!");
            }
        }
        this.setRange(range);

        return this;
    }

    @Override
    public void flyMethod() {
        System.out.println("rotated wing");
    }

    @Override
    public String toString() {
        return "Helicopter: " + super.toString() + ", range=" + range;
    }

}

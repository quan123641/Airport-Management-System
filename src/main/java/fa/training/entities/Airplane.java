package fa.training.entities;

import fa.training.utils.Validation;

import java.io.Serializable;
import java.util.Objects;
import java.util.Scanner;

public class Airplane implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String model;
    private double cruiseSpeed;
    private double emptyWeight;
    private double maxTakeoffWeight;
    private String status;

    public Airplane() {
    }

    public Airplane(String id, String model, double cruiseSpeed, double emptyWeight, double maxTakeoffWeight, String status) {
        this.id = id;
        this.model = model;
        this.cruiseSpeed = cruiseSpeed;
        this.emptyWeight = emptyWeight;
        this.maxTakeoffWeight = maxTakeoffWeight;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getCruiseSpeed() {
        return cruiseSpeed;
    }

    public void setCruiseSpeed(double cruiseSpeed) {
        this.cruiseSpeed = cruiseSpeed;
    }

    public double getEmptyWeight() {
        return emptyWeight;
    }

    public void setEmptyWeight(double emptyWeight) {
        this.emptyWeight = emptyWeight;
    }

    public double getMaxTakeoffWeight() {
        return maxTakeoffWeight;
    }

    public void setMaxTakeoffWeight(double maxTakeoffWeight) {
        this.maxTakeoffWeight = maxTakeoffWeight;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Airplane input(Scanner scanner) {
        System.out.print("Enter model (max 40 characters): ");
        do {
            String model = scanner.nextLine();
            if (Validation.validateModel(model)) {
                this.model = model;
                break;
            }
        } while (true);

        double cruiseSpeed = 0;
        while (cruiseSpeed <= 0) {
            try {
                System.out.print("Enter cruise speed (>0): ");
                cruiseSpeed = Double.parseDouble(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid cruise speed!!!");
            }
        }
        this.cruiseSpeed = cruiseSpeed;

        double emptyWeight = 0;
        while (emptyWeight <= 0) {
            try {
                System.out.print("Enter empty weight (>0): ");
                emptyWeight = Double.parseDouble(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid empty weight!!!");
            }
        }
        this.emptyWeight = emptyWeight;

        this.status = "Free";
        return this;
    }

    public void flyMethod() {
        System.out.println("Airplane");
    }

    @Override
    public String toString() {
        return "id=" + id + ", model=" + model + ", cruiseSpeed=" + cruiseSpeed +
                ", emptyWeight=" + emptyWeight + ", maxTakeOffWeight=" + maxTakeoffWeight + ", status=" + status;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Airplane airplane = (Airplane) object;
        return Objects.equals(id, airplane.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

package fa.training.utils;

import fa.training.entities.Airport;
import fa.training.entities.Fixedwing;
import fa.training.entities.Helicopter;

import java.util.List;

public class Validation {
    public static boolean validateModel(String model) {
        return model.matches(".{1,39}");
    }

    public static <T> boolean validateId(String id, Class<T> clazz) {
        if (clazz == Fixedwing.class) {
            return id.matches("FW\\d{5}");
        }
        if (clazz == Helicopter.class) {
            return id.matches("RW\\d{5}");
        }
        if (clazz == Airport.class) {
            return id.matches("AP\\d{5}");
        }
        return false;
    }

    public static boolean validateFixedwingPlaneType(String planeType) {
        return "CAG".equalsIgnoreCase(planeType) || "LGR".equalsIgnoreCase(planeType) || "PRV".equalsIgnoreCase(planeType);
    }

    public static boolean validateMaxTakeOffWeightOfHelicopter(double emptyWeight, double maxTakeOffWeight) {
        return maxTakeOffWeight <= (emptyWeight * 1.5);
    }

    public static boolean validateAddingFixedwingToAirport(double runwaySize, double minNeededRunwaySize) {
        return minNeededRunwaySize <= runwaySize;
    }
}

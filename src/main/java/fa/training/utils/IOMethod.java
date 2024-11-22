package fa.training.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IOMethod {
    public static <T extends Serializable> boolean writeFile(String fileName, Set<T> list) throws IOException {
        String dirPath = "src\\main\\resources\\data";
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File file = new File(dir, fileName);
        try (
                FileOutputStream fos = new FileOutputStream(file);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
        ) {
            oos.writeObject(list);
        }
        return true;
    }

    public static <T> Set<T> readFile(String fileName, Class<T> clazz) throws IOException, ClassNotFoundException {
        String dirPath = "src\\main\\resources\\data";
        File file = new File(dirPath, fileName);
        if (!file.exists() || file.length() == 0) {
            return new HashSet<>();
        }
        try (
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
        ) {
            return (Set<T>) ois.readObject();
        }
    }
}

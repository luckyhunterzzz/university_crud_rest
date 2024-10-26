package example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriteTest {
    public static void main(String[] args) {
        String directoryPath = "logs";
        String filePath = directoryPath + "/test.log";

        // Создаем папку logs, если она не существует
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            if (directory.mkdirs()) {
                System.out.println("Directory created: " + directoryPath);
            } else {
                System.out.println("Failed to create directory: " + directoryPath);
                return;
            }
        }

        // Пытаемся создать файл и записать в него
        File file = new File(filePath);
        try (FileWriter writer = new FileWriter(file)) {
            writer.write("This is a test log entry.");
            System.out.println("File created and written to: " + filePath);
        } catch (IOException e) {
            System.out.println("Failed to write to file: " + filePath);
            e.printStackTrace();
        }
    }
}
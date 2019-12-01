import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            String filePath;
            System.out.println("Wprowadź pełną ścieżkę pliku: ");
            try (Scanner reader = new Scanner(System.in)) {
                filePath = reader.nextLine();
            }
            File file = new File(filePath);
            if (!file.exists()) throw new Exception("Nie znaleziono pliku: " + file.getName());
            if (!file.getName().endsWith(".c"))
                throw new Exception("Błędne rozszerzenie pliku: " + file.getName());
            CAnalyser test = new CAnalyser();
            test.analyse(file);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

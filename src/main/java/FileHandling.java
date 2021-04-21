import java.io.*;
import java.util.Scanner;

public class FileHandling {

    public File createLoginFile() {
        // Creates the file with the required login details
        File loginFile = new File("login-details.txt");
        try {
            if (!loginFile.exists()) {
                FileWriter fileWriter = new FileWriter(loginFile);
                fileWriter.write("stock" + "\n" + "Pa$$w0rd");
                fileWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loginFile;
    }

    public boolean validateLogin(String username, String password) throws FileNotFoundException {
        // Determines whether the username and password match the file content
            Scanner scanner = new Scanner(createLoginFile());
            String un = scanner.nextLine();
            String pwd = scanner.nextLine();
        return un.equals(username) && pwd.equals(password);
    }
}
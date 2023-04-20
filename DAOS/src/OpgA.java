import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class OpgA {
    //6. Denne opgave består af tre delopgaver.
    /*Programmerne skal indeholde adgang til databasen via JDBC og en meget simpel brugergrænseflade
    (denne brugergrænseflade vil dog ikke være genstand for bedømmelse – det tilrådes meget, meget stærkt at lave en konsol-applikation).
    Programmerne skal forklares i rapporten og medtages i fuld udstrækning i bilag i pdf-format (hvid baggrund)
    */
        /*
    a. Programmet skal lave en oprettelse af en ny destillering. Programmet skal svare med forståelige og præcise fejlreaktioner,
    hvis oprettelsen af den ene eller anden grund ikke kan lade sig gøre. Dato behandles i java som en string.
     */
    public static void main(String[] args) {
        try {
            // Connection & Prepare Statement
            Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=DAOS2semProjekt;user=sa;password=reallyStrongPwd123");
            String sql = "INSERT INTO Destillering VALUES(?,?,?,?,?,?,?)";
            java.sql.PreparedStatement statement = connection.prepareStatement(sql);

            // Spørg efter input.
            System.out.println("Indsæt ny destillering");
            BufferedReader inLine = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Indsæt newMakenummer: ");
            String newMakeNummer = inLine.readLine();
            System.out.print("Indtast liter: ");
            String liter = inLine.readLine();
            System.out.print("Indtast alkoholProcent: ");
            String alkoholProcent = inLine.readLine();
            int alkoholProcentInt = Integer.parseInt(alkoholProcent.trim());
            if (alkoholProcentInt < 0 || alkoholProcentInt > 100) {
                throw new SQLException("Fejl: Alkoholprocenten skal være mellem 0 og 100.", "50000");
            }
            System.out.print("Indtast kornSort: ");
            String kornSort = inLine.readLine();
            System.out.print("Indtast rygeMateriale: ");
            String rygeMateriale = inLine.readLine();
            System.out.print("Indtast dato: ");
            String dato = inLine.readLine();
            LocalDate.parse(dato);
            System.out.println("Indtast medarbejderId: ");
            String medarbejderId = inLine.readLine();

            // Execute Statement
            statement.setInt(1, Integer.parseInt(newMakeNummer.trim()));
            statement.setInt(2, Integer.parseInt(liter.trim()));
            statement.setInt(3, alkoholProcentInt);
            statement.setString(4, kornSort.trim());
            statement.setString(5, rygeMateriale.trim());
            statement.setString(6, dato.trim());
            statement.setInt(7, Integer.parseInt(medarbejderId.trim()));
            statement.executeUpdate();
            System.out.println("Destillering indsat");

            // Luk JDBC-objekter
            statement.close();
            connection.close();
        } catch (SQLException e) {
            if (e.getErrorCode() == 50000) {
                System.out.println(e.getMessage());
            } else if (e.getErrorCode() == 547) {
                System.out.println("Fejl: 547 " + e.getMessage());
            } else {
                System.out.println("Fejl: " + e.getMessage());
            }
        } catch (IOException e) {
            System.out.println("Fejl:  " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Fejl: Forkert indtastet input, det skal være et tal " + e.getMessage());
        } catch (DateTimeParseException e) {
            System.out.println("Fejl: " + e.getMessage() + ". Der er en fejl i datoen du har indtastet.");
        }
    }
}

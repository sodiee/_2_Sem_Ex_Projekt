import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class OpgB {
    //6. Denne opgave består af tre delopgaver.
    /*Programmerne skal indeholde adgang til databasen via JDBC og en meget simpel brugergrænseflade
    (denne brugergrænseflade vil dog ikke være genstand for bedømmelse – det tilrådes meget, meget stærkt at lave en konsol-applikation).
    Programmerne skal forklares i rapporten og medtages i fuld udstrækning i bilag i pdf-format (hvid baggrund)
    */
    /*
    b. Programmet skal vise det samlede antal destilleringer og samlet antal liter destilleret væske en givet medarbejder har lavet.
     */
    public static void main(String[] args) {
        try {
            // Opret JDBC-forbindelse til databasen
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=DAOS2semProjekt;user=sa;password=reallyStrongPwd123");

            // Udfør SELECT-forespørgsel for at hente data og spørg efter input.
            BufferedReader inLine = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Hvilket medarbejderId vil du søge efter?");
            String ønsketMedarbejderId = inLine.readLine();
            String sql = "SELECT M.medarbejderId, COUNT(Dling.newMakenummer) AS antalDestilleringer, SUM(Dling.liter) AS samletLiter FROM Medarbejder M INNER JOIN Destillering Dling ON Dling.medarbejderId = M.medarbejderId WHERE M.medarbejderId = " + ønsketMedarbejderId +  "GROUP BY M.medarbejderId";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // Vis resultaterne på skærmen
            while (rs.next()) {
                int medarbejderId = rs.getInt("medarbejderId");
                int antalDestilleringer = rs.getInt("antalDestilleringer");
                double samletLiter = rs.getDouble("samletLiter");
                System.out.println("MedarbejderId nummer " + medarbejderId + " har lavet " + antalDestilleringer + " destilleringer og destilleret " + samletLiter + " liter væske.");
            }

            // Luk JDBC-objekter
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            if (e.getErrorCode() == 50000) {
                System.out.println(e.getMessage());
            } else if (e.getErrorCode() == 547) {
                System.out.println("Fejl: 547 " + e.getMessage());
            }
        } catch (IOException e) {
            System.out.println("Fejl:  " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Fejl: Forkert indtastet input, det skal være et tal " + e.getMessage());
        }
    }
}

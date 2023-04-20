import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Opg1 {
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
                System.out.println("Indsæt nyt fad");
                BufferedReader inLine = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Indsæt leverandør ");
                String leverandør = inLine.readLine();
                System.out.print("Indtast tidligere indhold: ");
                String tidligereIndhold = inLine.readLine();
                System.out.print("Indtast størrelse på fadet i liter: ");
                String størrelseLiter = inLine.readLine();
                System.out.print("Indtast allerede opfyldt liter: ");
                String opfyldtLiter = inLine.readLine();
                System.out.print("Indtast alder på fadet: ");
                String rygeMateriale = inLine.readLine();
                System.out.print("Indtast dato: ");
                String dato = inLine.readLine();

                // Execute Statement
                statement.setInt(1, Integer.parseInt(leverandør.trim()));
                statement.setInt(2, Integer.parseInt(tidligereIndhold.trim()));
                statement.setInt(3, Integer.parseInt(størrelseLiter.trim()));
                statement.setString(4, opfyldtLiter.trim());
                statement.setString(5, rygeMateriale.trim());
                statement.setString(6, dato.trim());
                statement.executeUpdate();
                System.out.println("Destillering indsat");

                // Luk JDBC-objekter
                statement.close();
                connection.close();
            } catch (SQLException e) {
                if (e.getErrorCode() == 50000) {
                    System.out.println(e.getMessage());
                }
            } catch (Exception e) {
                System.out.println("Fejl:  " + e.getMessage());
            }
        }
    }

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Opg3 {
    //6. Denne opgave består af tre delopgaver.
    /*Programmerne skal indeholde adgang til databasen via JDBC og en meget simpel brugergrænseflade
    (denne brugergrænseflade vil dog ikke være genstand for bedømmelse – det tilrådes meget, meget stærkt at lave en konsol-applikation).
    Programmerne skal forklares i rapporten og medtages i fuld udstrækning i bilag i pdf-format (hvid baggrund)
    */
    /*
    c. Programmet skal kunne lægge et fad på en hylde. Der skal kontrolleres at der er plads til fadet på hylden.
    Hvis der ikke er plads på hylden, skal brugerne have besked om dette.
     */
    public static void main(String[] args) {
        try {
            // Connection & Prepare Statement
            Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=DAOS2semProjekt;user=sa;password=reallyStrongPwd123");
            String sql = "INSERT INTO Fad VALUES(?,?,?,?,?,?)";
            java.sql.PreparedStatement statement = connection.prepareStatement(sql);

            // Spørg efter input.
            System.out.println("Indsæt nyt fad");
            BufferedReader inLine = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Indsæt leverandør: ");
            String leverandør = inLine.readLine();
            System.out.print("Indtast tidligere indhold: ");
            String tidligereIndhold = inLine.readLine();
            System.out.print("Indtast størrelse på fadet i liter: ");
            String størrelseLiter = inLine.readLine();
            System.out.print("Indtast allerede opfyldt liter: ");
            String opfyldtLiter = inLine.readLine();
            System.out.print("Indtast alder på fadet: ");
            String alder = inLine.readLine();
            System.out.print("Indtast hyldenummer: ");
            String hylde = inLine.readLine();
            System.out.print("Indtast status på fad(Skal være 'Tomt', 'Destillat' eller 'Whisky': ");
            String status = inLine.readLine();

            // Execute Statement
            statement.setString(1, leverandør.trim());
            statement.setString(2, tidligereIndhold.trim());
            statement.setInt(3, Integer.parseInt(størrelseLiter.trim()));
            statement.setInt(4, Integer.parseInt(opfyldtLiter.trim()));
            statement.setInt(5, Integer.parseInt(alder.trim()));
            statement.setInt(6, Integer.parseInt(hylde.trim()));
            statement.setString(7, status.trim());
            ResultSet rs = statement.executeQuery(sql);
            int hyldeIndsat = rs.getInt("hyldeNr");
            int reolIndsat = rs.getInt("reolNr");
            System.out.println("Fad indsat på reol " + reolIndsat + "hylde " + hyldeIndsat + ".");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

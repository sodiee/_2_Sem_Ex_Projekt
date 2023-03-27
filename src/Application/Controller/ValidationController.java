package Application.Controller;

import javafx.scene.control.Alert;

/**
 * Brugt til at sørge for at tal og strenge ikke indeholder noget som ikke er tilladt
 */
public class ValidationController {

    public static Boolean validateString(String stringValue, String fieldName, Boolean allowSpace, Boolean allowSymbol){

        //Hvis strengen er tom
        if(stringValue.length() == 0){
            Alert alertIntEmpty = new Alert(Alert.AlertType.ERROR);
            alertIntEmpty.setTitle("Tomt feldt");
            alertIntEmpty.setHeaderText(fieldName + " er tom!");
            alertIntEmpty.showAndWait();
            return false;
        }
        else{

            char[] chars = stringValue.toCharArray();
            for(char c : chars){
                //Hvis strengen indeholder et tal
                if(Character.isDigit(c)){
                    Alert alertIntEmpty = new Alert(Alert.AlertType.ERROR);
                    alertIntEmpty.setTitle("Ikke Gyldigt Input");
                    alertIntEmpty.setHeaderText(fieldName + " må kun indeholde bogstaver");
                    alertIntEmpty.showAndWait();
                    return false;
                }
                //Hvis strengen indeholder et mellemrum
                if(c == ' ' && allowSpace == false){
                    Alert alertIntEmpty = new Alert(Alert.AlertType.ERROR);
                    alertIntEmpty.setTitle("Ikke Gyldigt Input");
                    alertIntEmpty.setHeaderText(fieldName + " må ikke indeholde mellemrum");
                    alertIntEmpty.showAndWait();
                    return false;
                }
                //Hvis strengen indeholder et symbol/tegn
                //TODO: Virker ikke
                /** if(!stringValue.matches("[^A-Za-z0-9 ]") && allowSymbol == false){
                    Alert alertIntEmpty = new Alert(Alert.AlertType.ERROR);
                    alertIntEmpty.setTitle("Ikke Gyldigt Input");
                    alertIntEmpty.setHeaderText(fieldName + " må ikke indeholde symboler/tegn");
                    alertIntEmpty.showAndWait();
                    return false;
                }**/
            }
        }
        return true;
    }

    public static Boolean validateInt(String stringValue, String fieldName){

        //Hvis tallet er tomt
        if(stringValue.length() == 0){
            Alert alertIntEmpty = new Alert(Alert.AlertType.ERROR);
            alertIntEmpty.setTitle("Empty Field");
            alertIntEmpty.setHeaderText(fieldName + " is Empty!");
            alertIntEmpty.showAndWait();
            return false;
        }
        else{
            int value = -1;
            try{
                value = Integer.parseInt(stringValue.trim());
            }
            //Hvis det ikke er et tal
            catch (NumberFormatException exception){
                Alert alertIntNotNr = new Alert(Alert.AlertType.ERROR);
                alertIntNotNr.setTitle("Not a number");
                alertIntNotNr.setHeaderText(fieldName + " is not a number");
                alertIntNotNr.showAndWait();
                return false;
            }
            //Hvis det er et negativt tal
            if(value < 0){
                Alert alertIntNegative = new Alert(Alert.AlertType.ERROR);
                alertIntNegative.setTitle("Negative Number");
                alertIntNegative.setHeaderText(fieldName + " cannot be a negative number");
                alertIntNegative.showAndWait();
                return false;
            }
            else{

            }

        }
        return true;
    }

    public static Boolean validateDouble(String stringValue, String fieldName){

        if(stringValue.length() == 0){
            Alert alertIntEmpty = new Alert(Alert.AlertType.ERROR);
            alertIntEmpty.setTitle("Empty Field");
            alertIntEmpty.setHeaderText(fieldName + " is Empty!");
            alertIntEmpty.showAndWait();
            return false;
        }
        else{
            double value = -1;
            try{
                value = Double.parseDouble(stringValue.trim());
            }
            catch (NumberFormatException exception){
                Alert alertDoubleNotNr = new Alert(Alert.AlertType.ERROR);
                alertDoubleNotNr.setTitle("Not a number");
                alertDoubleNotNr.setHeaderText(fieldName + " is not a number");
                alertDoubleNotNr.showAndWait();
                return false;
            }
            if(value < 0){
                Alert alertDoubleNegative = new Alert(Alert.AlertType.ERROR);
                alertDoubleNegative.setTitle("Negative Number");
                alertDoubleNegative.setHeaderText(fieldName + " cannot be a negative number");
                alertDoubleNegative.showAndWait();
                return false;
            }
            else{

            }

        }
        return true;
    }
}


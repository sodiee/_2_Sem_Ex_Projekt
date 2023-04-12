package Application.Controller;

import javafx.scene.control.Alert;

/**
 * Brugt til at sørge for at tal og strenge ikke indeholder noget som ikke er tilladt
 */
public class ValidationController {

    /**
     * Tester en Streng for gyldigghed (ikke tom, ikke tal, etc)
     * @param stringValue strengen der skal testes
     * @param fieldName indentiteten af det som testes (fx navn, bil, etc)
     * @param allowSpace om strenge må indeholde mellemrum
     * @param allowSymbol om strengen må indeholde særtegn såsom @$€^
     */
    public static void validateStringException(String stringValue, String fieldName, Boolean allowSpace, Boolean allowSymbol) {
        //Hvis strengen er tom
        if (stringValue.length() == 0) {
            throw new IllegalArgumentException("Ugyldigt input. Der er et tomt felt");
        } else {
            char[] chars = stringValue.toCharArray();
            for (char c : chars) {
                //Hvis strengen indeholder et tal
                if (Character.isDigit(c)) {
                    throw new IllegalArgumentException("Ugyldigt input. Der må ikke indsættes tal.");
                } //Hvis strengen indeholder et mellemrum
                if (c == ' ' && allowSpace == false) {
                    throw new IllegalArgumentException("Ugyldigt inpit. Der må ikke indsættes mellemrum");
                }
                //Hvis strengen indeholder et symbol/tegn
                if (!stringValue.matches("^[A-Za-z0-9 ]*$") && allowSymbol == false) {
                    throw new IllegalArgumentException("Ugyldig input Der må ikke indsættes symbol/tegn.");
                }
            }
        }

    }

    /**
     * Tester en Streng for gylddighed som Integer(ikke tom, ikke bogstaver, etc)
     * @param stringValue strengen som testes
     * @param fieldName feltets navn såsom alder, vejnr, etc
     * @throws IllegalArgumentException
     */
    public static void validateIntException(String stringValue, String fieldName) throws IllegalArgumentException {
        //Hvis tallet er tomt
        if (stringValue.length() == 0) {
            throw new IllegalArgumentException("Ugyldigt input. Tekstfeltet er tomt.");
        } else {
            int value = -1;
            if (stringValue.matches("-?\\d+")) {
                value = Integer.parseInt(stringValue.trim());
            } else {
                // Hvis inputtet ikke er et tal
                throw new NumberFormatException("Ugyldigt input. Der må kun indsættes tal.");
            }
            //Hvis det er et negativt tal
            if (value < 0) {
                throw new IllegalArgumentException("Ugyldigt input. Der må ikke indsættes negative tal.");
            }
        }
    }

    /**
     * Tester en Streng for gylddighed som Double(ikke tom, ikke bogstaver, etc)
     * @param stringValue strengen som testes
     * @param fieldName feltets navn såsom alder, vejnr, etc
     * @throws IllegalArgumentException
     */
    public static void validateDoubleException(String stringValue, String fieldName){
        //Hvis tallet er tomt
        if(stringValue.length() == 0){
            throw new IllegalArgumentException("Ugyldigt input. Tekstfeltet er tomt.");
        }
        else{
            double value = -1;
            if (stringValue.matches("-?\\d+")) {
                value = Integer.parseInt(stringValue.trim());
            } else {
                // Hvis inputtet ikke er et tal
                throw new NumberFormatException("Ugyldigt input. Der må kun indsættes tal.");
            }
            if(value < 0){
                throw new IllegalArgumentException("Ugyldigt input. Der må ikke indsættes negative tal.");
            }
        }
    }
}


package Application.Controller;

import Application.Model.*;
import Storage.Storage;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    @Test
    void TC1_createFad() {

        //Arrange
        Lager lager = Controller.createLager(2, 3, 3, "Sønderhøj 30");
        Reol reol = Controller.createReol(lager, 3, 3);
        lager.addReol(reol);
        Hyldeplads hyldeplads = reol.getHylder().get(1).getHyldepladser().get(1);

        //Act
        Fad fad = Controller.createFad("Sherry distilleri, Lissabon", "Sherry", 1, 130, hyldeplads);

        //Assert
        Boolean actualBoolean = Storage.getFadArrayList().contains(fad);
        assertTrue(actualBoolean);
    }

    @Test
    void TC2_createFad() {
        //Arrange
        Lager lager = Controller.createLager(2, 3, 3, "Sønderhøj 30");
        Reol reol = Controller.createReol(lager, 3, 3);
        lager.addReol(reol);
        Hyldeplads hyldeplads = reol.getHylder().get(1).getHyldepladser().get(1);
        //Act
        //Assert
        Exception forventet = assertThrows(RuntimeException.class, () -> {
            Controller.createFad("Sherry distilleri, Lissabon", "Sherry", 1, 130, null);
        });
        assertEquals(forventet.getMessage(), "Hyldeplads må ikke være null");
    }

    @Test
    void TC3_createLager() {

        //Arrange

        //Act
        Lager lager = Controller.createLager(2, 3, 3, "Sønderhøj 30");

        //Assert
        Boolean actualBoolean = Storage.getLagerArrayList().contains(lager);
        assertTrue(actualBoolean);
    }

    @Test
    void TC4_createDestillat() {
        //Arrange

        //Act
        Destillat destillat = Controller.createDestillat("Bingo Dorthe", 100, 20, LocalDate.of(2001, 01, 01), "Byg", "Whisky lavet på byg, whiskyen er rød", null);

        //Assert
        Boolean actualBoolean = Storage.getDestillatArrayList().contains(destillat);
        assertTrue(actualBoolean);
    }

    @Test
    void TC5_createDestillat() {
        //Arrange

        //Act
        //Assert
        Exception forventet = assertThrows(IllegalArgumentException.class, () -> {
            Controller.createDestillat("Bingo Dorthe", 100, 20, LocalDate.now().plusDays(2), "Byg", "Whisky lavet på byg, whiskyen er rød", null);
        });
        assertEquals(forventet.getMessage(), "Ugyldig data");
    }

    @Test
    void TC6_createDestillat() {
        //Arrange

        //Act
        //Assert
        Exception forventet = assertThrows(RuntimeException.class, () -> {
            Controller.createDestillat("Bingo Dorthe", 0, 20, LocalDate.of(2001, 01, 01), "Byg", "Whisky lavet på byg, whiskyen er rød", null);
        });
        assertEquals(forventet.getMessage(), "Ugyldig data");
    }

    @Test
    void TC7_createDestillat() {
        //Arrange

        //Act
        //Assert
        Exception forventet = assertThrows(RuntimeException.class, () -> {
            Controller.createDestillat("Bingo Dorthe", -1, 20, LocalDate.of(2001, 01, 01), "Byg", "Whisky lavet på byg, whiskyen er rød", null);
        });
        assertEquals(forventet.getMessage(), "Ugyldig data");
    }

    @Test
    void TC8_createDestillat() {
        //Arrange

        //Act
        Destillat destillat = Controller.createDestillat("Bingo Dorthe", 100, 0, LocalDate.of(2001, 01, 01), "Byg", "Whisky lavet på byg, whiskyen er rød", null);

        //Assert
        Boolean actualBoolean = Storage.getDestillatArrayList().contains(destillat);
        assertTrue(actualBoolean);
    }

    @Test
    void TC9_createDestillat() {
        //Arrange
        Lager lager = new Lager(2, 3, 3, "Sønderhøj 30");
        Reol reol = Controller.createReol(lager, 3, 3);
        lager.addReol(reol);
        Hyldeplads hyldeplads = reol.getHylder().get(1).getHyldepladser().get(1);
        Fad fad = Controller.createFad("Sherry distilleri, Lissabon", "Sherry", 1, 130, hyldeplads);

        //Act
        //Assert
        Exception forventet = assertThrows(RuntimeException.class, () -> {
            Controller.createDestillat("Bingo Dorthe", 100, -1, LocalDate.of(2001, 01, 01), "Byg", "Whisky lavet på byg, whiskyen er rød", null);
        });
        assertEquals(forventet.getMessage(), "Ugyldig data");
    }

    @Test
    void TC21_createWhisky() {
        //Arrange
        Lager lager = Controller.createLager(2, 3, 3, "Sønderhøj 30");
        Reol reol = Controller.createReol(lager, 3, 3);
        lager.addReol(reol);
        Hyldeplads hyldeplads = reol.getHylder().get(1).getHyldepladser().get(1);
        Fad fad = Controller.createFad("Sherry distilleri, Lissabon", "Sherry", 1, 130, hyldeplads);
        Destillat destillat = Controller.createDestillat("Bingo Dorthe", 100, 0, LocalDate.of(2001, 01, 01), "Byg", "Whisky lavet på byg, whiskyen er rød", null);
        destillat.hældDestillatPåfad(fad);
        String navn = "Navn";
        //Act
        Controller.convertToWhisky(fad, navn);
        Whisky whisky = (Whisky) fad.getDestillat();

        //Assert
        Boolean actualBoolean = Storage.getWhiskyArrayList().contains(whisky);
        assertTrue(actualBoolean);
    }

    @Test
    void TC22_createWhisky() {
        //Arrange
        Lager lager = Controller.createLager(2, 3, 3, "Sønderhøj 30");
        Reol reol = Controller.createReol(lager, 3, 3);
        lager.addReol(reol);
        Hyldeplads hyldeplads = reol.getHylder().get(1).getHyldepladser().get(1);
        Fad fad = Controller.createFad("Sherry distilleri, Lissabon", "Sherry", 1, 130, hyldeplads);
        Destillat destillat = null;
        String navn = "Navn";

        //Act
        //Assert

        Exception forventet = assertThrows(NullPointerException.class, () -> {
            Controller.convertToWhisky(fad, navn);
        });
        String nullPointerExceptionMsg = "Der er ikke knyttet et destillat til dette fad, så konvertering til whisky kan ikke lade sig gøre.";

        assertEquals(forventet.getMessage(), nullPointerExceptionMsg);
    }

    @Test
    void TC23_createWhiskyPåFlaske() {
        //Arrange
        Lager lager = Controller.createLager(2, 3, 3, "Sønderhøj 30");
        Reol reol = Controller.createReol(lager, 3, 3);
        lager.addReol(reol);
        Hyldeplads hyldeplads = reol.getHylder().get(1).getHyldepladser().get(1);
        Fad fad = Controller.createFad("Sherry distilleri, Lissabon", "Sherry", 1, 130, hyldeplads);
        Destillat destillat = Controller.createDestillat("Bingo Dorthe", 100, 0, LocalDate.of(2001, 01, 01), "Byg", "Whisky lavet på byg, whiskyen er rød", null);
        destillat.hældDestillatPåfad(fad);
        String navn = "Navn";
        Controller.convertToWhisky(fad, navn);
        Whisky whisky = (Whisky) fad.getDestillat();

        //Act
        //Assert
        ArrayList<WhiskyPåFlaske> whiskyPåFlaskeArrayList = Controller.createWhiskyPåFlaske(whisky, 50, 15, lager);

        for (WhiskyPåFlaske wpf : whiskyPåFlaskeArrayList) {
            Boolean actualBoolean = Storage.getWhiskyPåFlaskeArrayList().contains(wpf);
            assertTrue(actualBoolean);
        }
    }

    @Test
    void TC24_createWhiskyPåFlaske() {
        //Arrange
        Lager lager = Controller.createLager(2, 3, 3, "Sønderhøj 30");
        Reol reol = Controller.createReol(lager, 3, 3);
        lager.addReol(reol);
        Hyldeplads hyldeplads = reol.getHylder().get(1).getHyldepladser().get(1);
        Fad fad = Controller.createFad("Sherry distilleri, Lissabon", "Sherry", 1, 130, hyldeplads);
        Destillat destillat = Controller.createDestillat("Bingo Dorthe", 100, 0, LocalDate.of(2001, 01, 01), "Byg", "Whisky lavet på byg, whiskyen er rød", null);
        destillat.hældDestillatPåfad(fad);
        String navn = "Navn";
        Controller.convertToWhisky(fad, navn);
        Whisky whisky = (Whisky) fad.getDestillat();

        //Act
        //Assert
        Exception forventet = assertThrows(IllegalArgumentException.class, () -> {
            Controller.createWhiskyPåFlaske(whisky, 200, 15, lager);
        });
        String faktisk = "Du har angivet for mange flasker i antal, i forhold til hvor meget der kan produceres";

        assertEquals(forventet.getMessage(), faktisk);
    }

    @Test
    void TC25_createWhiskyPåFlaske() {
        //Arrange
        Lager lager = Controller.createLager(2, 3, 3, "Sønderhøj 30");
        Reol reol = Controller.createReol(lager, 3, 3);
        lager.addReol(reol);
        Hyldeplads hyldeplads = reol.getHylder().get(1).getHyldepladser().get(1);
        Fad fad = Controller.createFad("Sherry distilleri, Lissabon", "Sherry", 1, 130, hyldeplads);
        Destillat destillat = Controller.createDestillat("Bingo Dorthe", 100, 0, LocalDate.of(2001, 01, 01), "Byg", "Whisky lavet på byg, whiskyen er rød", null);
        destillat.hældDestillatPåfad(fad);
        String navn = "Navn";
        Controller.convertToWhisky(fad, navn);
        Whisky whisky = (Whisky) fad.getDestillat();

        //Act
        //Assert
        ArrayList<WhiskyPåFlaske> whiskyPåFlaskeArrayList = Controller.createWhiskyPåFlaske(whisky, 50, 0, lager);

        for (WhiskyPåFlaske wpf : whiskyPåFlaskeArrayList) {
            Boolean actualBoolean = Storage.getWhiskyPåFlaskeArrayList().contains(wpf);
            assertTrue(actualBoolean);
        }
    }
}
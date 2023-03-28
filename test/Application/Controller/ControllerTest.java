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
        Lager sønderhøj = new Lager(2, 3, 3, "Sønderhøj 30");

        //Act
        Fad fad = Controller.createFad("Sherry distilleri, Lissabon", "Sherry", 1, 64, 130, sønderhøj);

        //Assert
        Boolean actualBoolean = Storage.getFadArrayList().contains(fad);
        assertTrue(actualBoolean);
    }

    @Test
    void TC2_createFad() {
        //Arrange
        //Act
        //Assert
        Exception forventet = assertThrows(RuntimeException.class, () -> {
            Controller.createFad("Sherry distilleri, Lissabon", "Sherry", 1, 64, 130, null);
        });
        assertEquals(forventet.getMessage(), "Lager må ikke være null");
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
        Destillat destillat = Controller.createDestillat("Bingo Dorthe", 100, 20, LocalDate.of(2001, 01, 01), LocalDate.of(2004, 01, 01), "Byg", "Whisky lavet på byg, whiskyen er rød");

        //Assert
        Boolean actualBoolean = Storage.getDestillatArrayList().contains(destillat);
        assertTrue(actualBoolean);
    }

    @Test
    void TC5_createDestillat() {
        //Arrange

        //Act
        //Assert
        Exception forventet = assertThrows(RuntimeException.class, () -> {
            Controller.createDestillat("Bingo Dorthe", 100, 20, LocalDate.of(2004, 01, 01), LocalDate.of(2001, 01, 01), "Byg", "Whisky lavet på byg, whiskyen er rød");
        });
        assertEquals(forventet.getMessage(), "Ugyldig data");
    }

    @Test
    void TC6_createDestillat() {
        //Arrange

        //Act
        //Assert
        Exception forventet = assertThrows(RuntimeException.class, () -> {
            Controller.createDestillat("Bingo Dorthe", 0, 20, LocalDate.of(2001, 01, 01), LocalDate.of(2004, 01, 01), "Byg", "Whisky lavet på byg, whiskyen er rød");
        });
        assertEquals(forventet.getMessage(), "Ugyldig data");
    }

    @Test
    void TC7_createDestillat() {
        //Arrange

        //Act
        //Assert
        Exception forventet = assertThrows(RuntimeException.class, () -> {
            Controller.createDestillat("Bingo Dorthe", -1, 20, LocalDate.of(2001, 01, 01), LocalDate.of(2004, 01, 01), "Byg", "Whisky lavet på byg, whiskyen er rød");
        });
        assertEquals(forventet.getMessage(), "Ugyldig data");
    }

    @Test
    void TC8_createDestillat() {
        //Arrange

        //Act
        Destillat destillat = Controller.createDestillat("Bingo Dorthe", 100, 0, LocalDate.of(2001, 01, 01), LocalDate.of(2004, 01, 01), "Byg", "Whisky lavet på byg, whiskyen er rød");

        //Assert
        Boolean actualBoolean = Storage.getDestillatArrayList().contains(destillat);
        assertTrue(actualBoolean);
    }

    @Test
    void TC9_createDestillat() {
        //Arrange

        //Act
        //Assert
        Exception forventet = assertThrows(RuntimeException.class, () -> {
            Controller.createDestillat("Bingo Dorthe", 100, -1, LocalDate.of(2001, 01, 01), LocalDate.of(2004, 01, 01), "Byg", "Whisky lavet på byg, whiskyen er rød");
        });
        assertEquals(forventet.getMessage(), "Ugyldig data");
    }

    @Test
    void TC21_createWhisky() {
        //Arrange
        Lager sønderhøj = new Lager(2, 3, 3, "Sønderhøj 30");
        Fad fad = Controller.createFad("Sherry distilleri, Lissabon", "Sherry", 1, 64, 130, sønderhøj);
        Destillat destillat = Controller.createDestillat("Bingo Dorthe", 100, 0, LocalDate.of(2001, 01, 01), LocalDate.of(2004, 01, 01), "Byg", "Whisky lavet på byg, whiskyen er rød");
        destillat.hældDestillatPåfad(fad);
        //Act
        Whisky whisky = Controller.createWhisky(fad);

        //Assert
        Boolean actualBoolean = Storage.getWhiskyArrayList().contains(whisky);
        assertTrue(actualBoolean);
    }

    @Test
    void TC22_createWhisky() {
        //Arrange
        Lager sønderhøj = new Lager(2, 3, 3, "Sønderhøj 30");
        Fad fad = Controller.createFad("Sherry distilleri, Lissabon", "Sherry", 1, 64, 130, sønderhøj);
        Destillat destillat = null;
        destillat.hældDestillatPåfad(fad);
        NullPointerException e = new NullPointerException();

        //Act
        //Assert

        Exception actual = assertThrows(NullPointerException.class, () -> {
            Controller.createWhisky(fad);
        });

        assertEquals(actual, e.getMessage());
    }

    @Test
    void TC23_createWhiskyPåFlaske() {
        //Arrange
        Lager sønderhøj = new Lager(2, 3, 3, "Sønderhøj 30");
        Fad fad = Controller.createFad("Sherry distilleri, Lissabon", "Sherry", 1, 64, 130, sønderhøj);
        Destillat destillat = Controller.createDestillat("Bingo Dorthe", 100, 0, LocalDate.of(2001, 01, 01), LocalDate.of(2004, 01, 01), "Byg", "Whisky lavet på byg, whiskyen er rød");
        destillat.hældDestillatPåfad(fad);
        Whisky whisky = Controller.createWhisky(fad);

        //Act
        //Assert
        ArrayList<WhiskyPåFlaske> whiskyPåFlaskeArrayList = Controller.createWhiskyPåFlaske(whisky, 50, 15);

        for (WhiskyPåFlaske wpf : whiskyPåFlaskeArrayList) {
            Boolean actualBoolean = Storage.getWhiskyPåFlaskeArrayList().contains(wpf);
            assertTrue(actualBoolean);
        }




    }

}
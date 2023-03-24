package Application.Controller;

import Application.Model.Destillat;
import Application.Model.Fad;
import Application.Model.Lager;
import Storage.Storage;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

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
    void TC5_createDestillatTC5() {
        //Arrange

        //Act
        //Assert
        Exception forventet = assertThrows(RuntimeException.class, () -> {
            Controller.createDestillat("Bingo Dorthe", 100, 20, LocalDate.of(2004, 01, 01), LocalDate.of(2001, 01, 01), "Byg", "Whisky lavet på byg, whiskyen er rød");
        });
        assertEquals(forventet.getMessage(), "Ugyldig data");
    }

    @Test
    void TC6_createDestillatTC5() {
        //Arrange

        //Act
        //Assert
        Exception forventet = assertThrows(RuntimeException.class, () -> {
            Controller.createDestillat("Bingo Dorthe", 0, 20, LocalDate.of(2001, 01, 01), LocalDate.of(2004, 01, 01), "Byg", "Whisky lavet på byg, whiskyen er rød");
        });
        assertEquals(forventet.getMessage(), "Ugyldig data");
    }

    @Test
    void TC7_createDestillatTC5() {
        //Arrange

        //Act
        //Assert
        Exception forventet = assertThrows(RuntimeException.class, () -> {
            Controller.createDestillat("Bingo Dorthe", -1, 20, LocalDate.of(2001, 01, 01), LocalDate.of(2004, 01, 01), "Byg", "Whisky lavet på byg, whiskyen er rød");
        });
        assertEquals(forventet.getMessage(), "Ugyldig data");
    }

    @Test
    void TC8_createDestillatTC5() {
        //Arrange

        //Act
        Destillat destillat = Controller.createDestillat("Bingo Dorthe", 100, 0, LocalDate.of(2001, 01, 01), LocalDate.of(2004, 01, 01), "Byg", "Whisky lavet på byg, whiskyen er rød");

        //Assert
        Boolean actualBoolean = Storage.getDestillatArrayList().contains(destillat);
        assertTrue(actualBoolean);
    }

    @Test
    void TC9_createDestillatTC5() {
        //Arrange

        //Act
        //Assert
        Exception forventet = assertThrows(RuntimeException.class, () -> {
            Controller.createDestillat("Bingo Dorthe", 100, -1, LocalDate.of(2001, 01, 01), LocalDate.of(2004, 01, 01), "Byg", "Whisky lavet på byg, whiskyen er rød");
        });
        assertEquals(forventet.getMessage(), "Ugyldig data");
    }
}
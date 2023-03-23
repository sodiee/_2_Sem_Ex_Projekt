package Application.Controller;

import Application.Model.Fad;
import Application.Model.Lager;
import Storage.Storage;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    @Test
    void createFad1() {

        //Arrange
        Lager sønderhøj = new Lager(2, 3, 3, "Sønderhøj 30");

        //Act
        Fad fad = Controller.createFad("Sherry distilleri, Lissabon", "Sherry", 1, 64, 130, sønderhøj);

        //Assert
        Boolean actualBoolean = Storage.getFadArrayList().contains(fad);
        assertTrue(actualBoolean);
    }

    @Test
    void createFad2() {
        //Arrange
        //Act
        //Assert
        Exception forventet = assertThrows(RuntimeException.class, () -> {
            Controller.createFad("Sherry distilleri, Lissabon", "Sherry", 1, 64, 130, null);
        });
        assertEquals(forventet.getMessage(), "Lager må ikke være null");
    }

    @Test
    void createLager() {

        //Arrange

        //Act
        Lager lager = Controller.createLager(2, 3, 3, "Sønderhøj 30");

        //Assert
        Boolean actualBoolean = Storage.getLagerArrayList().contains(lager);
        assertTrue(actualBoolean);
    }

    @Test
    void createDestillat()
}
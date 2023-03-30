package Model;

import Application.Controller.Controller;
import Application.Model.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FadTest {
    @Test
    void TC10_beregnAngelShare() {
        //Arrange
        Lager lager = Controller.createLager(2, 3, 3, "Sønderhøj 30");
        Reol reol = Controller.createReol(lager, 3, 3);
        lager.addReol(reol);
        Hyldeplads hyldeplads = reol.getHylder().get(1).getHyldepladser().get(1);
        Fad fad = Controller.createFad("Sherry destilleri, Lissabon", "Sherry", 1, 130, hyldeplads);
        Destillat destillat = Controller.createDestillat("Bingo Dorthe", 100, 60, LocalDate.of(2001, 01, 01), "Byg", "Whisky lavet på byg, whiskyen er rød");
        fad.setAlder(-1);

        //Act & Assert
        Exception forventet = assertThrows(IllegalArgumentException.class, () -> {
            fad.beregnAngelShare(destillat);
        });
        assertEquals(forventet.getMessage(), "Ugyldig alder på fad");
    }

    @Test
    void TC11_beregnAngelShare() {
        //Arrange
        Lager lager = Controller.createLager(2, 3, 3, "Sønderhøj 30");
        Reol reol = Controller.createReol(lager, 3, 3);
        lager.addReol(reol);
        Hyldeplads hyldeplads = reol.getHylder().get(1).getHyldepladser().get(1);
        Fad fad = Controller.createFad("Sherry destilleri, Lissabon", "Sherry", 1, 130, hyldeplads);
        Destillat destillat = Controller.createDestillat("Bingo Dorthe", 100, 60, LocalDate.of(2001, 01, 01), "Byg", "Whisky lavet på byg, whiskyen er rød");
        fad.setAlder(0);

        //Act
        double actual = fad.beregnAngelShare(destillat);

        //Assert
        double forventet = 2.0;
        assertEquals(forventet, actual, 0.1);
    }

    @Test
    void TC12_beregnAngelShare() {
        //Arrange
        Lager lager = Controller.createLager(2, 3, 3, "Sønderhøj 30");
        Reol reol = Controller.createReol(lager, 3, 3);
        lager.addReol(reol);
        Hyldeplads hyldeplads = reol.getHylder().get(1).getHyldepladser().get(1);
        Fad fad = Controller.createFad("Sherry destilleri, Lissabon", "Sherry", 1, 130, hyldeplads);
        Destillat destillat = Controller.createDestillat("Bingo Dorthe", 100, 60, LocalDate.of(2001, 01, 01), "Byg", "Whisky lavet på byg, whiskyen er rød");
        fad.setAlder(1);

        //Act
        double actual = fad.beregnAngelShare(destillat);

        //Assert
        double forventet = 5.88;
        assertEquals(forventet, actual, 0.1);
    }

    @Test
    void TC13_beregnAngelShare() {
        //Arrange
        Lager lager = Controller.createLager(2, 3, 3, "Sønderhøj 30");
        Reol reol = Controller.createReol(lager, 3, 3);
        lager.addReol(reol);
        Hyldeplads hyldeplads = reol.getHylder().get(1).getHyldepladser().get(1);
        Fad fad = Controller.createFad("Sherry destilleri, Lissabon", "Sherry", 1, 130, hyldeplads);
        Destillat destillat = Controller.createDestillat("Bingo Dorthe", 100, 60, LocalDate.of(2001, 01, 01), "Byg", "Whisky lavet på byg, whiskyen er rød");
        fad.setAlder(4);

        //Act
        double actual = fad.beregnAngelShare(destillat);

        //Assert
        double forventet = 5.88;
        assertEquals(forventet, actual, 0.1);
    }

    @Test
    void TC14_beregnAngelShare() {
        //Arrange
        Lager lager = Controller.createLager(2, 3, 3, "Sønderhøj 30");
        Reol reol = Controller.createReol(lager, 3, 3);
        lager.addReol(reol);
        Hyldeplads hyldeplads = reol.getHylder().get(1).getHyldepladser().get(1);
        Fad fad = Controller.createFad("Sherry destilleri, Lissabon", "Sherry", 1, 130, hyldeplads);
        Destillat destillat = Controller.createDestillat("Bingo Dorthe", 100, 60, LocalDate.of(2001, 01, 01), "Byg", "Whisky lavet på byg, whiskyen er rød");
        fad.setAlder(5);

        //Act
        double actual = fad.beregnAngelShare(destillat);

        //Assert
        double forventet = 8.77;
        assertEquals(forventet, actual, 0.1);
    }

    @Test
    void TC15_beregnAngelShare() {
        //Arrange
        Lager lager = Controller.createLager(2, 3, 3, "Sønderhøj 30");
        Reol reol = Controller.createReol(lager, 3, 3);
        lager.addReol(reol);
        Hyldeplads hyldeplads = reol.getHylder().get(1).getHyldepladser().get(1);
        Fad fad = Controller.createFad("Sherry destilleri, Lissabon", "Sherry", 1, 130, hyldeplads);
        Destillat destillat = Controller.createDestillat("Bingo Dorthe", 100, 60, LocalDate.of(2001, 01, 01), "Byg", "Whisky lavet på byg, whiskyen er rød");
        fad.setAlder(7);

        //Act
        double actual = fad.beregnAngelShare(destillat);

        //Assert
        double forventet = 8.77;
        assertEquals(forventet, actual, 0.1);
    }

    @Test
    void TC16_beregnAngelShare() {
        //Arrange
        Lager lager = Controller.createLager(2, 3, 3, "Sønderhøj 30");
        Reol reol = Controller.createReol(lager, 3, 3);
        lager.addReol(reol);
        Hyldeplads hyldeplads = reol.getHylder().get(1).getHyldepladser().get(1);
        Fad fad = Controller.createFad("Sherry destilleri, Lissabon", "Sherry", 1, 130, hyldeplads);
        Destillat destillat = Controller.createDestillat("Bingo Dorthe", 100, 60, LocalDate.of(2001, 01, 01), "Byg", "Whisky lavet på byg, whiskyen er rød");
        fad.setAlder(8);

        //Act
        double actual = fad.beregnAngelShare(destillat);

        //Assert
        double forventet = 11.52;
        assertEquals(forventet, actual, 0.1);
    }

    @Test
    void TC17_beregnAngelShare() {
        //Arrange
        Lager lager = Controller.createLager(2, 3, 3, "Sønderhøj 30");
        Reol reol = Controller.createReol(lager, 3, 3);
        lager.addReol(reol);
        Hyldeplads hyldeplads = reol.getHylder().get(1).getHyldepladser().get(1);
        Fad fad = Controller.createFad("Sherry destilleri, Lissabon", "Sherry", 1, 130, hyldeplads);
        Destillat destillat = Controller.createDestillat("Bingo Dorthe", 100, 60, LocalDate.of(2001, 01, 01), "Byg", "Whisky lavet på byg, whiskyen er rød");
        fad.setAlder(11);

        //Act
        double actual = fad.beregnAngelShare(destillat);

        //Assert
        double forventet = 11.52;
        assertEquals(forventet, actual, 0.1);
    }

    @Test
    void TC18_beregnAngelShare() {
        //Arrange
        Lager lager = Controller.createLager(2, 3, 3, "Sønderhøj 30");
        Reol reol = Controller.createReol(lager, 3, 3);
        lager.addReol(reol);
        Hyldeplads hyldeplads = reol.getHylder().get(1).getHyldepladser().get(1);
        Fad fad = Controller.createFad("Sherry destilleri, Lissabon", "Sherry", 1, 130, hyldeplads);
        Destillat destillat = Controller.createDestillat("Bingo Dorthe", 100, 60, LocalDate.of(2001, 01, 01), "Byg", "Whisky lavet på byg, whiskyen er rød");
        fad.setAlder(12);

        //Act
        double actual = fad.beregnAngelShare(destillat);

        //Assert
        double forventet = 14.26;
        assertEquals(forventet, actual, 0.1);
    }

    @Test
    void TC19_beregnAngelShare() {
        //Arrange
        Lager lager = Controller.createLager(2, 3, 3, "Sønderhøj 30");
        Reol reol = Controller.createReol(lager, 3, 3);
        lager.addReol(reol);
        Hyldeplads hyldeplads = reol.getHylder().get(1).getHyldepladser().get(1);
        Fad fad = Controller.createFad("Sherry destilleri, Lissabon", "Sherry", 1, 130, hyldeplads);
        Destillat destillat = Controller.createDestillat("Bingo Dorthe", 100, 60, LocalDate.of(2001, 01, 01), "Byg", "Whisky lavet på byg, whiskyen er rød");
        fad.setAlder(13);

        //Act
        double actual = fad.beregnAngelShare(destillat);

        //Assert
        double forventet = 14.26;
        assertEquals(forventet, actual, 0.1);
    }

    @Test
    void TC20_beregnAngelShare() {
        //Arrange
        Lager lager = Controller.createLager(2, 3, 3, "Sønderhøj 30");
        Reol reol = Controller.createReol(lager, 3, 3);
        lager.addReol(reol);
        Hyldeplads hyldeplads = reol.getHylder().get(1).getHyldepladser().get(1);
        Fad fad = Controller.createFad("Sherry destilleri, Lissabon", "Sherry", 1, 130, hyldeplads);
        Destillat destillat = Controller.createDestillat("Bingo Dorthe", 100, 60, LocalDate.of(2001, 01, 01), "Byg", "Whisky lavet på byg, whiskyen er rød");
        fad.setAlder(20);

        //Act
        double actual = fad.beregnAngelShare(destillat);

        //Assert
        double forventet = 14.26;
        assertEquals(forventet, actual, 0.1);
    }
}

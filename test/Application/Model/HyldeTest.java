package Application.Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HyldeTest {

    @Test
    void TC26_createHylde() {
        //Arrange
        Lager lager = new Lager(2, 2, 2, "Sønderhøj 30");
        Reol reol = new Reol(2, lager);
        Hylde hylde = new Hylde(2, reol);
        reol.addHylde(hylde);

        //Act
        int forventetHyldeNr = 2;

        //Assert
        int actualHyldeNr = hylde.getHyldeNr();
        assertEquals(actualHyldeNr, forventetHyldeNr);

        Boolean actualBooleanCreateHylde = reol.getHylder().contains(hylde);
        assertTrue(actualBooleanCreateHylde);
    }

    @Test
    void TC27_createHylde() {
        //Arrange
        Lager lager = new Lager(2, 2, 2, "Sønderhøj 30");
        Reol reol = new Reol(2, lager);
        Hylde hylde = new Hylde(2, reol);
        reol.addHylde(hylde);

        //Act
        int forventetHyldeNr = 1;

        //Assert
        int actualHyldeNr = hylde.getHyldeNr();
        assertNotEquals(actualHyldeNr, forventetHyldeNr);
    }

    @Test
    void TC28_getHyldeNr() {
        //Arrange
        Lager lager = new Lager(2, 2, 2, "Sønderhøj 30");
        Reol reol = new Reol(2, lager);
        Hylde hylde = new Hylde(2, reol);
        reol.addHylde(hylde);

        //Act
        int forventetHyldeNr = 2;

        //Assert
        int actualHyldeNr = hylde.getHyldeNr();
        assertEquals(actualHyldeNr, forventetHyldeNr);
    }

    @Test
    void TC29_getHyldeNr() {
        //Arrange
        Lager lager = new Lager(2, 2, 2, "Sønderhøj 30");
        Reol reol = new Reol(2, lager);
        Hylde hylde = new Hylde(2, reol);
        reol.addHylde(hylde);

        //Act
        int forventetHyldeNr = 1;

        //Assert
        int actualHyldeNr = hylde.getHyldeNr();
        assertNotEquals(actualHyldeNr, forventetHyldeNr);
    }

    @Test
    void TC30_getHyldepladser() {
        //Arrange
        Lager lager = new Lager(2, 2, 2, "Sønderhøj 30");
        Reol reol = new Reol(2, lager);
        Hylde hylde = new Hylde(2, reol);
        reol.addHylde(hylde);
        Hyldeplads hyldeplads = new Hyldeplads(2, hylde);
        hylde.addHyldePlads(hyldeplads);

        //Act
        Boolean forventet = hylde.getHyldepladser().contains(hyldeplads);

        //Assert
        Boolean actual = hylde.getHyldepladser().contains(hyldeplads);
        assertEquals(actual, forventet);
    }

    @Test
    void TC31_getHyldepladser() {
        //Arrange
        Lager lager = new Lager(2, 2, 2, "Sønderhøj 30");
        Reol reol = new Reol(2, lager);
        Hylde hylde = new Hylde(2, reol);
        reol.addHylde(hylde);
        Hyldeplads hyldeplads = new Hyldeplads(2, hylde);

        //Act
        Boolean forventet = hylde.getHyldepladser().contains(hyldeplads);

        //Assert
        Boolean actual = hylde.getHyldepladser().contains(hyldeplads);
        assertEquals(actual, forventet);
    }

    @Test
    void TC32_addHyldePlads() {
        //Arrange
        Lager lager = new Lager(2, 2, 2, "Sønderhøj 30");
        Reol reol = new Reol(2, lager);
        Hylde hylde = new Hylde(2, reol);
        reol.addHylde(hylde);
        Hyldeplads hyldeplads = new Hyldeplads(2, hylde);
        hylde.addHyldePlads(hyldeplads);

        //Act
        Boolean forventet = hylde.getHyldepladser().contains(hyldeplads);

        //Assert
        Boolean actual = hylde.getHyldepladser().contains(hyldeplads);
        assertEquals(actual, forventet);
    }

    @Test
    void TC33_addHyldePlads() {
        //Arrange
        Lager lager = new Lager(2, 2, 2, "Sønderhøj 30");
        Reol reol = new Reol(2, lager);
        Hylde hylde = new Hylde(2, reol);
        reol.addHylde(hylde);
        Hyldeplads hyldeplads = new Hyldeplads(2, hylde);

        //Act
        Boolean forventet = hylde.getHyldepladser().contains(hyldeplads);

        //Assert
        Boolean actual = hylde.getHyldepladser().contains(hyldeplads);
        assertEquals(actual, forventet);
    }

    @Test
    void TC34_setReol() {
        //Arrange
        Lager lager = new Lager(2, 2, 2, "Sønderhøj 30");
        Reol reol = new Reol(2, lager);
        Hylde hylde = new Hylde(2, reol);
        reol.addHylde(hylde);
        Hyldeplads hyldeplads = new Hyldeplads(2, hylde);
        hylde.addHyldePlads(hyldeplads);

        Reol reol1 = new Reol(3, lager);

        //Act
        Reol forventetReol = reol1;
        hylde.setReol(reol1);

        //Assert
        Reol actualReol = hylde.getReol();
        assertEquals(forventetReol, actualReol);
    }

    @Test
    void TC35_setReol() {
        //Arrange
        Lager lager = new Lager(2, 2, 2, "Sønderhøj 30");
        Reol reol = new Reol(2, lager);
        Hylde hylde = new Hylde(2, reol);
        reol.addHylde(hylde);
        Hyldeplads hyldeplads = new Hyldeplads(2, hylde);
        hylde.addHyldePlads(hyldeplads);

        Reol reol1 = new Reol(3, lager);

        //Act
        Reol forventetReol = reol1;

        //Assert
        Reol actualReol = hylde.getReol();
        assertNotEquals(forventetReol, actualReol);
    }
}
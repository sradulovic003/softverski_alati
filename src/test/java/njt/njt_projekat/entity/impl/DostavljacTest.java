package njt.njt_projekat.entity.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DostavljacTest {

	Dostavljac d;

    @BeforeEach
    void setUp() throws Exception {
        d = new Dostavljac();
    }

    @AfterEach
    void tearDown() throws Exception {
        d = null;
    }

    @Test
    void testDostavljac() {
        assertNotNull(d);
    }

    @Test
    void testDostavljacLong() {
        d = new Dostavljac(1L);
        assertNotNull(d);
        assertEquals(1L, d.getDostavljacId());
    }

    @Test
    void testDostavljacLongStringStringString() {
        d = new Dostavljac(1L, "Marko", "Markovic", "0601234567");
        assertNotNull(d);
        assertEquals(1L, d.getDostavljacId());
        assertEquals("Marko", d.getIme());
        assertEquals("Markovic", d.getPrezime());
        assertEquals("0601234567", d.getTelefon());
    }

    @Test
    void testSetDostavljacId() {
        d.setDostavljacId(1L);
        assertEquals(1L, d.getDostavljacId());
    }

    @Test
    void testSetIme() {
        d.setIme("Marko");
        assertEquals("Marko", d.getIme());
    }

    @Test
    void testSetPrezime() {
        d.setPrezime("Markovic");
        assertEquals("Markovic", d.getPrezime());
    }

    @Test
    void testSetTelefon() {
        d.setTelefon("0601234567");
        assertEquals("0601234567", d.getTelefon());
    }

}

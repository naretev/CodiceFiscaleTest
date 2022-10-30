package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void createFiscalCode() {
        Person person = new Person("Peter", "Santesson", "m", "12/10/1997");
        String fiscalCode = Main.CreateFiscalCode(person);
        assertEquals("SNTPTR97R12", fiscalCode);
    }

    @Test
    void encodeSurname() {
        String surname1 = Main.EncodeSurname("andersson");
        assertEquals("NDR", surname1);
        String surname2 = Main.EncodeSurname("lewi");
        assertEquals("LWE", surname2);
    }

    @Test
    void encodeName() {
        String name1 = Main.EncodeName("stefan");
        assertEquals("SFN", name1);
        String name2 = Main.EncodeName("un");
        assertEquals("NUX", name2);
    }

    @Test
    void encodeGenderAndDOB() {
        String code1 = Main.EncodeGenderAndDOB("f", "5/10/1991");
        assertEquals("91R45", code1);
        String code2 = Main.EncodeGenderAndDOB("m", "5/6/2000");
        assertEquals("00H05", code2);
    }

    @Test
    void getConsonants() {
        String consonants = Main.GetConsonants("abcdefghijklmnopqrstuvwxyz");
        assertEquals("bcdfghjklmnpqrstvwxyz", consonants);
    }

    @Test
    void getVowels() {
        String vowels = Main.GetVowels("abcdefghijklmnopqrstuvwxyz");
        assertEquals("aeiou", vowels);
    }

    @Test
    void getFirstNumber() {
        String firstNumber1 = Main.GetFirstNumber("19/09/2003");
        assertEquals("19", firstNumber1);
        assertThrows(IndexOutOfBoundsException.class,
                () -> Main.GetFirstNumber("/09/2003"));
        assertThrows(IndexOutOfBoundsException.class,
                () -> Main.GetFirstNumber("023/09/2003"));
        assertThrows(IndexOutOfBoundsException.class,
                () -> Main.GetFirstNumber("0/09/2003"));
    }
}
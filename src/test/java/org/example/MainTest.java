package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Objects;

import org.junit.jupiter.api.function.Executable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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

    @Test
    void testsWitPerformTestFunction() throws Throwable {

        performTest(TestData.builder()
          .message("Get first number 19")
          .function(() -> {
              String firstNumber = Main.GetFirstNumber("19/09/2003");
              assertEquals("19", firstNumber);
              log.info("Got expected value; {}", firstNumber);
          })
          .build());

        performTest(TestData.builder()
          .message("Missing first number")
          .function(() -> {
              String firstNumber = Main.GetFirstNumber("/09/2003");
          })
          .exceptionClass(IndexOutOfBoundsException.class)
          .build());

        performTest(TestData.builder()
          .message("Too long first number")
          .function(() -> {
              String firstNumber = Main.GetFirstNumber("023/09/2003");
          })
          .exceptionClass(IndexOutOfBoundsException.class)
          .build());

        performTest(TestData.builder()
          .message("Too shot first number")
          .function(() -> {
              String firstNumber = Main.GetFirstNumber("0/09/2003");
          })
          .exceptionClass(IndexOutOfBoundsException.class)
          .build());

    }


    void performTest(TestData testData)
      throws Throwable{
        log.info("Performing test: {}", testData.getMessage());
        if (testData.getExceptionClass() != null) {
            final Exception exception = assertThrows(testData.getExceptionClass(), testData.getFunction());
            log.info("Expected exception is thrown: {}", exception.toString());
        } else {
            testData.getFunction().execute();
        }
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class TestData {
        Executable function;
        String message;
        Class<? extends Exception> exceptionClass;
    }

}
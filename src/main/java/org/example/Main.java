package org.example;

public class Main {
    public static final char[] VOWELS = {'a', 'e', 'i', 'o', 'u'};
    public static final char[] MONTHS = {'A', 'B', 'C', 'D', 'E', 'H', 'L', 'M', 'P', 'R', 'S', 'T'};

    public static void main(String[] args) {
        Person person = new Person("Peter", "Santesson", "m", "12/10/1997");
        String fiscalCode = CreateFiscalCode(person);
        System.out.println("Result: " + fiscalCode);
    }

    public static String CreateFiscalCode(Person person) {
        String fiscalCode = EncodeSurname(person.surname)
                + EncodeName(person.name)
                + EncodeGenderAndDOB(person.gender, person.dateOfBirth);
        return fiscalCode;
    }

    public static String EncodeSurname(String surname) {
        String cName = GetConsonants(surname);
        String vName = GetVowels(surname);

        String encodedSurname = cName + vName + "XXX";
        encodedSurname = encodedSurname.substring(0, 3).toUpperCase();

        return encodedSurname;
    }

    public static String EncodeName(String name) {
        String cName = GetConsonants(name);
        String vName = GetVowels(name);

        if (cName.length() > 3) {
            cName = cName.charAt(0) + cName.substring(2,4);
        }
        String encodedName = cName + vName + "XXX";
        encodedName = encodedName.substring(0, 3).toUpperCase();

        return encodedName;
    }

    public static String EncodeGenderAndDOB(String gender, String dateOfBirth) {
        String day = GetFirstNumber(dateOfBirth);
        dateOfBirth = dateOfBirth.substring(day.length() + 1);
        String month = GetFirstNumber(dateOfBirth);
        dateOfBirth = dateOfBirth.substring(month.length() + 1);
        String year = dateOfBirth.substring(2);

        if (gender == "f") {
            day = String.valueOf(Integer.parseInt(day) + 40);
        } else if (Integer.parseInt(day) < 10) {
            day = "0" + day;
        }
        return year + MONTHS[Integer.parseInt(month) - 1] + day;
    }

    public static String GetConsonants(String s) {
        StringBuilder consonants = new StringBuilder(s);
        for (int i = 0; i < s.length(); i++) {
            for (char c : VOWELS) {
                if (c == s.charAt(i)) {
                    consonants.setCharAt(i, '/');
                }
            }
        }
        return consonants.toString().replace("/", "");
    }

    public static String GetVowels(String s) {
        String vowels = "";
        for (int i = 0; i < s.length(); i++) {
            for (char c : VOWELS) {
                if (c == s.charAt(i)) {
                    vowels = vowels + c;
                }
            }
        }
        return vowels;
    }

    public static String GetFirstNumber(String dateOfBirth) {
        String firstNumber = "";
        int index = 0;
        while (dateOfBirth.charAt(index) != '/') {
            index++;
            firstNumber = dateOfBirth.substring(0,index);
        }
        if (index < 1 || index > 2 || firstNumber.equals("0")) {
            throw new IndexOutOfBoundsException();
        }
        return firstNumber;
    }
}
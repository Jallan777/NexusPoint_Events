/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgebs;

import java.util.Random;

/**
 *
 * @author jacob
 */
public class Tools {

    public static String generateLine(int charNum, String character) {

        String line = "";
        for (int i = 0; i < charNum; i++) {

            line = line + character;
        }
        return line;
    }

    //Create random letter for reference
    public static String generateRandomLetters() {
        Random random = new Random();
        char letter1 = (char) (random.nextInt(26) + 'A');
        char letter2 = (char) (random.nextInt(26) + 'A');
        return "" + letter1 + letter2;
    }

    //Create random numbers
    public static String generateRandomNumbers() {
        Random random = new Random();
        String result = "";

        for (int i = 0; i < 6; i++) {
            result += random.nextInt(10);
        }
        return result;
    }

    //Validates password is acceptable when making a new user
    public static boolean passwordValidator(String password) {

        String pattern = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).*$"; //Looks confusing, but is setting the regex pattern to match against password string
        // Defined by (?=.*[WHAT TO MATCH AGAINST]) [a-z] [A-Z] //d = digit [SPECIAL_CHARACTERS]
        if (password.length() > 25) {

            System.out.println("\nPassword too long!\n");
            return false;
        } else {
            return password.matches(pattern);
        }

    }
    //Validates email is acceptable when making a new user

    public static boolean emailValidator(String email) {

        if (!email.contains("@")) {

            System.out.println("Must be a valid email!\n");
            return false;
        }

        if (email.indexOf("@") != email.lastIndexOf("@")) {

            System.out.println("Please only use 1 instance of the \'@\' symbol\n");
            return false;
        }

        String[] validEmailEnds = {".com", ".co.nz", ".ac.nz"};
        boolean validEmailEnd = false;
        for (String endString : validEmailEnds) {

            if (email.endsWith(endString) && email.length() < 30) {
                validEmailEnd = true;
                break;
            }
        }
        return validEmailEnd;
    }
    //Validates phone number is acceptable when making a new user

    public static boolean phNumValidator(String phoneNum) {

        String[] validNumStarts = {"021", "022", "027", "+64", "09"};

        phoneNum = phoneNum.replaceAll("\\s", "").replaceAll("-", "");

        for (String numStart : validNumStarts) {

            if (phoneNum.startsWith(numStart) && phoneNum.length() > 14) {
                System.out.println("\nPhone Number too long!\n");
                return false;
            } else if (phoneNum.startsWith(numStart) && phoneNum.length() <= 14) {

                return true;
            }
        }
        return false;
    }
}

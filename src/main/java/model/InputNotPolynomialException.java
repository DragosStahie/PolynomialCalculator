package model;

//custom exception used for showing the error message
public class InputNotPolynomialException extends Exception {

    private static String msg;

    public InputNotPolynomialException (String pol) {

        msg = "Inputul " + pol + " este invalid!";
    }

    @Override
    public String toString() {

        return msg;
    }
}

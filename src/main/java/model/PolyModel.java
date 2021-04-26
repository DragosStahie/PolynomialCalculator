package model;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PolyModel {

    public PolyModel() {

    }

    public Polynomial extractPolynomials(String polString) throws InputNotPolynomialException {

        Polynomial pol;

        //Regular expressions for extracting the coefficients and powers
        String  polynomialRegex     = "[-]?[0-9]{1,3}X??[\\^][0-9]{1,3}";
        String  coefficientRegex    = "[-]?[0-9]{1,3}";

        Pattern polynomialPattern   = Pattern.compile(polynomialRegex);
        Pattern coefficientPattern  = Pattern.compile(coefficientRegex);

        Matcher polynomialMatcher   = polynomialPattern.matcher(polString);

        int power       = 0;
        int coefficient = 0;

        ArrayList<Monomial> monomialsList = new ArrayList<>();

        while (polynomialMatcher.find()) {

            Matcher coefficientMatcher  = coefficientPattern.matcher(polynomialMatcher.group());

            if (coefficientMatcher.find()) {

                coefficient = Integer.parseInt(coefficientMatcher.group());
            }

            if (coefficientMatcher.find()) {

                power = Integer.parseInt(coefficientMatcher.group());
            }


            monomialsList.add(new Monomial(power, coefficient));

        }

        try {
            //Sorting the monomials so that the biggest power is the first in the list
            Collections.sort(monomialsList, new SortByPower());
            pol = new Polynomial(monomialsList);
        }
        catch (IndexOutOfBoundsException e) {

            throw new InputNotPolynomialException(polString);
        }

        return pol;
    }

    //custom compare method for sorting the monomials
    class SortByPower implements Comparator<Monomial> {

        public int compare(Monomial m1, Monomial m2) {

            return m2.getPower() - m1.getPower();
        }
    }

}

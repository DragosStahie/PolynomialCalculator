package model;

public class Monomial {

    private int power;
    private int coefficient;

    public Monomial(int pwr, int coef) {

        this.power = pwr;
        this.coefficient = coef;
    }

    public int getPower() {

        return this.power;
    }

    public int getCoefficient() {

        return this.coefficient;
    }

    public void updatePower(int update) {

        this.power = update;
    }

    public void updateCoefficient(int update) {

        this.coefficient = update;
    }

    public void addMonomials(Monomial mon) {

        if(this.power == mon.getPower()) {

            this.coefficient += mon.getCoefficient();
        }
    }

    public void subMonomials(Monomial mon) {

        if(this.power == mon.getPower()) {

            this.coefficient -= mon.getCoefficient();
        }
    }

    @Override
    public String toString() {

        if (coefficient != 0) {

            if (power > 1) {

                return coefficient + "X^" + power;
            }

            if (power == 1) {

                return coefficient + "X";
            }

            return coefficient + "";
        }

        return "";
    }

}

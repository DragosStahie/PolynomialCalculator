package model;

import java.util.ArrayList;

public class Polynomial {

    private ArrayList<Monomial> monomials;

    //Overloaded constructors to be used in different situations
    public Polynomial(){

        this.monomials = new ArrayList<Monomial>();
    }

    public Polynomial(int num) {

        this.monomials = new ArrayList<>();
        this.monomials.add(new Monomial(num, num));
    }

    public Polynomial(ArrayList<Monomial> mon) {

        this.monomials = new ArrayList<Monomial>();

        int currentDegree = mon.get(0).getPower();
        for(Monomial m : mon) {

            if(m.getPower() == currentDegree) {

                this.monomials.add(m);
                currentDegree--;
            }
            else {

                while(m.getPower() < currentDegree) {

                    this.monomials.add(new Monomial(currentDegree--, 0));
                }
                this.monomials.add(m);
                currentDegree--;
            }
        }
        while(currentDegree >= 0) {

            this.monomials.add(new Monomial(currentDegree--, 0));
        }
    }

    public int getDegree() {

        int degree = 0;

        for(Monomial m : this.monomials) {

            degree++;
        }
        return degree - 1;
    }

    //gets rid of empty, useless monomials within the Model.Polynomial
    private Polynomial resetPolynomial() {

        boolean ok = false;
        ArrayList<Monomial> auxList = new ArrayList<>();

        for(Monomial m : this.monomials) {

            if(ok) {

                auxList.add(m);
            }
            else {

                if(m.getCoefficient() != 0) {

                    auxList.add(m);
                    ok = true;
                }
            }
        }

        if(!auxList.isEmpty()) {

            return new Polynomial(auxList);
        }
        else {

            return this;
        }
    }

    //overridden toString method for printing Polynomials properly
    @Override
    public String toString() {

        String msg = "";
        for(Monomial m : monomials) {

            if(m.getCoefficient() > 0 && m.getPower() != this.getDegree()) {

                if(monomials.indexOf(m) == 0) {

                    msg += m;
                }
                else {

                    msg += " + " + m;
                }

            }
            else {

                msg += " " + m;
            }
        }

        return  msg;
    }

    public ArrayList<Monomial> getMonomials() {

        return this.monomials;
    }

    public void addPolynomials(Polynomial pol) {

        Polynomial auxPol = this.resetPolynomial();
        this.monomials = auxPol.monomials;

        auxPol = pol.resetPolynomial();
        pol.monomials = auxPol.monomials;

        int degree1 = this.getDegree();
        int degree2 = pol.getDegree();

        ArrayList<Monomial> monList = pol.getMonomials();

        if(degree1 == degree2) {

            for(int index = 0; index <= degree1; index ++) {

                this.monomials.get(index).addMonomials(monList.get(index));
            }
        }
        else {

            if(degree1 > degree2) {

                int index2 = 0;
                for(int index = degree1 - degree2; index <= degree1; index ++) {

                    this.monomials.get(index).addMonomials(monList.get(index2++));
                }
            }
            else {

                int index2 = 0;
                for(int index = degree2 - degree1; index <= degree2; index ++) {

                    pol.getMonomials().get(index).addMonomials(this.monomials.get(index2++));
                }

                this.monomials = pol.getMonomials();
            }

        }

    }

    public void subPolynomials(Polynomial pol) {

        Polynomial auxPol = this.resetPolynomial();
        this.monomials = auxPol.monomials;

        auxPol = pol.resetPolynomial();
        pol.monomials = auxPol.monomials;

        int degree1 = this.getDegree();
        int degree2 = pol.getDegree();

        ArrayList<Monomial> monList = pol.getMonomials();

        if(degree1 == degree2) {

            for(int index = 0; index <= degree1; index ++) {

                this.monomials.get(index).subMonomials(monList.get(index));
            }
        }
        else {

            if(degree1 > degree2) {

                int index2 = 0;
                for(int index = degree1 - degree2; index <= degree1; index ++) {

                    this.monomials.get(index).subMonomials(monList.get(index2++));
                }
            }
            else {

                ArrayList<Monomial> auxMons = new ArrayList<Monomial>();
                auxMons.addAll(this.monomials);
                this.monomials.clear();

                int degr  = degree2;
                for(int index = 0; index < degree2 - degree1; index ++) {

                    this.monomials.add(new Monomial(degr--,0));
                }

                this.monomials.addAll(auxMons);

                for(int index = 0; index <= degree2; index ++) {

                    this.monomials.get(index).subMonomials(monList.get(index));
                }

            }

        }

        auxPol = this.resetPolynomial();
        this.monomials = auxPol.monomials;

    }

    public Polynomial mulPolynomials(Polynomial pol) {

        Polynomial auxPol = this.resetPolynomial();
        this.monomials = auxPol.monomials;

        auxPol = pol.resetPolynomial();
        pol.monomials = auxPol.monomials;

        Polynomial result = new Polynomial();
        ArrayList<Monomial> intermediateMonomials = new ArrayList<>();

        for(Monomial m1 : this.getMonomials()) {

            for(Monomial m2 : pol.getMonomials()) {

                intermediateMonomials.add(new Monomial(m1.getPower() + m2.getPower(),
                        m1.getCoefficient() * m2.getCoefficient()));
            }

            result.addPolynomials(new Polynomial(intermediateMonomials));
            intermediateMonomials.clear();
        }

        return result;
    }

    public ArrayList<Polynomial> divPolynomials(Polynomial pol) {

        Polynomial auxPol = this.resetPolynomial();
        this.monomials = auxPol.monomials;

        auxPol = pol.resetPolynomial();
        pol.monomials = auxPol.monomials;

        this.resetPolynomial();
        pol.resetPolynomial();

        ArrayList<Polynomial> result = new ArrayList<>();
        Polynomial Q = new Polynomial(0);
        Polynomial R = new Polynomial();
        R.addPolynomials(this);

        ArrayList<Monomial> mon = new ArrayList<>();

        do {

            if(!result.isEmpty()) {

                auxPol.monomials = Q.monomials;
                result.get(0).addPolynomials(auxPol);
                result.get(1).monomials = R.getMonomials();
            }
            else {

                result.add(new Polynomial(0));
                result.add(R);
            }

            Q.monomials.clear();
            Q.monomials.add(new Monomial(R.monomials.get(0).getPower() - pol.getMonomials().get(0).getPower(),
                    R.monomials.get(0).getCoefficient() / pol.getMonomials().get(0).getCoefficient()));

            Polynomial aux = Q.mulPolynomials(pol);
            R.subPolynomials(aux);
            auxPol = R.resetPolynomial();
            R.monomials = auxPol.monomials;
        } while (R.getDegree() >= pol.getDegree() && !Q.isNull());

        result.get(0).addPolynomials(Q);

        return result;
    }

    public Polynomial drvPolynomials() {

        Polynomial auxPol = this.resetPolynomial();
        this.monomials = auxPol.monomials;

        Polynomial result = new Polynomial();
        result.addPolynomials(this);

        for(Monomial m : result.monomials) {

            m.updateCoefficient(m.getCoefficient()*m.getPower());
            m.updatePower(m.getPower() - 1);
        }

        result.monomials.remove(result.monomials.size() - 1);

        return result;
    }

    public Polynomial itgPolynomials() {

        Polynomial auxPol = this.resetPolynomial();
        this.monomials = auxPol.monomials;

        Polynomial result = new Polynomial();
        result.addPolynomials(this);

        for(Monomial m : result.monomials) {

            m.updatePower(m.getPower() + 1);
            m.updateCoefficient(m.getCoefficient()/m.getPower());
        }

        result.monomials.add(new Monomial(0, 0));

        return result;
    }

    //checks if a Model.Polynomial is empty
    public boolean isNull() {

        for(Monomial m : this.monomials) {

            if(m.getCoefficient() != 0) {

                return false;
            }
        }

        return  true;
    }

    //Overridden equals method to properly compare two Polynomials
    @Override
    public boolean equals(Object obj) {

        if(this.getDegree() != ((Polynomial)obj).getDegree()) {

            return false;
        }

        for(int index = 0; index <= this.getDegree(); index++) {

            if(this.monomials.get(index).getCoefficient() !=
                    ((Polynomial)obj).getMonomials().get(index).getCoefficient()) {

                return false;
            }
        }

        return true;
    }

}

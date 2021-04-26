package controller;

import model.PolyModel;
import model.Polynomial;
import model.InputNotPolynomialException;
import view.PolyView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PolyController {


    private PolyModel model;
    private PolyView view;

    public PolyController (PolyModel model, PolyView view) {

        this.model = model;
        this.view = view;

        //adding the listeners to the buttons
        view.addAddListener(new AddListener());
        view.addSubListener(new SubListener());
        view.addMulListener(new MulListener());
        view.addDivListener(new DivListener());
        view.addDrvListener(new DrvListener());
        view.addItgListener(new ItgListener());

    }

    //implementing the action done by each listener for the buttons
    class AddListener implements ActionListener {

        @Override
        public void actionPerformed (ActionEvent e) {

            view.clearError();
            Polynomial pol1;
            Polynomial pol2;

            try {

                pol1 = model.extractPolynomials(view.getUserInput1());
                pol2 = model.extractPolynomials(view.getUserInput2());
            }
            catch (InputNotPolynomialException error) {

                view.showError(error.toString());
                pol1 = new Polynomial();
                pol2 = new Polynomial();
            }

            pol1.addPolynomials(pol2);
            view.updateDisplay("Addition result: " + pol1);

        }
    }

    class SubListener implements ActionListener {

        @Override
        public void actionPerformed (ActionEvent e) {

            view.clearError();
            Polynomial pol1;
            Polynomial pol2;

            try {

                pol1 = model.extractPolynomials(view.getUserInput1());
                pol2 = model.extractPolynomials(view.getUserInput2());
            }
            catch (InputNotPolynomialException error) {

                view.showError(error.toString());
                pol1 = new Polynomial();
                pol2 = new Polynomial();
            }

            pol1.subPolynomials(pol2);
            view.updateDisplay("Subtraction result: " + pol1);

        }

    }

    class MulListener implements ActionListener {

        @Override
        public void actionPerformed (ActionEvent e) {

            view.clearError();
            Polynomial pol1;
            Polynomial pol2;
            Polynomial result;

            try {

                pol1 = model.extractPolynomials(view.getUserInput1());
                pol2 = model.extractPolynomials(view.getUserInput2());
            }
            catch (InputNotPolynomialException error) {

                view.showError(error.toString());
                pol1 = new Polynomial();
                pol2 = new Polynomial();
            }

            result = pol1.mulPolynomials(pol2);
            view.updateDisplay("Multiplication result: " + result);

        }
    }

    class DivListener implements ActionListener {

        @Override
        public void actionPerformed (ActionEvent e) {

            view.clearError();
            Polynomial pol1;
            Polynomial pol2;
            ArrayList<Polynomial> result = new ArrayList<>();

            try {

                pol1 = model.extractPolynomials(view.getUserInput1());
                pol2 = model.extractPolynomials(view.getUserInput2());
            }
            catch (InputNotPolynomialException error) {

                view.showError(error.toString());
                pol1 = new Polynomial();
                pol2 = new Polynomial();
            }

            result = pol1.divPolynomials(pol2);

            try {

                pol1 = model.extractPolynomials(view.getUserInput1());
                pol2 = model.extractPolynomials(view.getUserInput2());
            }
            catch (InputNotPolynomialException error) {

                view.showError(error.toString());
                pol1 = new Polynomial();
                pol2 = new Polynomial();
            }

            if(result.get(0).mulPolynomials(pol2).equals(pol1)) {

                view.updateDisplay("Division result: Q: " + result.get(0));
            }
            else {

                view.updateDisplay("Division result: Q: " + result.get(0) + "       R: " + result.get(1));

            }
        }
    }

    class DrvListener implements ActionListener {

        @Override
        public void actionPerformed (ActionEvent e) {

            view.clearError();
            Polynomial pol1;

            try {

                pol1 = model.extractPolynomials(view.getUserInput1());
            } catch (InputNotPolynomialException error) {

                view.showError(error.toString());
                pol1 = new Polynomial();
            }

            Polynomial result = pol1.drvPolynomials();

            view.updateDisplay("Derivative result: " + result);
        }
    }

    class ItgListener implements ActionListener {

        @Override
        public void actionPerformed (ActionEvent e) {

            view.clearError();
            Polynomial pol1;

            try {

                pol1 = model.extractPolynomials(view.getUserInput1());
            }
            catch (InputNotPolynomialException error) {

                view.showError(error.toString());
                pol1 = new Polynomial();
            }

            Polynomial result = pol1.itgPolynomials();

            view.updateDisplay("Integral result: " + result);

        }
    }
}


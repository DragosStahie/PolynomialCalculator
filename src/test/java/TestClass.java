import model.Monomial;
import model.PolyModel;
import model.Polynomial;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class TestClass {

    private static PolyModel model;

    public TestClass() {

        model = new PolyModel();
    }

    @Test
    public void testAdunare() {

        Monomial m3 = new Monomial(3, 2);
        Monomial m2 = new Monomial(2, 3);
        Monomial m1 = new Monomial(1, 5);
        Monomial m0 = new Monomial(0, 6);

        ArrayList<Monomial> monList = new ArrayList<>();
        monList.add(m3);
        monList.add(m2);
        monList.add(m1);
        monList.add(m0);

        Polynomial pol1 = new Polynomial(monList);

        monList.clear();
        monList.add(m3);
        monList.add(m1);

        Polynomial pol2 = new Polynomial(monList);

        Monomial mm3 = new Monomial(3, 4);
        Monomial mm1 = new Monomial(1, 10);

        monList.clear();
        monList.add(mm3);
        monList.add(m2);
        monList.add(mm1);
        monList.add(m0);

        Polynomial result = new Polynomial(monList);
        pol1.addPolynomials(pol2);

        assertTrue("Invalid addition operation!", pol1.equals(result));
    }

    @Test
    public void testScadere() {

        Monomial m3 = new Monomial(3, 2);
        Monomial m2 = new Monomial(2, 3);
        Monomial m1 = new Monomial(1, 5);
        Monomial m0 = new Monomial(0, 6);

        ArrayList<Monomial> monList = new ArrayList<>();
        monList.add(m3);
        monList.add(m2);
        monList.add(m1);
        monList.add(m0);

        Polynomial pol1 = new Polynomial(monList);

        monList.clear();
        monList.add(m3);
        monList.add(m1);

        Polynomial pol2 = new Polynomial(monList);

        monList.clear();
        monList.add(m2);
        monList.add(m0);

        Polynomial result = new Polynomial(monList);
        pol1.subPolynomials(pol2);

        assertTrue("Invalid subtraction operation!", pol1.equals(result));
    }

    @Test
    public void testInmultire() {

        Monomial m3 = new Monomial(3, 2);
        Monomial m2 = new Monomial(2, 3);
        Monomial m1 = new Monomial(1, 5);
        Monomial m0 = new Monomial(0, 6);

        ArrayList<Monomial> monList = new ArrayList<>();
        monList.add(m3);
        monList.add(m2);
        monList.add(m1);
        monList.add(m0);

        Polynomial pol1 = new Polynomial(monList);

        monList.clear();
        monList.add(m1);

        Polynomial pol2 = new Polynomial(monList);

        Monomial mm3 = new Monomial(4, 10);
        Monomial mm2 = new Monomial(3, 15);
        Monomial mm1 = new Monomial(2, 25);
        Monomial mm0 = new Monomial(1, 30);

        monList.clear();
        monList.add(mm3);
        monList.add(mm2);
        monList.add(mm1);
        monList.add(mm0);

        Polynomial result = new Polynomial(monList);

        assertTrue("Invalid multiplication operation!", result.equals(pol1.mulPolynomials(pol2)));
    }

    @Test
    public void testImpartire() {

        Monomial mm3 = new Monomial(4, 10);
        Monomial mm2 = new Monomial(3, 15);
        Monomial mm1 = new Monomial(2, 25);
        Monomial mm0 = new Monomial(1, 30);

        Monomial m3 = new Monomial(3, 2);
        Monomial m2 = new Monomial(2, 3);
        Monomial m1 = new Monomial(1, 5);
        Monomial m0 = new Monomial(0, 6);

        ArrayList<Monomial> monList = new ArrayList<>();
        monList.add(mm3);
        monList.add(mm2);
        monList.add(mm1);
        monList.add(mm0);

        Polynomial pol1 = new Polynomial(monList);

        monList.clear();
        monList.add(m1);

        Polynomial pol2 = new Polynomial(monList);

        monList.clear();
        monList.add(m3);
        monList.add(m2);
        monList.add(m1);
        monList.add(m0);

        Polynomial result = new Polynomial(monList);

        assertTrue("Invalid division operation!", result.equals(pol1.divPolynomials(pol2).get(0)));
    }

    @Test
    public void testDerivare() {

        Monomial m3 = new Monomial(3, 2);
        Monomial m2 = new Monomial(2, 3);
        Monomial m1 = new Monomial(1, 5);
        Monomial m0 = new Monomial(0, 6);

        ArrayList<Monomial> monList = new ArrayList<>();
        monList.add(m3);
        monList.add(m2);
        monList.add(m1);
        monList.add(m0);

        Polynomial pol1 = new Polynomial(monList);

        Monomial mm3 = new Monomial(2, 6);
        Monomial mm2 = new Monomial(1, 6);
        Monomial mm1 = new Monomial(0, 5);

        monList.clear();
        monList.add(mm3);
        monList.add(mm2);
        monList.add(mm1);

        Polynomial result = new Polynomial(monList);

        assertTrue("Invalid derivative operation!", result.equals(pol1.drvPolynomials()));
    }

    @Test
    public void testIntegrare() {

        Monomial mm3 = new Monomial(2, 6);
        Monomial mm2 = new Monomial(1, 6);
        Monomial mm1 = new Monomial(0, 5);

        ArrayList<Monomial> monList = new ArrayList<>();
        monList.add(mm3);
        monList.add(mm2);
        monList.add(mm1);

        Polynomial pol1 = new Polynomial(monList);

        Monomial m3 = new Monomial(3, 2);
        Monomial m2 = new Monomial(2, 3);
        Monomial m1 = new Monomial(1, 5);

        monList.clear();
        monList.add(m3);
        monList.add(m2);
        monList.add(m1);

        Polynomial result = new Polynomial(monList);

        assertTrue("Invalid integral operation!", result.equals(pol1.itgPolynomials()));
    }
}

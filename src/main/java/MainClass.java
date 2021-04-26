import controller.PolyController;
import model.PolyModel;
import view.PolyView;

import javax.swing.*;

public class MainClass {

    public static void main(String[] args) {

        PolyModel model		= new PolyModel();
        PolyView view		= new PolyView(model);
        PolyController controller	= new PolyController(model, view);

        view.setVisible(true);
        view.setLocationRelativeTo(null);
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}

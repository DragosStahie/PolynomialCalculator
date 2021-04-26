package view;

import model.PolyModel;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class PolyView extends JFrame{

    private JPanel		panel1			= new JPanel();
    private JPanel		panel2			= new JPanel();
    private JPanel		buttonPanel		= new JPanel();
    private JPanel		content			= new JPanel();
    private JLabel		polTag1			= new JLabel("Polinom 1:");
    private JLabel		polTag2			= new JLabel("Polinom 2:");
    private JTextField	polinom1		= new JTextField();
    private JTextField	polinom2		= new JTextField();
    private JButton		addButton		= new JButton("Add");
    private JButton		subButton		= new JButton("Sub");
    private JButton		mulButton		= new JButton("Mul");
    private JButton		divButton		= new JButton("Div");
    private JButton		drvButton		= new JButton("Drv");
    private JButton		itgButton		= new JButton("Itg");
    private JTextArea	afisaj			= new JTextArea();
    private JLabel 		errorMsg		= new JLabel("Input Error!");


    private PolyModel model;

    public PolyView (PolyModel model) {

        //setting the layout of the graphical interface
        this.model = model;

        errorMsg.setForeground(Color.red);
        errorMsg.setFont(new Font("", Font.BOLD, 18));
        errorMsg.setVisible(false);

        afisaj.setFont(new Font("", Font.LAYOUT_LEFT_TO_RIGHT, 18));
        afisaj.setEditable(false);


        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(addButton);
        addButton.setFont(new Font("", Font.BOLD, 16));
        addButton.setPreferredSize(new Dimension(80, 30));

        buttonPanel.add(subButton);
        subButton.setFont(new Font("", Font.BOLD, 16));
        subButton.setPreferredSize(new Dimension(80, 30));

        buttonPanel.add(mulButton);
        mulButton.setFont(new Font("", Font.BOLD, 16));
        mulButton.setPreferredSize(new Dimension(80, 30));

        buttonPanel.add(divButton);
        divButton.setFont(new Font("", Font.BOLD, 16));
        divButton.setPreferredSize(new Dimension(80, 30));

        buttonPanel.add(drvButton);
        drvButton.setFont(new Font("", Font.BOLD, 16));
        drvButton.setPreferredSize(new Dimension(80, 30));

        buttonPanel.add(itgButton);
        itgButton.setFont(new Font("", Font.BOLD, 16));
        itgButton.setPreferredSize(new Dimension(80, 30));

        panel1.setLayout(new GridLayout(0, 1, 0, 8));
        polTag1.setFont(new Font("", Font.BOLD, 16));
        polTag2.setFont(new Font("", Font.BOLD, 16));
        panel1.add(polTag1);
        panel1.add(polinom1);
        panel1.add(polTag2);
        panel1.add(polinom2);
        panel1.add(buttonPanel);

        panel2.setLayout(new GridLayout(0,1));
        panel2.add(afisaj);

        panel2.setPreferredSize(new Dimension(600, 50));

        content.setLayout(new BorderLayout());
        content.setBorder(new EmptyBorder(5, 30, 30, 30));
        content.add(panel1, BorderLayout.NORTH);
        content.add(errorMsg, BorderLayout.CENTER);
        content.add(panel2, BorderLayout.SOUTH);

        content.setPreferredSize(new Dimension(600, 400));

        this.setTitle("Model.Polynomial Calculator pe felie");
        this.setContentPane(content);
        this.pack();
    }

    //reading user input
    public String getUserInput1() {

        return polinom1.getText();
    }

    public String getUserInput2() {

        return polinom2.getText();
    }

    //updating the display
    public void updateDisplay(String displayInfo) {

        afisaj.setText(displayInfo);
    }

    //methods for adding listeners to the buttons
    public void addAddListener(ActionListener listener) {

        addButton.addActionListener(listener);
    }

    public void addSubListener(ActionListener listener) {

        subButton.addActionListener(listener);
    }

    public void addMulListener(ActionListener listener) {

        mulButton.addActionListener(listener);
    }

    public void addDivListener(ActionListener listener) {

        divButton.addActionListener(listener);
    }
    public void addDrvListener(ActionListener listener) {

        drvButton.addActionListener(listener);
    }
    public void addItgListener(ActionListener listener) {

        itgButton.addActionListener(listener);
    }


    //method for showing the error message
    public void showError(String msg) {

        errorMsg.setText("<html>" + msg + "</html>");
        errorMsg.setVisible(true);
    }

    public void clearError() {

        errorMsg.setVisible(false);
    }
}


package registrationapp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;


public class client implements ActionListener {

    String spinnerR;
    
    JTextField textID,textFN,textSur,textAge,textCNo;
    JSpinner spinner;

    public client() {

        JPanel panel = new JPanel();
        JFrame frame = new JFrame("Pearson Institute Registration App");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(panel);

        panel.setLayout(null);

        JLabel label = new JLabel("PIHE Registration App v1.0");
        label.setBounds(10, 20, 165, 25);
        panel.add(label);

        JLabel labelDev = new JLabel("Developer: A.J.Potgieter");
        labelDev.setBounds(200, 20, 165, 25);
        panel.add(labelDev);

        JLabel labelID = new JLabel("ID Number:");
        labelID.setBounds(10, 50, 165, 25);
        panel.add(labelID);

        textID = new JTextField(20);
        textID.setBounds(200, 50, 165, 25);
        panel.add(textID);

        JLabel labelFN = new JLabel("First Name:");
        labelFN.setBounds(10, 80, 165, 25);
        panel.add(labelFN);

        textFN = new JTextField(20);
        textFN.setBounds(200, 80, 165, 25);
        panel.add(textFN);

        JLabel labelSur = new JLabel("Surname:");
        labelSur.setBounds(10, 110, 165, 25);
        panel.add(labelSur);

        textSur = new JTextField(20);
        textSur.setBounds(200, 110, 165, 25);
        panel.add(textSur);

        JLabel labelAge = new JLabel("Age:");
        labelAge.setBounds(10, 140, 165, 25);
        panel.add(labelAge);

        textAge = new JTextField(20);
        textAge.setBounds(200, 140, 165, 25);
        panel.add(textAge);

        JLabel labelCNo = new JLabel("Cell Number:");
        labelCNo.setBounds(10, 170, 165, 25);
        panel.add(labelCNo);

        textCNo = new JTextField(20);
        textCNo.setBounds(200, 170, 165, 25);
        panel.add(textCNo);

        JLabel labelSDegree = new JLabel("Select Degree:");
        labelSDegree.setBounds(10, 200, 165, 25);
        panel.add(labelSDegree);

        String degrees[] = {"BSC IT", "BSc Computer Science", "Higher Certificate in IT", "BSc Biomedicine", "Bachelor of Commerce", "Bachelor of Arts"};

        spinner = new JSpinner(new SpinnerListModel(degrees));
        ((DefaultEditor) spinner.getEditor()).getTextField().setEditable(false);
        spinner.setBounds(200, 200, 165, 25);
        panel.add(spinner);

        JButton buttonStart = new JButton("Register");
        buttonStart.setBounds(10, 230, 165, 25);
        panel.add(buttonStart);
        buttonStart.addActionListener(this);

        frame.setVisible(true);
    }

    public static void main(String[] args) {

        new client();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
//register button pressed
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rmiTest","root","root");
            Statement st = con.createStatement();
            
            int ID = Integer.parseInt(textID.getText());
            String name = textFN.getText();
            String surname = textSur.getText();
            int age = Integer.parseInt(textAge.getText());
            int cellNo = Integer.parseInt(textCNo.getText());
            String degree = spinner.getValue().toString();
            
            ConnectInterface dbi = (ConnectInterface)Naming.lookup("rmi://localhost:4444/db");
            
            String result = dbi.Insert(ID, name, surname, age, cellNo, degree);
            JOptionPane.showMessageDialog(null, result, "Success",JOptionPane.INFORMATION_MESSAGE);
            
        }catch(Exception e){//new
            
            JOptionPane.showMessageDialog(null, "Error, please fill all the fields", "Error",JOptionPane.ERROR_MESSAGE);
            
            
        }
    }

}

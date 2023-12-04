import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import System.*;

public class LogForm extends JDialog {

    Repositary repositary = new Repositary();
    private JPasswordField passwordField;
    private JTextField email;
    private JPanel LoginPanel;
    private JCheckBox rememberMeCheckBox;
    private JButton loginbtn;
    private JLabel forgetPw;
    private JButton CancelBtn;

    public LogForm(JFrame parent){
        super(parent);
        setTitle("Login");
        setContentPane(LoginPanel);
        setMinimumSize(new Dimension(800, 500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        loginbtn.setFocusable(false);
        CancelBtn.setFocusable(false);


        loginbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = email.getText(); //Store username entered by the user in the variable "username"
                String password = passwordField.getText(); //Store password entered by the user in the variable "password"

                Connection connection = repositary.setConnection();

                if(username.equals("")) //If username is null
                {
                    JOptionPane.showMessageDialog(null,"Please enter username"); //Display dialog box with the message
                }
                else if(password.equals("")) //If password is null
                {
                    JOptionPane.showMessageDialog(null,"Please enter password"); //Display dialog box with the message
                }
                else {

                    try {
                        boolean logInSuccessfully = false;
                        String sqlStr = "SELECT * FROM admin WHERE username = ?";
                        PreparedStatement preparedStatement = connection.prepareStatement(sqlStr);
                        preparedStatement.setString(1, username);
                        ResultSet resultSet = preparedStatement.executeQuery();

                        if(resultSet.next()){
                            String storePassword = resultSet.getString("password");
                            if(storePassword.equals(password)){
                                logInSuccessfully = true;
                            }
                        }

                        if(logInSuccessfully){
                            dispose();
                            new Menu(null);
                        }else {
                            JOptionPane.showMessageDialog(null, "Incorrect Password or Username!");
                        }

                        preparedStatement.close();
                        resultSet.close();


                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        CancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        setVisible(true);
    }
}

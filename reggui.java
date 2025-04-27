import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class reggui  extends JFrame  {

    private Controller controller ;
   public reggui() {
       try {

           String dbUrl = "jdbc:mysql://localhost:3306/tictic";
           String dbUsername = "root";
           String dbPassword = "";

           controller = new Controller(dbUrl, dbUsername, dbPassword);
       } catch (SQLException e) {
           e.printStackTrace();
           JOptionPane.showMessageDialog(null, "Failed to connect to database: " + e.getMessage());
       }
   }

    public void RegClass(){

        setTitle("Registration");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1, 10, 10));

        //username
        JPanel usernamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(15);

        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameField);

        //symbol
        JPanel symbolPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));

        JLabel symbolLabel = new JLabel("Symbol:");
        JTextField symbolField = new JTextField(3);

       symbolPanel.add(symbolLabel);
       symbolPanel.add(symbolField);

        //password

        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(15);

        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);


        //button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton regButton = new JButton("Register");
        buttonPanel.add(regButton);


        //backbutton

        JPanel backbuttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton back = new JButton("Back");
        backbuttonPanel.add(back);

        //add all to the main frame
        add(usernamePanel);
        add(symbolPanel);
        add(passwordPanel);
        add(buttonPanel);
        add(backbuttonPanel);


        regButton.addActionListener(e -> {
            try {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                char symbol = symbolField.getText().charAt(0);

                boolean success = controller.registerUser(username, password, symbol);


                if (success) {
                    JOptionPane.showMessageDialog(this, "Registration Successful!");
                    // now login then start playing
                    dispose();
                    logingui log = new logingui();
                    log.LoginClass();

                } else {
                    JOptionPane.showMessageDialog(this, "Registration Failed!");

                    add(backbuttonPanel);
                    back.addActionListener(ea -> {
                        dispose();
                        gameacess deo =new gameacess();
                        deo.ac();

                    });
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });


        setLocationRelativeTo(null);
        setVisible(true);


    }


}

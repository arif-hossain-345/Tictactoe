import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class logingui extends JFrame {
    Controller controller;

    public logingui() {
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

    public void LoginClass() {
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1, 10, 10)); // 3 rows: Username row, Password row, Button row

        // Username Row
        JPanel usernamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(15);
        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameField);

        // Password Row
        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 7, 13));
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(15);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);

        // Button Row
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton loginButton = new JButton("Login");
        buttonPanel.add(loginButton);

        // Back Button Row
        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton back = new JButton("Back");
        backButtonPanel.add(back);

        // Add all to main frame
        add(usernamePanel);
        add(passwordPanel);
        add(buttonPanel);
        add(backButtonPanel);

        // Action for login button
        loginButton.addActionListener(e -> {
            try {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Validate login
                boolean success = controller.loginUser(username, password);

                if (success) {
                    JOptionPane.showMessageDialog(this, "Login Successful!");
                    dispose();  // Close the login window

                    // Create a new game and start the game
                    char playerSymbol = controller.getUserSymbol(username);
                    char computerSymbol = (playerSymbol == 'X') ? 'O' : 'X';
                    Board board = new Board();
                    Game game = new Game(username, playerSymbol, computerSymbol, board);
                    game.gamestart();

                } else {
                    JOptionPane.showMessageDialog(this, "Login Failed!");

                    // Action for back button // Close the login window // go back to the previous screen
                    back.addActionListener(ea -> {
                        dispose();
                        gameacess deo = new gameacess();
                        deo.ac();

                    });
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });




        // Center the window
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

import java.sql.*;

public class Controller {

    private Connection connection;

    // Constructor to initialize DB connection
    public Controller(String dbUrl, String username, String password) throws SQLException {
        this.connection = DriverManager.getConnection(dbUrl, username, password);
        createTables();
    }

    // Create the necessary tables if they don't exist
    private void createTables() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("CREATE TABLE IF NOT EXISTS Users (" +
                "user_id INT AUTO_INCREMENT PRIMARY KEY, " +
                "username VARCHAR(255) UNIQUE NOT NULL, " +
                "password_hash VARCHAR(255) NOT NULL" +
                ")");

        stmt.close();
    }

    // Method to hash the password
    private String hashPassword(String password) {
        return Integer.toHexString(password.hashCode()); // Simple hash, replace with bcrypt in production
    }

    // Register new user
    public boolean registerUser(String username, String password,char symbol) throws SQLException {
        String passwordHash = hashPassword(password);
        String query = "INSERT INTO Users (username, password_hash,symbol) VALUES (?, ?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, passwordHash);
            stmt.setString(3, String.valueOf(symbol)); // Save the symbol
            stmt.executeUpdate();
            return true; // Registration successful
        } catch (SQLIntegrityConstraintViolationException e) {
            return false; // Username already exists
        }
    }


    // Login a user
    public boolean loginUser(String username, String password) throws SQLException {
        String query = "SELECT password_hash FROM Users WHERE username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String storedPasswordHash = rs.getString("password_hash");
                if(storedPasswordHash.equals(hashPassword(password))){
                    return true;

                }
                rs.close();
            } else {
                return false;
            }
        }
        return false;
    }






    public char getUserSymbol(String username) throws SQLException {
        String query = "SELECT symbol FROM Users WHERE username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("symbol").charAt(0);
            } else {
                throw new SQLException("User not found!");
            }
        }
    }


    // Close the database connection
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}

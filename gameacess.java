import javax.swing.*;
import java.awt.*;
public class gameacess extends JFrame {

    public gameacess() {}

    public void ac(){
        setTitle("Welcome to Tic-Tac ");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3,3,10,10));


JPanel p1=new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel l1 = new JLabel("Register ? or Already have an account ?");
p1.add(l1);

        //creating buttons for login or reg

        JPanel logbutton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton login = new JButton("Login");
        logbutton.add(login);
        JPanel rbutton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton registration = new JButton("Registration");
        rbutton.add(registration);

        //adding them to the main frame
        add(p1);
        add(logbutton);
        add(rbutton);

        login.addActionListener(e -> {
      dispose();
            logingui log = new logingui();
            log.LoginClass();

        });

        registration.addActionListener(e -> {
            dispose();
            reggui regg = new reggui();
            regg.RegClass();
        });

        setLocationRelativeTo(null);
        setVisible(true);




    }

}

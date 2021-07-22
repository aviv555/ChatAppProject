package View;
import Controller.Controller;
import Client.dm.User_In_Client;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Log_In_FrameView extends JFrame implements ActionListener {
    private static final JFrame frame = new JFrame();
    private static final JLayeredPane layeredPane = new JLayeredPane();
    private static final Controller controller = new Controller();
    private static final JTextField text_field = new JTextField(10);
    private static final JLabel image_label = new JLabel();
    private static final JPanel log_in_panel = new JPanel();
    private static final JButton log_in_button = new JButton();
    private static final ImageIcon logo = new ImageIcon("src/View/Graphics/logo.png");
    private static final ImageIcon background_image = new ImageIcon("src/View/Graphics/loginBackground.png");

    //sets background image aka image label
    private static void set_background() {
        image_label.setIcon(background_image);
        image_label.setBackground(Color.black);
        image_label.setBorder(BorderFactory.createLineBorder(Color.black));
        image_label.setOpaque(true);
        image_label.setBounds(0,0,535,565);
    }

    private static void set_log_in_panel() {
        log_in_panel.setOpaque(true);
        log_in_panel.setBounds(50,260,300,40);
        log_in_panel.setBorder(BorderFactory.createBevelBorder(1));
        log_in_panel.setBackground(Color.pink);
        JLabel label = new JLabel("PHONE NUMBER");
        label.setBounds(10, 20, 100, 25);
        log_in_panel.add(label);
        text_field.setBackground(Color.black);
        text_field.setBounds(120, 20, 165, 25);
        text_field.setOpaque(false);
        log_in_panel.add(text_field);
        log_in_button.setText("Log In");
        log_in_button.setBounds(295, 20, 100, 25);
        log_in_button.addActionListener(new Log_In_FrameView());
        log_in_panel.add(log_in_button);
    }

    private static void set_layers() {
        layeredPane.setBounds(0,0,535,570);
        layeredPane.add(log_in_panel);
        layeredPane.add(image_label);
    }

    private static void set_frame() {
        frame.setTitle("CHAT APP");
        frame.setIconImage(logo.getImage());
        frame.setLayout(null);
        frame.setSize(new Dimension(535,595));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(layeredPane);
    }

    public static void execute_log_in_page() {
        set_background();
        set_log_in_panel();
        set_layers();
        set_frame();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        User_In_Client user = new User_In_Client();
        String text = text_field.getText();

        if (text.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "LOG IN FAILED");
            return;
        }

        if (e.getSource() == log_in_button) {
            try {
                user = controller.log_in_request_to_server(Long.parseLong(text));
            } catch (IOException | ClassNotFoundException exception) {
                exception.printStackTrace();
            }

            if (user == null)
                JOptionPane.showMessageDialog(frame, "LOG IN FAILED");
            else {
                JOptionPane.showMessageDialog(frame, "LOG IN SUCCESS");
                WriteAndReadFrameView.execute(user);
                frame.dispose();
            }
        }
    }

    public static void main(String[] args) {
        Log_In_FrameView.execute_log_in_page();
    }
}

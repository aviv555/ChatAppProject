package View;
import Controller.Controller;
import Client.dm.User_In_Client;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class WriteAndReadFrameView extends JFrame implements ActionListener {
    private static User_In_Client user = new User_In_Client();
    private static final Controller controller = new Controller();
    private static final JFrame frame = new JFrame();

    private static JScrollPane contact_list_panel;

    private static final JPanel write_and_read_panel = new JPanel();

    private static final JPanel write_panel = new JPanel();
    private static final JButton send_button = new JButton("SEND");
    private static JScrollPane write_text_field_panel;
    private static final JTextArea write_text_field = new JTextArea();
    private static final JTextField write_contact_field = new JTextField();

    private static final JPanel read_panel = new JPanel();
    private static final JButton read_button = new JButton("READ");
    private static final JTextField read_contact_field = new JTextField();

    private static final JTextArea contact_text_area = new JTextArea();

    private static void set_contact_panel() {
        Font font = new Font("", Font.BOLD, 20);
        contact_text_area.setFont(font);
        contact_text_area.setText("Contacts\n -------------------\n" + user.contacts_as_string());
        contact_text_area.setEditable(false);
        contact_text_area.setBackground(Color.pink);

        contact_list_panel = new JScrollPane(contact_text_area);
        contact_list_panel.setPreferredSize(new Dimension(160, 100));
        contact_list_panel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    }

    private static void set_read_panel() {
        read_panel.setOpaque(true);
        read_panel.setLayout(new BorderLayout(10,0));
        read_panel.setBorder(BorderFactory.createBevelBorder(1));
        read_panel.setBackground(Color.pink);
        JLabel label = new JLabel("Read From:");
        label.setPreferredSize(new Dimension(100,30));
        read_panel.add(label, BorderLayout.WEST);
        read_contact_field.setBackground(Color.black);
        read_contact_field.setPreferredSize(new Dimension(100,30));
        read_contact_field.setOpaque(false);
        read_panel.add(read_contact_field, BorderLayout.CENTER);
        read_button.setPreferredSize(new Dimension(100,30));
        read_button.addActionListener(new WriteAndReadFrameView());
        read_panel.add(read_button, BorderLayout.EAST);
    }

    private static void set_write_panel() {
        write_panel.setOpaque(true);
        write_panel.setLayout(new BorderLayout(10,0));
        write_panel.setBorder(BorderFactory.createBevelBorder(1));
        write_panel.setBackground(Color.pink);
        JLabel label = new JLabel("Write To:");
        label.setPreferredSize(new Dimension(100,30));
        write_panel.add(label, BorderLayout.WEST);

        write_contact_field.setBackground(Color.black);
        write_contact_field.setPreferredSize(new Dimension(100,30));
        write_contact_field.setOpaque(false);
        write_panel.add(write_contact_field, BorderLayout.CENTER);
        send_button.setPreferredSize(new Dimension(100,30));
        send_button.addActionListener(new WriteAndReadFrameView());
        write_panel.add(send_button, BorderLayout.EAST);
    }

    private static void set_text_area_panel() {
        write_text_field.setFont(new Font("", Font.ITALIC, 20));
        write_text_field_panel = new JScrollPane(write_text_field);
        write_text_field.setLineWrap(true);
        write_text_field.setWrapStyleWord(true);
        write_text_field_panel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    }

    private static void set_write_and_read_panel() {
        set_write_panel();
        set_read_panel();
        set_text_area_panel();
        write_and_read_panel.setLayout(new BorderLayout());
        write_and_read_panel.add(write_panel, BorderLayout.NORTH);
        write_and_read_panel.add(read_panel, BorderLayout.SOUTH);
        write_and_read_panel.add(write_text_field_panel, BorderLayout.CENTER);
    }

    private static void set_frame() {
        frame.setLayout(new BorderLayout());
        frame.setIconImage(new ImageIcon("src/View/Graphics/logo.png").getImage());
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(contact_list_panel, BorderLayout.WEST);
        frame.add(write_and_read_panel, BorderLayout.EAST);
    }
    //main page
    public static void execute(User_In_Client connected_user) {
        user = connected_user;
        set_contact_panel();
        set_write_and_read_panel();
        set_frame();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ((e.getSource().equals(send_button))) {
            long source = user.getPhone_number();

            String target = write_contact_field.getText();
            Long target_phone_number = user.getContacts().get(target);
            if (target_phone_number == null) {
                JOptionPane.showMessageDialog(frame, "NO CONTACT FOUND");

                return;
            }

            String message = write_text_field.getText();

            if (message.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "ERROR! EMPTY MESSAGE");
                return;
            }

            try {
                if (controller.send_message_request_to_server(source, target_phone_number, message)) {
                    JOptionPane.showMessageDialog(frame, "THE MESSAGE HAS BEEN SENT!");
                } else {
                    JOptionPane.showMessageDialog(frame, "ERROR! THE MESSAGE HAS NOT BEEN SENT");

                }
            } catch (IOException | ClassNotFoundException ioException) {
                ioException.printStackTrace();
                return;
            }
        }
        if (e.getSource() == read_button) {
            long source = user.getPhone_number();

            String target = read_contact_field.getText();
            Long target_phone_number = user.getContacts().get(target);
            if (target_phone_number == null) {
                JOptionPane.showMessageDialog(frame, "NO CONTACT FOUND");
                return;
            }

            try {
                String session = controller.read_conversation_request_to_server(source, target_phone_number);
                    ConversationView conversationView = new ConversationView();
                    conversationView.execute(session);
            } catch (IOException | ClassNotFoundException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}

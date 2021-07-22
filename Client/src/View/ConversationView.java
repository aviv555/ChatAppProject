package View;
import javax.swing.*;
import java.awt.*;

public class ConversationView extends JFrame {
            public void execute(String conversation) {
                JFrame frame = new JFrame();
                frame.setIconImage(new ImageIcon("src/View/Graphics/logo.png").getImage());
                frame.setSize(new Dimension(250,250));
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setResizable(true);

                JTextArea textArea = new JTextArea();
                textArea.setBackground(Color.pink);
                textArea.setFont(new Font("", Font.ITALIC, 14));
                textArea.setText(conversation);
                textArea.setEditable(false);

                JScrollPane panel = new JScrollPane(textArea);
                panel.setBorder(BorderFactory.createBevelBorder(1));
                panel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

                frame.add(panel);
                frame.setVisible(true);
            }
}

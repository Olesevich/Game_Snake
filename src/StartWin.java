import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartWin extends JPanel {

    private JButton b1 ,b2;
    private JLabel l1;
    Start st = new Start();


    public StartWin(){
        setBackground(Color.GREEN);
        l1 = new JLabel("Хотите сыграть в змейку?",SwingConstants.CENTER);
        b1 = new JButton("Да");
        b2 = new JButton("Нет");
        b1.setPreferredSize(new Dimension(100,50));
        b2.setPreferredSize(new Dimension(100,50));
        b1.setBackground(Color.ORANGE);
        b2.setBackground(Color.ORANGE);
        l1.setFont(new Font("Arial",Font.BOLD,20));
        l1.setPreferredSize(new Dimension(300,200));
        add(l1);
        add(b1);
        add(b2);
        b1.addActionListener(st);
        b2.addActionListener(st);
    }

    class Start implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == b1) {
                MainWindows mw = new MainWindows();
                setVisible(false);
            }
            if (e.getSource() == b2) {
                setVisible(false);
                System.exit(0);

            }
        }
    }
}

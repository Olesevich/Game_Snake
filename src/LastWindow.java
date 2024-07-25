import javax.swing.*;

public class LastWindow extends JFrame {

    public LastWindow(){
        setTitle("Змейка");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(367,454);
        setResizable(false);
        setLocationRelativeTo(null);
        add(new LastWin());
        setVisible(true);
    }
}

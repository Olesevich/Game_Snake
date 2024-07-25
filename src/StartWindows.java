import javax.swing.*;

public class StartWindows extends JFrame {


    public StartWindows(){

        setTitle("Змейка");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(367,454);  //390
        setResizable(false);
        setLocationRelativeTo(null);
        add(new StartWin());
        setVisible(true);

    }
}

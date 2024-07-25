import javax.swing.*;
import java.awt.*;

public class MainWindows extends JFrame {


    public MainWindows(){
        setTitle("Змейка");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(367,454);//370,390
        setResizable(false);
        setLocationRelativeTo(null);
        add(new GameField());
        setVisible(true);
    }

    public static void main(String[] args) {
        StartWindows sw = new StartWindows();


    }
}

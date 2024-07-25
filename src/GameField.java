import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GameField extends JPanel implements ActionListener {

    private final int SIZE = 320;
    private final int DOT_SIZE = 16;
    private final int ALL_DOTS = 400;
    private Image dot;
    private Image apple;
    private Image sten;
    private int appleX;
    private int appleY;
    private int [] x = new int [ALL_DOTS];
    private int [] y = new int [ALL_DOTS];
    private int dots;
    private Timer timer;
    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;
    private boolean inGame = true;
    private JLabel l1, l2, l3, l4;
    public   int levelUp = 1;
    public int sum;
    public String a,b;

    public GameField(){
        setBackground(Color.GREEN);
        a = String.valueOf(levelUp);
        l1 = new JLabel("Уровень: ");
        l1.setFont(new Font("Arial",Font.BOLD,16));
        l1.setPreferredSize(new Dimension(90,60));
        l1.setForeground(Color.RED);
        l2 = new JLabel(" ");
        l2.setFont(new Font("Arial",Font.BOLD,16));
        l2.setPreferredSize(new Dimension(50,60));
        l3 = new JLabel("Сумма: ");
        l3.setFont(new Font("Arial",Font.BOLD,16));
        l3.setPreferredSize(new Dimension(90,60));
        l3.setForeground(Color.RED);
        l4 = new JLabel(" ");
        l4.setFont(new Font("Arial",Font.BOLD,16));
        l4.setPreferredSize(new Dimension(50,60));
        add(l1);
        add(l2);
        add(l3);
        add(l4);
        loadImage();
        initGame();
        addKeyListener(new FieldKeyListener());
        setFocusable(true);
    }

    public void initGame(){  //создаем змейку длиной три, и начало ее пути
        dots = 3;
        for (int i = 0; i < dots; i++) {
            x[i] = 80 - i * DOT_SIZE;
            y[i] = 176;
        }
        timer = new Timer(250, this); //время обнавления игр. поля
        timer.start();
        createApple();
    }

    public void createApple(){  //создание и размещение рандом яблока
        appleX = new Random().nextInt(20)*DOT_SIZE+16;
        appleY = new Random().nextInt(20)*DOT_SIZE+80;
    }

    public void loadImage(){ //загрузка картинки яблоко и змейки
        ImageIcon iia = new ImageIcon("apple.png");
        apple = iia.getImage();
        ImageIcon iid = new ImageIcon("dot.png");
        dot = iid.getImage();
        ImageIcon iis = new ImageIcon("apple.png");
        sten = iis.getImage();
    }

    public void move(){  //движение змейки...сначало тело звейки, потом голова
        for (int i = dots; i > 0 ; i--) {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
        if(left){
            x[0] -= DOT_SIZE;
        }
        if(right){
            x[0] += DOT_SIZE;
        }
        if(up){
            y[0] -= DOT_SIZE;
        }
        if(down){
            y[0] += DOT_SIZE;
        }

    }


    public void levelOfDifficulty (){
        if (dots == 8){
            timer.stop();
            timer = new Timer(220, this);//220
            timer.start();
            levelUp = 2;
        }
        if (dots == 13){
            timer.stop();
            timer = new Timer(190, this);//190
            timer.start();
            levelUp = 3;
        }
        if (dots == 18){
            timer.stop();
            timer = new Timer(150, this);
            timer.start();
            levelUp = 4;
        }
        if (dots == 23){
            timer.stop();
            timer = new Timer(110, this);
            timer.start();
            levelUp = 5;
        }
        if (dots == 28){
            timer.stop();
            timer = new Timer(90, this);
            timer.start();
            levelUp = 6;
        }
        if (dots == 32){
            timer.stop();
            timer = new Timer(70, this);
            timer.start();
            levelUp = 7;
        }
        if (dots == 35){
            timer.stop();
            timer = new Timer(55, this);
            timer.start();
            levelUp = 8;
        }
        a = String.valueOf(levelUp);
        l2.setText(a);
    }


    public void ramka(Graphics g){
        for (int i = 0; i < 22; i++) {
            g.drawImage(sten,i*16 ,0,this);
            g.drawImage(sten,i*16 ,400,this);
            g.drawImage(sten,i*16 ,64,this);
        }
        for (int i = 0; i < 25; i++) {
            g.drawImage(sten,0 ,i*16,this);
            g.drawImage(sten,336 ,i*16,this);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(inGame){
            ramka(g);
            g.drawImage(apple,appleX,appleY,this);
            for (int i = 0; i < dots; i++) {
                g.drawImage(dot, x[i],y[i],this);
            }
        }
        else {  // создает надпись что игра окончина
            timer.stop();
            setVisible(false);
            LastWindow lw = new LastWindow();
        }
        repaint();
    }

    public void checkApple(){ //добавляет змейки хвост и создает новое яблоко
        if(x[0] == appleX && y[0] == appleY){
            dots++;
            sum++;
            b = String.valueOf(sum);
            l4.setText(b);
            createApple();
        }
    }

    public void checColisions(){  //конец игры если змейка сталнулась или вышла
        for (int i = dots; i > 0; i--) {
            if(i > 4 && x[0] == x[i] && y[0] == y[i]){
                inGame = false;
            }
        }
        if(x[0]> 330){
            inGame = false;
        }
        if(x[0] < 16){
            inGame = false;
        }
        if(y[0]> 384){
            inGame = false;
        }
        if(y[0] < 80){
            inGame = false;
        }

    }

    @Override  //
    public void actionPerformed(ActionEvent e) {
        if(inGame){
            checkApple();
            checColisions();
            move();
            levelOfDifficulty();
        }
        repaint();
    }

    class  FieldKeyListener extends KeyAdapter{  //змейка дижется от кнопок
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_LEFT && !right){
                left = true;
                up = false;
                down = false;
            }
            if(key == KeyEvent.VK_RIGHT && !left){
                right = true;
                up = false;
                down = false;
            }
            if(key == KeyEvent.VK_UP && !down){
                up = true;
                right = false;
                left = false;
            }
            if(key == KeyEvent.VK_DOWN && !up){
                left = false;
                right = false;
                down = true;
            }
        }
    }
}

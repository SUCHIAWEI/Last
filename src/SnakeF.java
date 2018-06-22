import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class SnakeF extends JFrame {
    private Container cp;


    int times , scores ;
    int rows =16, cols =25;
    private JButton run = new JButton("Start");
    private JButton re = new JButton("Again");
    private JButton exit = new JButton("Exit");
    private JLabel score = new JLabel("Score:"+0 );
    private JLabel score2 = new JLabel("0");
    private JLabel said = new JLabel("<html>遊<br>戲<br>說<br>明<br>：" +
            "<br>吃<br>到<br>東<br>西<br>可<br>以<br>加<br>分<br>" +
            "每<br>過<br>20<br>秒<br>速<br>度<br>將<br>會<br>加<br>快<br>" +
            "且<br>蛇<br>會<br>變<br>長<br>。<<br></html>");
    private JLabel time = new JLabel("Time:" + 0);
    private Timer t;
    private Timer t2;
    private Timer foodtime;
    private JPanel jpn = new JPanel(new GridLayout(1, 2, 1, 1));
    private JPanel jps = new JPanel(new GridLayout(1, 3, 1, 1));
    private JPanel jpc = new JPanel();
    private JPanel jpe = new JPanel();
    public SnakeGrid snakeset[][] = new SnakeGrid[16][25];
    private ImageIcon img = new ImageIcon("back.jpg");
    private int choose =1;
    private int howfast=400;
    private boolean flag = true;
    private Random ran = new Random();
    private boolean live =true;


    public ArrayList<SnakeGrid> snakebody = new ArrayList<>();
    public ArrayList<SnakeGrid> food = new ArrayList<>();



    public SnakeF() {
        init();
    }

    private void init() {

        this.setBounds(100, 100, 1010, 800);
        cp = this.getContentPane();
        cp.add(jpc, BorderLayout.CENTER);
        jpc.setLayout(new GridLayout(rows, cols, 1, 1));

        //版面配置
        cp.add(jpn, BorderLayout.NORTH);
        cp.add(jps, BorderLayout.SOUTH);
        cp.add(jpe, BorderLayout.EAST);
        jpn.add(score);
        jpn.add(score2);
        jpn.add(time);
        jps.add(run);
        jps.add(re);
        jps.add(exit);
        jpe.add(said);
        said.setFont(new Font(null, Font.CENTER_BASELINE, 16));
        score.setFont(new Font(null, Font.CENTER_BASELINE, 50));
        score.setBackground(new Color(0x3B3B3B));
        score.setForeground(Color.white);
        score.setOpaque(true);
        time.setFont(new Font(null, Font.CENTER_BASELINE, 50));
        time.setBackground(new Color(0x000000));
        time.setForeground(Color.white);
        time.setOpaque(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        run.setFont(new Font(null, Font.CENTER_BASELINE, 34));
        re.setFont(new Font(null, Font.CENTER_BASELINE, 34));
        exit.setFont(new Font(null, Font.CENTER_BASELINE, 34));
        exit.setBackground(new Color(0x4B825D));
        exit.setOpaque(true);
        exit.setForeground(Color.white);


        //畫背景
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 25; j++) {
                snakeset[i][j] = new SnakeGrid();
                snakeset[i][j].x = j;
                snakeset[i][j].y = i;
                jpc.add(snakeset[i][j]);
            }
        }

        foodtime = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //食物
                int a = ran.nextInt(16);
                int b = ran.nextInt(25);
               if (food.size()<10){
                    if (snakeset[a][b].getFlag() == SnakeGrid.Type.Ground && snakeset[a][b].getFlag() != SnakeGrid.Type.Snake) {
                        snakeset[a][b].setFlag(SnakeGrid.Type.Food);
                        System.out.println(a +"\t" +b);
                        food.add(snakeset[a][b]);
                    }
                }
            }
        });

        t2 = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                times = times + 1;
                time.setText("Time:" + Integer.toString(times));
                if (times%4==0) {
                    howfast = howfast -25;
                    t.setDelay(howfast);
//                    snakebody.add(0,snakeset[y][x]);
                }
            }
        });


        cp.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                //鍵盤控制 1=上 2=下 3=左 4=右
                super.keyPressed(e);
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        choose = 1;
                        break;
                    case KeyEvent.VK_DOWN:
                        choose = 2;
                        break;
                    case KeyEvent.VK_LEFT:
                        choose = 3;
                        break;
                    case KeyEvent.VK_RIGHT:
                        choose = 4;
                        break;
                }
            }
        });

        t = new Timer(howfast, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //蛇的控制
                int x;
                int y;
//                y = snakebody.get(0).y;
//                x = snakebody.get(0).x;
//                if (y<1 || y>14 || x<1 || x>23){
//                    t.stop();
//                    t2.stop();
//                    foodtime.stop();
//                    JOptionPane.showMessageDialog(SnakeF.this,"Game Over");
//                }

                switch (choose) {
                    case 1:
                        y = snakebody.get(0).y-1;
                        x = snakebody.get(0).x;
//                        if (snakeset[y][x].getFlag() == SnakeGrid.Type.Food) {
                            System.out.println("123");
                            getfood(snakeset[y][x]);
//                        }
                        snakeset[y][x].setFlag(SnakeGrid.Type.Snake);
                        snakebody.add(0, snakeset[y][x]);
                        snakebody.get(snakebody.size() - 1).setFlag(SnakeGrid.Type.Ground);
                        snakebody.remove(snakebody.size() - 1);
                        break;
                    case 2:
                        y = snakebody.get(0).y+1;
                        x = snakebody.get(0).x;
                        if (snakeset[y][x].getFlag() == SnakeGrid.Type.Food) {
                            System.out.println("...");
                            getfood(snakeset[y][x]);
                        }
                        snakeset[y][x].setFlag(SnakeGrid.Type.Snake);
                        snakebody.add(0, snakeset[y][x]);
                        snakebody.get(snakebody.size() - 1).setFlag(SnakeGrid.Type.Ground);
                        snakebody.remove(snakebody.size() - 1);
                        break;
                    case 3:
                        y = snakebody.get(0).y;
                        x = snakebody.get(0).x-1;
                        if (snakeset[y][x].getFlag() == SnakeGrid.Type.Food) {
                            System.out.println("...");
                            getfood(snakeset[y][x]);
                        }
                        snakeset[y][x].setFlag(SnakeGrid.Type.Snake);
                        snakebody.add(0, snakeset[y][x]);
                        snakebody.get(snakebody.size() - 1).setFlag(SnakeGrid.Type.Ground);
                        snakebody.remove(snakebody.size() - 1);

                        break;
                    case 4:
                        y = snakebody.get(0).y;
                        x = snakebody.get(0).x+1;
                        if (snakeset[y][x].getFlag() == SnakeGrid.Type.Food) {
                            System.out.println("...");
                            getfood(snakeset[y][x]);
                        }
                        snakeset[y][x].setFlag(SnakeGrid.Type.Snake);
                        snakebody.add(0, snakeset[y][x]);
                        snakebody.get(snakebody.size()- 1).setFlag(SnakeGrid.Type.Ground);
                        snakebody.remove(snakebody.size() - 1);


                        break;
                }

            }
        });
        snakebody.add(snakeset[12][12]);
        snakeset[12][12].setFlag(SnakeGrid.Type.Snake);
        snakebody.add(snakeset[13][12]);
        snakeset[13][12].setFlag(SnakeGrid.Type.Snake);
        snakebody.add(snakeset[14][12]);
        snakeset[14][12].setFlag(SnakeGrid.Type.Snake);

        run.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t.start();
                t2.start();
                foodtime.start();
                cp.requestFocus();
            }

        });
        re.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


    }
    private void getfood (SnakeGrid snakeset) {
        for (int i = 0; i < food.size(); i++){
            if (snakeset.equals(food.get(i))){
                food.remove(i);
                scores++;
                score.setText("Score:"+Integer.toString(scores));
                break;
            }
        }

    }

}

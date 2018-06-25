import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class SnakeF extends JFrame {
    private Container cp;
    private int times , scores ;
    private int choose =1;
    private int howfast=400;
    private JButton run = new JButton("Start");
    private JButton re = new JButton("Again");
    private JButton exit = new JButton("Exit");
    private JLabel score = new JLabel("Score:"+0 );
    private JLabel said = new JLabel("<html>遊<br>戲<br>說<br>明<br>：" +
            "<br>吃<br>到<br>東<br>西<br>可<br>以<br>加<br>分<br>" +
            "每<br>過<br>5<br>秒<br>速<br>度<br>將<br>會<br>加<br>快<br>" +
            "且<br>蛇<br>會<br>變<br>長<br>。<<br></html>");
    private JLabel time = new JLabel("Time:" + 0);
    private Timer snakemove;
    private Timer speedset;
    private Timer foodtime;
    private JPanel jpn = new JPanel(new GridLayout(1, 2, 1, 1));
    private JPanel jps = new JPanel(new GridLayout(1, 3, 1, 1));
    private JPanel jpc = new JPanel();
    private JPanel jpe = new JPanel();
    private Random ran = new Random();

    private SnakeGrid snakeset[][] = new SnakeGrid[16][25];
    private ArrayList<SnakeGrid> snakebody = new ArrayList<>();
    private ArrayList<SnakeGrid> food = new ArrayList<>();



    public SnakeF() {
        init();
    }

    private void init() {

        this.setBounds(100, 100, 1010, 800);
        cp = this.getContentPane();
        cp.add(jpc, BorderLayout.CENTER);
        jpc.setLayout(new GridLayout(16, 25, 1, 1));

        //版面配置
        cp.add(jpn, BorderLayout.NORTH);
        cp.add(jps, BorderLayout.SOUTH);
        cp.add(jpe, BorderLayout.EAST);
        jpn.add(score);
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
                        food.add(snakeset[a][b]);
                    }
                }
            }
        });

        speedset = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                times = times + 1;
                time.setText("Time:" + Integer.toString(times));
                int y = snakebody.get(0).y;
                int x = snakebody.get(0).x;
                System.out.println(times);
                    if (times % 5 == 0) {
                        howfast = howfast - 25;
                        if (howfast>50) {
                            System.out.println("加速" + "\t" + "目前速度為:" + howfast);
                            //速度最快降到50
                            snakemove.setDelay(howfast);
                        }
                        if (howfast==50){
                            System.out.println("已達最高速度");
                        }
                        snakebody.add(0, snakeset[y][x]);//蛇變長 , 從蛇的矩陣的頭開始加
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

        snakemove = new Timer(howfast, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //蛇的控制
                int x;
                int y;
                    switch (choose) {
                        case 1: //上
                            if ( snakebody.get(0).y-1>-1) {
                                y = snakebody.get(0).y - 1;
                                x = snakebody.get(0).x;
                                //吃食物加分
                                if (snakeset[y][x].getFlag() == SnakeGrid.Type.Food) {
                                    for (int i = 0; i < food.size(); i++){
                                        if (snakeset.equals(food.get(i))){
                                            food.remove(i);
                                            scores++;
                                            score.setText("Score:"+Integer.toString(scores));
                                            break;
                                        }
                                    }
                                    getfood(snakeset[y][x]);
                                }
                                //死掉
                                if (snakeset[y][x].getFlag() == SnakeGrid.Type.Snake) {
                                    die();
                                }
                                snakeset[y][x].setFlag(SnakeGrid.Type.Snake); //把前面一格set成snake
                                snakebody.add(0, snakeset[y][x]); //加到蛇的陣列裡
                                snakebody.get(snakebody.size() - 1).setFlag(SnakeGrid.Type.Ground); //因為我們在頭加了一格所以我們要把最後變變回地板
                                snakebody.remove(snakebody.size() - 1);//然後把蛇的長度-1  不然這樣你的蛇會越來越長
                            }
                            else {
                                die();
                            }
                            break;
                        case 2: //下
                            if ( snakebody.get(0).y+1<16) {
                                y = snakebody.get(0).y + 1;
                                x = snakebody.get(0).x;
                                if (snakeset[y][x].getFlag() == SnakeGrid.Type.Food) {
                                    getfood(snakeset[y][x]);
                                }
                                if (snakeset[y][x].getFlag() == SnakeGrid.Type.Snake) {
                                    die();
                                }
                                snakeset[y][x].setFlag(SnakeGrid.Type.Snake);
                                snakebody.add(0, snakeset[y][x]);
                                snakebody.get(snakebody.size() - 1).setFlag(SnakeGrid.Type.Ground);
                                snakebody.remove(snakebody.size() - 1);
                            }
                            else {
                                die();
                            }
                            break;
                        case 3: //左
                            if ( snakebody.get(0).x-1>-1) {
                                y = snakebody.get(0).y;
                                x = snakebody.get(0).x - 1;
                                if (snakeset[y][x].getFlag() == SnakeGrid.Type.Food) {
                                    getfood(snakeset[y][x]);
                                }
                                if (snakeset[y][x].getFlag() == SnakeGrid.Type.Snake) {
                                    die();
                                }
                                snakeset[y][x].setFlag(SnakeGrid.Type.Snake);
                                snakebody.add(0, snakeset[y][x]);
                                snakebody.get(snakebody.size() - 1).setFlag(SnakeGrid.Type.Ground);
                                snakebody.remove(snakebody.size() - 1);
                            }
                            else {
                                die();
                            }
                            break;
                        case 4: //右
                            if ( snakebody.get(0).x+1<25) {
                                y = snakebody.get(0).y;
                                x = snakebody.get(0).x + 1;
                                if (snakeset[y][x].getFlag() == SnakeGrid.Type.Food) {
                                    getfood(snakeset[y][x]);
                                }
                                if (snakeset[y][x].getFlag() == SnakeGrid.Type.Snake) {
                                    die();
                                }
                                snakeset[y][x].setFlag(SnakeGrid.Type.Snake);
                                snakebody.add(0, snakeset[y][x]);
                                snakebody.get(snakebody.size() - 1).setFlag(SnakeGrid.Type.Ground);
                                snakebody.remove(snakebody.size() - 1);
                            }
                            else {
                                die();
                            }
                            break;
                    }
            }
        });
        //一開始蛇的位置
        snakebody.add(snakeset[12][12]);
        snakeset[12][12].setFlag(SnakeGrid.Type.Snake);
        snakebody.add(snakeset[13][12]);
        snakeset[13][12].setFlag(SnakeGrid.Type.Snake);
        snakebody.add(snakeset[14][12]);
        snakeset[14][12].setFlag(SnakeGrid.Type.Snake);


        run.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //開始
                snakemove.start();
                speedset.start();
                foodtime.start();
                cp.requestFocus();
            }

        });
        re.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //重來
                cp.requestFocus();
                choose = 1 ;
                times = 0;
                scores = 0;
                time.setText("Time:" + 0);
                score.setText("Score:" + 0);
                howfast = 400;
                snakemove.setDelay(howfast);
                //清除陣列 變回地板
                for (int i = 0 ; i<snakebody.size(); i++){
                    snakebody.get(i).setFlag(SnakeGrid.Type.Ground);
                }
                snakebody.clear();
                for (int i = 0 ; i<food.size(); i++){
                    food.get(i).setFlag(SnakeGrid.Type.Ground);
                }
                food.clear();
                //重做一條蛇
                snakebody.add(snakeset[12][12]);
                snakeset[12][12].setFlag(SnakeGrid.Type.Snake);
                snakebody.add(snakeset[13][12]);
                snakeset[13][12].setFlag(SnakeGrid.Type.Snake);
                snakebody.add(snakeset[14][12]);
                snakeset[14][12].setFlag(SnakeGrid.Type.Snake);
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
            }
        }
    }
    private void die () {
                    snakemove.stop();
                    speedset.stop();
                    foodtime.stop();
                    JOptionPane.showMessageDialog(SnakeF.this,"Game Over");
                }
}

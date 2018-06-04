import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

public class SnakeF extends JFrame {
    private Container cp;


    int times;
//    private PaintPanel paintPanel = new PaintPanel();

    private JButton run = new JButton("Start");
    private JButton re = new JButton("Again");
    private JButton exit = new JButton("Exit");
    private JLabel score = new JLabel("Score:");
    private JLabel said = new JLabel("<html>遊<br>戲<br>說<br>明<br>：" +
            "<br>吃<br>到<br>東<br>西<br>可<br>以<br>加<br>分<br>" +
            "每<br>過<br>20<br>秒<br>速<br>度<br>將<br>會<br>加<br>快<br>" +
            "且<br>蛇<br>會<br>變<br>長<br>。<<br></html>");
    private JLabel time = new JLabel("Time:" + 0);
    private Timer t;
    private Timer t2;
    private JPanel jpn = new JPanel(new GridLayout(1, 2, 1, 1));
    private JPanel jps = new JPanel(new GridLayout(1, 3, 1, 1));
    private JPanel jpc = new JPanel();
    private JPanel jpe = new JPanel();
    private SnakeGrid snakeset[][] = new SnakeGrid[16][25];
    private ImageIcon img = new ImageIcon("back.jpg");
    private int choose =1;
    private int howfast=500;
    private boolean flag = true;


    private ArrayList<SnakeGrid> snakebody = new ArrayList<>();


    public SnakeF() {
        init();
    }

    private void init() {

        this.setBounds(100, 100, 1010, 800);
        cp = this.getContentPane();
        cp.add(jpc, BorderLayout.CENTER);
        jpc.setLayout(new GridLayout(16,25,1,1));


        cp.add(jpn, BorderLayout.NORTH);
        cp.add(jps, BorderLayout.SOUTH);
        cp.add(jpe, BorderLayout.EAST);
//        cp.add(paintPanel);
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






        for (int i = 0; i < 16 ;i++){
            for (int j = 0; j < 25 ; j++){
                snakeset[i][j] = new SnakeGrid();
                snakeset[i][j].y = i;
                snakeset[i][j].x = j;
                jpc.add(snakeset[i][j]);
            }
        }

        t2 = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                times = (int)times+1;
                time.setText("Time:" + Integer.toString(times));
            }
        });
        if (times%2==0) {
            howfast = howfast - 450;
            System.out.println("123");
        }



        t = new Timer(howfast, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int x;
                int y;
                switch (choose) {
                    case 1:
                        y = snakebody.get(0).y-1;
                        x = snakebody.get(0).x;
                        snakeset[y][x].setFlag(SnakeGrid.Type.Snake);
                        snakebody.add(0, snakeset[y][x]);
                        snakebody.get(snakebody.size() - 1).setFlag(SnakeGrid.Type.Ground);
                        snakebody.remove(snakebody.size() - 1);
                        break;
                    case 2:

                        break;
                    case 3:

                        break;
                    case 4:

                        break;
                }

            }
        });

        t2 = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                times++;
                time.setText("Time:" + Integer.toString(times));
                howfast=howfast-450;
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
                cp.requestFocus();
            }

        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

//    private final class PaintPanel extends JPanel {
//        private int x =0;
//        private int y =0;
//        private int w =40;
//        public void paint(Graphics g) {
//            g.setColor(new Color(0xEE9A49));
//            g.drawRect(x,y,800,1000);
//
//                for(int i = 1; i < 25; i ++) {
//                    g.drawLine(x + (i * w), y , x + (i * w),800);
//                    g.drawLine( x, y + (i * w), 1000, y + (i * w));
//
//                }
//        }
//
//    }
}

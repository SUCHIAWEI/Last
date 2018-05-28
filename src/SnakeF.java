import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class SnakeF extends JFrame {
    private Container cp;


    int times;
    private PaintPanel paintPanel = new PaintPanel();
    private int resolution = 15;
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
    private ImageIcon img = new ImageIcon("back.jpg");

    private JLabel background = new JLabel(img);

    private LinkedList<Point> snakeList = new LinkedList<Point>();

    public SnakeF() {
        init();
    }

    private void init() {

        this.setBounds(100, 100, 1010, 800);
        cp = this.getContentPane();

        this.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
        background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
        ((JPanel)this.getContentPane()).setOpaque(false);

        cp.add(jpc, BorderLayout.CENTER);
        jpc.setLayout(new BorderLayout());
        cp.add(jpn, BorderLayout.NORTH);
        cp.add(jps, BorderLayout.SOUTH);
        cp.add(jpe, BorderLayout.EAST);
        jpc.add(paintPanel);
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

        snakeList.add(new Point(7,0));
        snakeList.add(new Point(6,0));
        snakeList.add(new Point(5,0));
        snakeList.add(new Point(4,0));
        snakeList.add(new Point(3,0));
        snakeList.add(new Point(2,0));
        snakeList.add(new Point(1,0));
        snakeList.add(new Point(0,0));



        run.setFont(new Font(null, Font.CENTER_BASELINE, 34));
        re.setFont(new Font(null, Font.CENTER_BASELINE, 34));
        exit.setFont(new Font(null, Font.CENTER_BASELINE, 34));
        exit.setBackground(new Color(0x4B825D));
        exit.setOpaque(true);
        exit.setForeground(Color.white);

        t = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        t2 = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                times++;
                time.setText("Time:" + Integer.toString(times));
            }
        });
        run.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t.start();
                t2.start();
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private final class PaintPanel extends JPanel {
        private int x =0;
        private int y =0;
        private int w =40;
//        private int jpcarea =800;

        public void paint(Graphics g) {
            Dimension d = null;
            d = getSize(d);

            g.setColor(new Color(0xEE9A49));

            g.drawRect(x,y,800,1000);

                for(int i = 1; i < 25; i ++) {
                    g.drawLine(x + (i * w), y , x + (i * w),800);
                    g.drawLine( x, y + (i * w), 1000, y + (i * w));
                }



        }
    }
}

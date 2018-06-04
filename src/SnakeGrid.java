
import javax.swing.*;
import java.awt.*;




    public class SnakeGrid extends JLabel{

        public Type flag = Type.Ground;
        public int x;
        public int y;
        public ImageIcon imgs = new ImageIcon("green.png");
        public ImageIcon img = new ImageIcon("back.jpg");

        SnakeGrid(){
            setFlag(flag);
        }
        public enum Type {
            Ground, Snake, Food
        }

        public void setFlag(Type flag){
            switch (flag){
                case Ground:
                    setIcon(img);
                    setOpaque(false);
                    break;

                case Snake:
                    setIcon(imgs);
                    setOpaque(true);
                    break;

                case Food:
                    setBackground(Color.RED);
                    setOpaque(true);

            }
        }
    }


import javax.swing.*;
import java.awt.*;




    public class SnakeGrid extends JLabel{

        public Type flag = Type.Ground;
        public int x;
        public int y;
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
                    this.flag = Type.Ground;
                    setIcon(img);
                    setOpaque(false);
                    break;

                case Snake:
                    this.flag = Type.Snake;
                    setIcon(null);
                    setBackground(Color.cyan);
                    setOpaque(true);
                    break;

                case Food:
                    this.flag = Type.Food;
                    setIcon(null);
                    setBackground(Color.RED);
                    setOpaque(true);
                    break;
            }
        }

        public Type getFlag() {
            return flag;
        }
    }

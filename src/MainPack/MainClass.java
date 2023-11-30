package MainPack;
import javax.swing.JFrame;
import java.awt.*;
public class MainClass {

        public static void main(String[] args){
            Calculator window = new Calculator();
            window.setTitle("calculator");
            window.setSize(350, 600);
            window.setMinimumSize(new Dimension(400, 625));
            window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            window.setVisible(true);
        }
    }
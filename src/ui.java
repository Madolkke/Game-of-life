import javax.swing.*;
import java.awt.*;

public class ui extends JFrame {
    public ui(){

    }

    public void getUInterface(){

    }
    public JFrame DrawUInterface(){
        JFrame jFrame = new JFrame("Game of life");
        Container container = jFrame.getContentPane();
        jFrame.setVisible(true);
        jFrame.setSize(1000,800);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        return jFrame;

    }

}

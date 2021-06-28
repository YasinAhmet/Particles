import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Frame extends JFrame  {


    public void Frame() {
        setSize(600,800);

        Panel p = new Panel();
        p.Panel();

        add(p);
        setVisible(true);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class Panel extends JPanel {
    ArrayList<Particle> particles = new ArrayList<>();
    Timer timer = new Timer(17, this::time);
    Timer effects = new Timer(85, this::ActPerformed);
    boolean Pushmod = false;
    int points = 0;
    MouseMotionListener mm = new MouseMotionListener() {

        @Override
        public void mouseDragged(MouseEvent e) {
            for (int i = 0; i < particles.size(); i++) {
                if ((particles.get(i).getX() >= e.getX() - 100 && particles.get(i).getX() <= e.getX() + 100) && (particles.get(i).getY() >= e.getY() - 100 && particles.get(i).getY() <= e.getY() + 100)) {

                    if (particles.get(i).getX() - e.getX() >= 30) {
                        particles.get(i).setVelx(particles.get(i).getVelx() + 1);
                    } else if (particles.get(i).getX() - e.getX() >= -30) {
                        particles.get(i).setVelx(particles.get(i).getVelx() - 1);
                    }

                    if (particles.get(i).getY() - e.getY() >= 30) {
                        particles.get(i).setVely(particles.get(i).getVely() +1);
                    } else if (particles.get(i).getY() - e.getY() >= -30) {
                        particles.get(i).setVely(particles.get(i).getVely() - 1);
                    }
                }
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }
    };
    MouseListener m = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 3) {
            if (Pushmod)
                Pushmod = false;
            else if (!Pushmod)
                Pushmod = true;
        }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            for (int i = 0; i < particles.size(); i++) {
                if ((particles.get(i).getX() >= e.getX() - 100 && particles.get(i).getX() <= e.getX() + 100) && (particles.get(i).getY() >= e.getY() - 100 && particles.get(i).getY() <= e.getY() + 100)) {

                    if (particles.get(i).getX() - e.getX() >= 30) {
                        particles.get(i).setVelx(particles.get(i).getVelx() + 100/(particles.get(i).getX() - e.getX()));
                    } else if (particles.get(i).getX() - e.getX() >= -30) {
                        particles.get(i).setVelx(particles.get(i).getVelx() - 100/(particles.get(i).getX() - e.getX()));
                    }

                    if (particles.get(i).getY() - e.getY() >= 30) {
                        particles.get(i).setVely(particles.get(i).getVely() +100/(particles.get(i).getY() - e.getY()));
                    } else if (particles.get(i).getY() - e.getY() >= -30) {
                        particles.get(i).setVely(particles.get(i).getVely() - 100/(particles.get(i).getY() - e.getY()));
                    }
                }
            }

            if (Pushmod) {
                Color c = Color.blue;
                int g = 0;
                for (int i = 0; i < 20; i++) {

                    g++;
                    if (i < 5) {
                        Particle p = new Particle();
                        p.Partic(e.getX(), e.getY() + g, c, -5, 0);
                        particles.add(p);
                    } else if (i < 10) {
                        Particle p = new Particle();
                        p.Partic(e.getX() + g, e.getY(), c, 0, +5);
                        particles.add(p);
                    } else if (i < 15) {
                        Particle p = new Particle();
                        p.Partic(e.getX(), e.getY() - g, c, +5, 0);
                        particles.add(p);
                    } else if (i < 20) {
                        Particle p = new Particle();
                        p.Partic(e.getX() - g, e.getY(), c, 0, -5);
                        particles.add(p);
                    }
                    if (g == 5) {
                        g = 0;
                    }
                }
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    };



    public void Panel() {

        setSize(400,600);
        setBackground(Color.GRAY);
        addMouseListener(m);
        addMouseMotionListener(mm);
        timer.start();
        effects.start();

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < particles.size(); i++) {
            g.setColor(particles.get(i).getCol());
            g.fillRect(particles.get(i).getX(), particles.get(i).getY(), 10,10);
        }

    }
    public void time(ActionEvent e) {
        repaint();
        for (int i = 0; i < particles.size(); i++) {
            int b = particles.get(i).getCol().getBlue();
            int r = particles.get(i).getCol().getRed();
            int g = particles.get(i).getCol().getGreen();

            if (particles.get(i).getCol().getBlue() > 1) {
                particles.get(i).setCol(new Color(r, g, b - 1));
            } else if (particles.get(i).getCol().getRed() < 254) {
                particles.get(i).setCol(new Color(r + 1, g, b));
            } else if (particles.get(i).getCol().getGreen() < 254)
                particles.get(i).setCol(new Color(r,g+1,b));

            particles.get(i).setY(particles.get(i).getY() + particles.get(i).getVely());
            particles.get(i).setX(particles.get(i).getX() + particles.get(i).getVelx());



            if (particles.get(i).getX() > 600 || particles.get(i).getX() < 0) {
                particles.get(i).setVely((particles.get(i).getVely()));
                particles.get(i).setVelx(-(particles.get(i).getVelx()));
            }
            if (particles.get(i).getY() >= 700) {
                particles.remove(particles.get(i));
            }
            else if (particles.get(i).getY() < 0) {
                particles.remove(particles.get(i));
                points = points +1;
                System.out.println("Point: "+ points);
            }
        }
    }
    public void ActPerformed(ActionEvent event) {
     for (int i = 0; i < particles.size(); i++) {

         if (particles.get(i).getVelx() > 1) {
             particles.get(i).setVelx(particles.get(i).getVelx() -1);
         } else if (particles.get(i).getVelx() < -1) {
             particles.get(i).setVelx(particles.get(i).getVelx() +1);
         }

         if (particles.get(i).getVely() > 5) {
             particles.get(i).setVely(particles.get(i).getVely() -1);
         }
         else if (particles.get(i).getVely() < -1) {
             particles.get(i).setVely(particles.get(i).getVely() +1);
         }

         particles.get(i).setVely(particles.get(i).getVely()+1);

     }
    }


}

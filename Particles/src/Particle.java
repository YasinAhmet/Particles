import java.awt.Color;

public class Particle {
    private int x = 0, y = 0;
    private int velx = 0, vely = 0;
    private Color col = Color.blue;

    public void Partic(int x, int y, Color col, int velx, int vely) {
    this.x = x;
    this.y = y;
    this.col = col;
    this.velx = velx;
    this.vely = vely;

    }

    public int getX() {return x;}
    public int getY() {return y;}
    public Color getCol() {return col;}
    public int getVelx() {return velx;}
    public int getVely() {return vely;}

    public void setX(int x) {this.x = x;}
    public void setVelx(int velx) {this.velx = velx;}
    public void setVely(int vely) {this.vely = vely;}
    public void setY(int y) {this.y = y;}
    public void setCol(Color col) {this.col = col;}

}

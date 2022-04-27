import java.awt.*;

// holds all necessary info for the point values, their value in grid as well as placement in frame
public class PointDegree {
    private double x;
    private double y;
    private double z;
    private int xAdd;
    private int yAdd;
    private int zAdd;
    private double size;

    public static final int X_DROP = 60, Y_DROP = -120, X_START = 800, Y_START = 900, RAD = 200, CIRC_R = 12, STRETCH = 6;

    public PointDegree(double size){
        this(0, 0, 0, size);
    }

    public PointDegree(double x, double y, double z, double size){
        this.x = x;
        this.y = y;
        this.z = z;
        this.size = size + 1;
    }


    public void render(Graphics g, int width, int height){
        int color = (int) (((z % Math.PI) + size) / (2*size) * 180 + 55);
        g.setColor(new Color(color, color, color));
        g.fillOval((int) ((x * height / 2 + height) + z * (X_DROP) + RAD + X_START) / STRETCH + xAdd,
                    (int) ((y * height / 2 + height) - z * (Y_DROP) + RAD + Y_START) / STRETCH + yAdd,
                        CIRC_R, CIRC_R);
    }


    public void renderLine(Graphics g, int width, int height, PointDegree other){
        g.drawLine((int) ((x * height / 2 + height) + z * (X_DROP) + RAD + X_START) / STRETCH + CIRC_R / 2 + xAdd,
                (int) ((y * height / 2 + height) - z * (Y_DROP) + RAD + Y_START) / STRETCH + CIRC_R / 2 + yAdd,
                (int) ((other.getX() * height / 2 + height) + other.getZ()
                        * (X_DROP) + RAD + X_START) / STRETCH  + CIRC_R / 2 + other.getXAdd(),
                (int) ((other.getY() * height / 2 + height) - other.getZ()
                        * (Y_DROP) + RAD + Y_START) / STRETCH  + CIRC_R / 2 + other.getYAdd());
    }


    public void xRotate(double rotation){
        double tempY = y * Math.cos(rotation) - z * Math.sin(rotation);
        z = z * Math.cos(rotation) + y * Math.sin(rotation);
        y = tempY;
    }

    public void yRotate(double rotation){
        double tempX = x * Math.cos(rotation) - z * Math.sin(rotation);
        z = z * Math.cos(rotation) + x * Math.sin(rotation);
        x = tempX;
    }

    public void zRotate(double rotation){
        double tempX = x * Math.cos(rotation) - y * Math.sin(rotation);
        y = y * Math.cos(rotation) + x * Math.sin(rotation);
        x = tempX;
    }

    public void addX(double num){
        this.xAdd += num;
    }

    public void addY(double num){
        this.yAdd += num;
    }

    public void addZ(double num){
        this.zAdd += num;
    }



    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public double getZ(){
        return z;
    }

    public int getXAdd(){
        return xAdd;
    }

    public int getYAdd(){
        return yAdd;
    }


}

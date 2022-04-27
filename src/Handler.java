import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

public class Handler {
    private PointDegree[] points;
    private static Map<PointDegree, LinkedList<PointDegree>> edges;
    private int width;
    private int height;
    private int xVel, yVel, zVel;
    private double xRotVel, yRotVel, zRotVel;

    public static final double SIZE = 2.6, ROTATION = .02;
    private static final int ADD = 10, ACCELERATION = 5, ADD_ACC = ADD / ACCELERATION;
    private static final double ROT_ACC = ROTATION / ACCELERATION;


    // public static final int ;

    public Handler(int width, int height){
        points = new PointDegree[8];
        points[0] = new PointDegree(SIZE * 1.0, SIZE * 1.0, SIZE * 1.0, SIZE);
        points[1] = new PointDegree(SIZE * 1.0, SIZE * 1.0, SIZE * -1.0, SIZE);
        points[2] = new PointDegree(SIZE * 1.0, SIZE * -1.0, SIZE * 1.0, SIZE);
        points[3] = new PointDegree(SIZE * 1.0, SIZE * -1.0, SIZE * -1.0, SIZE);
        points[4] = new PointDegree(SIZE * -1.0, SIZE * 1.0, SIZE * 1.0, SIZE);
        points[5] = new PointDegree(SIZE * -1.0, SIZE * 1.0, SIZE * -1.0, SIZE);
        points[6] = new PointDegree(SIZE * -1.0, SIZE * -1.0, SIZE * 1.0, SIZE);
        points[7] = new PointDegree(SIZE * -1.0, SIZE * -1.0, SIZE * -1.0, SIZE);



        edges = new HashMap<>();

        edges.put(points[0], new LinkedList<PointDegree>());
        edges.get(points[0]).add(points[1]);
        edges.get(points[0]).add(points[2]);
        edges.get(points[0]).add(points[4]);

        edges.put(points[3], new LinkedList<PointDegree>());
        edges.get(points[3]).add(points[1]);
        edges.get(points[3]).add(points[2]);
        edges.get(points[3]).add(points[7]);


        edges.put(points[5], new LinkedList<PointDegree>());
        edges.get(points[5]).add(points[1]);
        edges.get(points[5]).add(points[4]);
        edges.get(points[5]).add(points[7]);

        edges.put(points[6], new LinkedList<PointDegree>());
        edges.get(points[6]).add(points[2]);
        edges.get(points[6]).add(points[4]);
        edges.get(points[6]).add(points[7]);



        this.width = width;
        this.height = height;
    }

    // shifts in x direction
    public void addX(double num){
        if (Math.abs(xVel) <= Math.abs(num) - ADD_ACC){
            xVel += num / ACCELERATION;
        }
        for (int i = 0; i < points.length; i++){
            points[i].addX(xVel);
        }
    }

    // shifts in y direction
    public void addY(double num){
        if (Math.abs(yVel) <= Math.abs(num) - ADD_ACC){
            yVel += num / ACCELERATION;
        }
        for (int i = 0; i < points.length; i++){
            points[i].addY(yVel);
        }
    }

    // shifts in z direction
    public void addZ(double num){
        if (Math.abs(zVel) <= Math.abs(num) - ADD_ACC){
            zVel += num / ACCELERATION;
        }
        for (int i = 0; i < points.length; i++){
            points[i].addZ(zVel);
        }
    }

    // rotates around x axis
    public void xRotate(double rotation){
        if (xRotVel <= rotation - ROT_ACC){
            xRotVel += rotation / ACCELERATION;
        }
        for (int i = 0; i < points.length; i++){
            points[i].xRotate(xRotVel);
        }
    }

    // rotates around y axis
    public void yRotate(double rotation){
        if (yRotVel <= rotation - ROT_ACC){
            yRotVel += rotation / ACCELERATION;
        }
        for (int i = 0; i < points.length; i++){
            points[i].yRotate(yRotVel);
        }
    }

    // rotates around z axis
    public void zRotate(double rotation){
        if (zRotVel <= rotation - ROT_ACC){
            zRotVel += rotation / ACCELERATION;
        }
        for (int i = 0; i < points.length; i++){
            points[i].zRotate(yRotVel);
        }
    }

    // creates cube rotation
    public void tick(){
        if (xVel != 0) {
            xVel -= (xVel / Math.abs(xVel)) * ADD_ACC;
        }
        if (yVel != 0) {
            yVel -= (yVel / Math.abs(yVel)) * ADD_ACC;
        }
        if (zVel != 0) {
            zVel -= (zVel / Math.abs(zVel)) * ADD_ACC;
        }

        if (xRotVel != 0) {
            xRotVel -= (xRotVel / Math.abs(xRotVel)) * ROT_ACC;
        }
        if (yRotVel != 0) {
            yRotVel -= (yRotVel / Math.abs(yRotVel)) * ROT_ACC;
        }
        if (zRotVel != 0) {
            zRotVel -= (zRotVel / Math.abs(zRotVel)) * ROT_ACC;
        }
    }


    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);

        g.setColor(Color.WHITE);
        for (Map.Entry<PointDegree, LinkedList<PointDegree>> entry : edges.entrySet()){
            for (int i = 0; i < entry.getValue().size(); i++){
                entry.getKey().renderLine(g, width, height, entry.getValue().get(i));
            }
        }

        for (int i = 0; i < points.length; i++){
            points[i].render(g, width, height);
        }
    }
}
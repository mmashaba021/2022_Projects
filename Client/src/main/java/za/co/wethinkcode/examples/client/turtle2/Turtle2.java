package za.co.wethinkcode.examples.client.turtle2;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//





import java.awt.Color;
//This is a customized version of the turtle class thats is available in library
public class Turtle2 {
    private double x;
    private double y;
    private double angle;

    public Turtle2(double x0, double y0, double a0) {
        this.x = x0;
        this.y = y0;
        this.angle = a0;
    }

    public void setPosition(double newx, double newy) {
        this.x = newx;
        this.y = newy;
    }

    public double[] getPosition() {
        double[] a = new double[]{this.x, this.y};
        return a;
    }

    public void setAngle(double newAngle) {
        this.angle = newAngle;
    }

    public double getAngle() {
        return this.angle;
    }

    public void show() {
        StdDraw2.show();
    }

    public void setColor(Color color) {
        StdDraw2.setPenColor(color);
    }

    public void setSize(double radius) {
        StdDraw2.setPenRadius(radius);
    }

    public void left(double delta) {
        this.angle += delta;
    }

    public void right(double delta) {
        this.angle -= delta;
    }

    public void forward(double step) {
        double newx = this.x + step * Math.cos(Math.toRadians(this.angle));
        double newy = this.y + step * Math.sin(Math.toRadians(this.angle));
        StdDraw2.line(this.x, this.y, newx, newy);
        this.setPosition(newx, newy);
        this.show();
    }

    public void backward(double step) {
        double newx = this.x - step * Math.cos(Math.toRadians(this.angle));
        double newy = this.y - step * Math.sin(Math.toRadians(this.angle));
        StdDraw2.line(this.x, this.y, newx, newy);
        this.setPosition(newx, newy);
        this.show();
    }

    public void hexagon(double sideLength) {
        for(int i = 0; i < 6; ++i) {
            this.right(60.0D);
            this.forward(sideLength);
        }

    }
}

package com.nbicocchi.javafx.geometry.physics;

public class Vector {

    public static Vector zero = new Vector(0, 0);
    public static Vector right = new Vector(1, 0);
    public static Vector up = new Vector(0, 1);

    //region Vector Data
    private double x;
    private double y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    //endregion

    //region Changing Vector
    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void increase(Vector v) {
        this.x += v.x;
        this.y += v.y;
    }

    public void decrease(Vector v) {
        this.x -= v.x;
        this.y -= v.y;
    }

    public void scale(double m) {
        this.x *= m;
        this.y *= m;
    }

    public void unscaling(double d) {
        this.x /= d;
        this.y /= d;
    }

    public void normalize() {
        x /= length();
        y /= length();
    }
    //endregion

    //region Vectorial Vector operations
    public Vector add(Vector v) {
        return new Vector(x + v.x, y + v.y);
    }

    public Vector subtract(Vector v) {
        return new Vector(x - v.x, y - v.y);
    }

    public Vector multiply(double m)
    {
        return new Vector(x * m, y * m);
    }

    public Vector divide(double d) {
        return new Vector(x / d, y / d);
    }

    public Vector normalized() {
        return new Vector(x / length(), y / length());
    }

    public Vector crossProduct(Vector v) {

        return new Vector(v.y * x , v.x * y);
    }
    //endregion

    //region Scalar Vector operations
    public double dotProduct(Vector v) {
        return x * v.x + y * v.y;
    }

    public static double distance(Vector a, Vector b) {
        return a.subtract(b).length();


    }
    //endregion

    //region Vector Properties
    public double lengthSquare() {

        return x * x + y * y;
    }

    public double length() {
        return Math.sqrt(lengthSquare());
    }
    //endregion

}

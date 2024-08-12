package com.nbicocchi.javafx.geometry.physics.math;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class Vector {

    //region Predefined values
    public static Vector zero = new Vector(0, 0);
    public static Vector right = new Vector(1, 0);
    public static Vector left = new Vector(-1, 0);
    public static Vector up = new Vector(0, -1);
    public static Vector down = new Vector(0, 1);
    //endregion

    //region Vector attributes
    private double x;
    private double y;
    //endregion

    //region constructors
    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // only for unit vectors
    public Vector(double theta) {
        this.x = cos(theta);
        this.y = sin(theta);
    }

    public Vector(Vector v) {
        this.x = v.x;
        this.y = v.y;
    }

    public static Vector polarToCartesian(double theta, double r)
    {
        return new Vector(cos(theta)*r, sin(theta)*r);
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

    public void invert()
    {
        this.x = -this.x;
        this.y = -this.y;
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

    //region Vector operations
    public Vector add(Vector v) {
        return new Vector(x + v.x, y + v.y);
    }

    public Vector subtract(Vector v) {
        return new Vector(x - v.x, y - v.y);
    }

    public Vector inverted()
    {
        return new Vector(-x, -y);
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
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double lengthSquare() {

        return x * x + y * y;
    }

    public double length() {
        return Math.sqrt(lengthSquare());
    }
    //endregion
}

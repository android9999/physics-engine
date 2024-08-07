package com.nbicocchi.javafx.geometry.physics;

public class Vector {

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

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Vector add(Vector v) {
        return new Vector(x + v.x, y + v.y);
    }

    public Vector subtract(Vector v) {
        return new Vector(x - v.x, y - v.y);
    }

    public Vector dotProduct(Vector v) {
        return new Vector(x * v.x, y * v.y);
    }

    public Vector multiply(double m) {
        return new Vector(x * m, y * m);
    }

    public Vector divide(double d) {
        return new Vector(x / d, y / d);
    }

    public double lengthSquare() {

        return x * x + y * y;
    }

    public double length() {
        return Math.sqrt(lengthSquare());
    }

    public Vector normalize() {
        return new Vector(x / length(), y / length());
    }

    public Vector crossProduct() {

        return new Vector(y * x, x * y);
    }

}

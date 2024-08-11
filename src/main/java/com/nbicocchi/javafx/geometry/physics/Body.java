package com.nbicocchi.javafx.geometry.physics;


import com.nbicocchi.javafx.geometry.shapes.Circle;
import com.nbicocchi.javafx.geometry.shapes.DrawableShape;
import com.nbicocchi.javafx.geometry.shapes.Rectangle;
import com.nbicocchi.javafx.geometry.shapes.Triangle;
import javafx.geometry.Rectangle2D;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;

public class Body {

    public enum ShapeType
    {
        Circle,
        Box,
        Triangle,
        Polygon,
        Rectangle
    }

    private Vector position;
    private Vector linearVelocity;
    private double rotation;
    private double rotationVelocity;

    public double mass;
    public double density;
    public double restitution;
    public double area;

    public boolean active;

    public double radius;
    public double height;
    public double width;

    public ShapeType shapeType;

    private DrawableShape shape;

    Vector center;

    public Body(
    double x,
    double y,
    Vector linearVelocity,
    double rotation,
    double rotationVelocity,
    double density,
    double restitution,
    double area,
    boolean active,
    double radius,
    double height,
    double width,
    ShapeType shapeType,
    Color color
    )
    {

        this.position = new Vector(x, y);
        this.linearVelocity = linearVelocity;
        this.rotation = rotation;
        this.rotationVelocity = rotationVelocity;
        this.density = density;
        this.restitution = restitution;
        this.area = area;
        this.active = active;
        this.radius = radius;
        this.height = height;
        this.width = width;
        this.shapeType = shapeType;
        this.shape = createShape(x, y, shapeType, width, height, color);

        mass = area * density;
        center = new Vector(x + width/2, y+ height/2);
    }

    public DrawableShape getShape()
    {
        return shape;
    }






    public static DrawableShape createShape(double x, double y, Body.ShapeType shapeType, double width, double height, Color color) {
        Rectangle2D bounds = new Rectangle2D(x, y, width, height);
        DrawableShape shape;
        switch (shapeType) {
            case Body.ShapeType.Triangle -> {
                shape = new Triangle(bounds, color);
            }
            case Body.ShapeType.Box -> {
                shape = new Rectangle(bounds, color);
            }
            case Body.ShapeType.Circle -> {
                new Circle(bounds, color);
                shape = new Circle(bounds, color);
            }
            default -> {
                shape = new Circle(bounds, color);
            }
        }

        return shape;
    }

    public void move(Vector displacement)
    {
        position.increase(displacement);
        setShapePosition(position.getX(), position.getY());
    }


    private void setShapePosition(double x, double y)
    {
        shape.setBounds( new Rectangle2D(x , y, width, height ) );
    }

    public Vector getPosition()
    {
        return position;
    }

    public Vector getCenter() {
        return center;
    }
}



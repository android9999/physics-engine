package com.nbicocchi.javafx.geometry.physics;

import com.nbicocchi.javafx.geometry.shapes.Circle;
import com.nbicocchi.javafx.geometry.shapes.DrawableShape;
import com.nbicocchi.javafx.geometry.shapes.Rectangle;
import com.nbicocchi.javafx.geometry.shapes.Triangle;
import javafx.geometry.Rectangle2D;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class World {

    static int f = 0;

    public static List<DrawableShape> shapes  = new ArrayList<>();

    public static void addShape(double x, double y, String type, double width, double height, Color color) {
        Rectangle2D bounds = new Rectangle2D(x, y, width, height);
        DrawableShape shape;
        switch (type) {
            case "Triangle" -> {
                shape = new Triangle(bounds, color);
                World.shapes.add(shape);
            }
            case "Rectangle" -> {
                shape = new Rectangle(bounds, color);
                World.shapes.add(shape);
            }
            case "Circle" -> {
                new Circle(bounds, color);
                shape = new Circle(bounds, color);
                World.shapes.add(shape);
            }
            default -> {
            }
        }
    }

    public static void Update()
    {
        f++;
    }
}

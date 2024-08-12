package com.nbicocchi.javafx.geometry.physics.body;

import com.nbicocchi.javafx.geometry.shapes.*;
import javafx.geometry.Rectangle2D;
import javafx.scene.paint.Color;

public class ShapeFactory {
    public static DrawableShape createShape(double x, double y, Body.ShapeType shapeType, double width, double height, Color color) {
        Rectangle2D bounds = new Rectangle2D(x, y, width, height);
        switch (shapeType) {
            case Circle:
                return new Circle(bounds, color);
            case Box:
            case Rectangle:
                return new Rectangle(bounds, color);
            case Triangle:
                return new Triangle(bounds, color);
            default:
                throw new IllegalArgumentException("Unsupported shape type");
        }
    }
}

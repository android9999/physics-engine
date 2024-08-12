package com.nbicocchi.javafx.geometry.physics.body;

import com.nbicocchi.javafx.geometry.physics.math.Vector;
import com.nbicocchi.javafx.geometry.shapes.DrawableShape;
import javafx.geometry.Rectangle2D;

public class ShapeComponent {
    private final DrawableShape shape;
    private final double width;
    private final double height;

    public ShapeComponent(DrawableShape shape, double width, double height) {
        this.shape = shape;
        this.width = width;
        this.height = height;
    }

    public DrawableShape getShape() {
        return shape;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public void updateShapePosition(Vector position) {
        shape.setBounds(new Rectangle2D(position.getX(), position.getY(), width, height));
    }
}

package com.nbicocchi.javafx.geometry.shapes;

import javafx.scene.canvas.GraphicsContext;

public interface DrawableShape extends Shape {
    void paint(GraphicsContext gc);
}

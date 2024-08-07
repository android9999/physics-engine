package com.nbicocchi.javafx.geometry.shapes;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Triangle extends AbstractShape {
    public Triangle(Rectangle2D bounds, Color color) {
        super(bounds, color);
    }

    @Override
    public void paint(GraphicsContext gc) {
        double[] xPoints = {bounds.getMinX(), (bounds.getMinX() + bounds.getMaxX()) / 2, bounds.getMaxX()};
        double[] yPoints = {bounds.getMaxY(), bounds.getMinY(), bounds.getMaxY()};
        gc.setFill(color);
        gc.fillPolygon(xPoints, yPoints, 3);
    }

}

package com.nbicocchi.javafx.geometry.app;

import com.nbicocchi.javafx.geometry.physics.Body;
import com.nbicocchi.javafx.geometry.physics.Vector;
import com.nbicocchi.javafx.geometry.physics.World;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Arrays;

public class WorldController {
    private final World world;
    private Body selected;

    public WorldController() {
        this.world = World.getInstance();
    }

    public String[] getShapeTypes() {
        return Arrays.stream(Body.ShapeType.values())
                                .map(Enum::name)
                                .toArray(String[]::new)
        ;
    }

    public void addShape(String shapeType, double size, double x, double y, Color color) {
        world.addBody(300, y, Body.ShapeType.valueOf(shapeType), size, size, color, Vector.zero, 0, 0, 0, 0, 0, true, size);
    }

    public boolean selectShape(double x, double y) {
        selected = null;
        for (Body body : world.getBodies()) {
            if (body.getShape().getBounds().contains(x, y)) {
                selected = body;
                return true;
            }
        }
        return false;
    }

    public void removeSelectedShape() {
        if (selected != null) {
            world.removeBody(selected);
            selected = null;
        }
    }

    public void updateSelectedShapeSize(double size) {
        if (selected != null) {
            Rectangle2D currentBounds = selected.getShape().getBounds();
            double minX = currentBounds.getMinX();
            double minY = currentBounds.getMinY();

            // Create a new Rectangle2D with the updated size and the current minX and minY
            Rectangle2D newBounds = new Rectangle2D(minX, minY, size, size);
            selected.getShape().setBounds(newBounds);  // Ensure this method accepts Rectangle2D
        }
    }

    public void updateSelectedShapeColor(Color color) {
        if (selected != null) {
            selected.getShape().setColor(color);
        }
    }

    public void render(GraphicsContext gc) {
        world.renderBodies(gc);
        if (selected != null) {
            gc.setStroke(Color.YELLOW);
            gc.setLineWidth(5);
            gc.strokeRect(selected.getShape().getBounds().getMinX(), selected.getShape().getBounds().getMinY(), selected.getShape().getBounds().getWidth(), selected.getShape().getBounds().getHeight());
        }
    }

    public void update(long now) {
        world.Update();
    }
}

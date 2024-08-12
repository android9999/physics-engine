package com.nbicocchi.javafx.geometry.physics.world;

import com.nbicocchi.javafx.geometry.app.UIComponents;
import com.nbicocchi.javafx.geometry.physics.body.Body;
import com.nbicocchi.javafx.geometry.physics.math.Vector;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Arrays;

public class WorldController {
    private final World world;
    private Body selected;
    private UIComponents uiComponents;

    public WorldController(UIComponents uiComponents) {
        this.world = new World(new WorldClock(this));
    }

    public void setUIComponents(UIComponents uiComponents) {
        this.uiComponents = uiComponents;
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
            if (body.getShapeComponent().getShape().getBounds().contains(x, y)) {
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
            Rectangle2D currentBounds = selected.getShapeComponent().getShape().getBounds();
            double minX = currentBounds.getMinX();
            double minY = currentBounds.getMinY();

            // Create a new Rectangle2D with the updated size and the current minX and minY
            Rectangle2D newBounds = new Rectangle2D(minX, minY, size, size);
            selected.getShapeComponent().getShape().setBounds(newBounds);  // Ensure this method accepts Rectangle2D
        }
    }

    public void updateSelectedShapeColor(Color color) {
        if (selected != null) {
            selected.getShapeComponent().getShape().setColor(color);
        }
    }

    public void render(GraphicsContext gc) {
        world.renderBodies(gc);
        if (selected != null) {
            gc.setStroke(Color.YELLOW);
            gc.setLineWidth(5);
            gc.strokeRect(selected.getShapeComponent().getShape().getBounds().getMinX(), selected.getShapeComponent().getShape().getBounds().getMinY(), selected.getShapeComponent().getShape().getBounds().getWidth(), selected.getShapeComponent().getShape().getBounds().getHeight());
        }
    }

    public void update() {
        world.update();
    }

    public void requestRender() {
        if (uiComponents != null) {
            uiComponents.paint(); // Request the UIComponents to repaint the canvas
        }
    }
}

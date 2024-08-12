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

    private static WorldController instance;

    private final static int width = 1000;
    private final static int height = 500;

    private WorldController()
    {
        this.world = new World(new WorldClock(this));
    }

    public static WorldController getInstance()
    {
        if(instance == null)
        {
            instance = new WorldController();
        }

        return instance;
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

    public void addShape(String shapeType, double size, double x, double y, Color color, double theta, double r) {
        world.addBody(x, y, Body.ShapeType.valueOf(shapeType), size, size, color, Vector.polarToCartesian(theta, r), 0, 0, 0, 0, 0, true, size);
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

    public void update(long now) {
        world.update(now);
    }

    public void requestRender() {
        if (uiComponents != null) {
            uiComponents.paint(); // Request the UIComponents to repaint the canvas
        }
    }

    protected UIComponents getUiComponents()
    {
        return uiComponents;
    }

    public static int getWidth()
    {
        return width;
    }
    public static int getHeight()
    {
        return height;
    }
}

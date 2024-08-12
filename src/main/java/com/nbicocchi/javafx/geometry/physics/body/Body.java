package com.nbicocchi.javafx.geometry.physics.body;

import com.nbicocchi.javafx.geometry.physics.math.Vector;
import javafx.scene.paint.Color;

public class Body {
    public enum ShapeType {
        Circle, Box, Triangle, Polygon, Rectangle
    }

    private final Transform transform;
    private final PhysicalProperties physicalProperties;
    private final ShapeComponent shapeComponent;
    private final ActiveState activeState;
    private final ShapeType shapeType;

    public Body(double x, double y, Vector linearVelocity, double rotation, double rotationVelocity, double density,
                double restitution, double area, boolean active, double radius, double height, double width,
                ShapeType shapeType, Color color) {

        this.transform = new Transform(new Vector(x, y), linearVelocity, rotation, rotationVelocity);
        this.physicalProperties = new PhysicalProperties(density, restitution, area);
        this.shapeComponent = new ShapeComponent(ShapeFactory.createShape(x, y, shapeType, width, height, color), width, height);
        this.activeState = new ActiveState(active);
        this.shapeType = shapeType;
    }

    public Transform getTransform() {
        return transform;
    }

    public PhysicalProperties getPhysicalProperties() {
        return physicalProperties;
    }

    public ShapeComponent getShapeComponent() {
        return shapeComponent;
    }

    public ActiveState getActiveState() {
        return activeState;
    }

    public ShapeType getShapeType() {
        return shapeType;
    }

    public void move(Vector displacement) {
        transform.move(displacement);
        shapeComponent.updateShapePosition(transform.getPosition());
    }

    public Vector getPosition() {
        return transform.getPosition();
    }

    public Vector getCenter() {
        return transform.getCenter(shapeComponent.getWidth(), shapeComponent.getHeight());
    }
}

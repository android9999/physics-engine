package com.nbicocchi.javafx.geometry.physics.body;

import com.nbicocchi.javafx.geometry.physics.math.Vector;

public class Transform {
    private Vector position;
    private Vector linearVelocity;
    private double rotation;
    private double rotationVelocity;

    public Transform(Vector position, Vector linearVelocity, double rotation, double rotationVelocity) {
        this.position = position;
        this.linearVelocity = linearVelocity;
        this.rotation = rotation;
        this.rotationVelocity = rotationVelocity;
    }

    public Vector getPosition() {
        return position;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    public Vector getLinearVelocity() {
        return linearVelocity;
    }

    public void setLinearVelocity(Vector linearVelocity) {
        this.linearVelocity = linearVelocity;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public double getRotationVelocity() {
        return rotationVelocity;
    }

    public void setRotationVelocity(double rotationVelocity) {
        this.rotationVelocity = rotationVelocity;
    }

    public void move(Vector displacement) {
        position = position.add(displacement);
    }

    public Vector getCenter(double width, double height) {
        return new Vector(position.getX() + width / 2, position.getY() + height / 2);
    }
}

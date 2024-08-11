package com.nbicocchi.javafx.geometry.physics;

@FunctionalInterface
public interface CollisionsFunctions {
    void ifCollision(Body body1, Body body2);
}
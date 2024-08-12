package com.nbicocchi.javafx.geometry.physics.math;

import com.nbicocchi.javafx.geometry.physics.body.Body;

@FunctionalInterface
public interface CollisionsFunctions {
    void ifCollision(Body body1, Body body2);
}
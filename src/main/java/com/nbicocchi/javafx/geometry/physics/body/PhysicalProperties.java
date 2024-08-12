package com.nbicocchi.javafx.geometry.physics.body;

public class PhysicalProperties {
    private final double mass;
    private final double density;
    private final double restitution;
    private final double area;

    public PhysicalProperties(double density, double restitution, double area) {
        this.density = density;
        this.restitution = restitution;
        this.area = area;
        this.mass = density * area;
    }

    public double getMass() {
        return mass;
    }

    public double getDensity() {
        return density;
    }

    public double getRestitution() {
        return restitution;
    }

    public double getArea() {
        return area;
    }
}

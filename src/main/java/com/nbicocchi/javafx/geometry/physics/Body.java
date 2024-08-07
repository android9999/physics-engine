package com.nbicocchi.javafx.geometry.physics;


public class Body {

    public enum ShapeType
    {
        Circle,
        Box,
        Ellipse,
        Triangle,
        Polygon,
        Rectangle
    }

    private Vector position;
    private Vector linearVelocity;
    private float rotation;
    private float rotationVelocity;

    public float mass;
    public float density;
    public float restitution;
    public float area;

    public boolean active;

    public float radius;
    public float height;
    public float width;

    public ShapeType shapeType;

    public Body(Vector position,
    Vector linearVelocity,
    float rotation,
    float rotationVelocity,
    float density,
    float restitution,
    float area,
    boolean active,
    float radius,
    float height,
    float width,
    ShapeType shapeType)
    {

        this.position = position;
        this.linearVelocity = linearVelocity;
        this.rotation = rotation;
        this.rotationVelocity = rotationVelocity;
        this.density = density;
        this.restitution = restitution;
        this.area = area;
        this.active = active;
        this.radius = radius;
        this.height = height;
        this.width = width;
        this.shapeType = shapeType;

        mass = area * density;
    }
}

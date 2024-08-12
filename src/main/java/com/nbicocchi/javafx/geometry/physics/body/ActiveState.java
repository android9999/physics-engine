package com.nbicocchi.javafx.geometry.physics.body;

public class ActiveState {
    private boolean active;

    public ActiveState(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}

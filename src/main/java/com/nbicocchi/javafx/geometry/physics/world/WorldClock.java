package com.nbicocchi.javafx.geometry.physics.world;

import com.nbicocchi.javafx.geometry.physics.world.WorldController;
import javafx.animation.AnimationTimer;

public class WorldClock {
    private final WorldController worldController;
    private final AnimationTimer timer;

    public WorldClock(WorldController worldController) {
        this.worldController = worldController;
        this.timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                updateWorld(now);
            }
        };

        start();
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    private void updateWorld(long now) {
        worldController.update(now); // Update the world state
        worldController.requestRender();
    }
}

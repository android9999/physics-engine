package com.nbicocchi.javafx.geometry.app;

import com.nbicocchi.javafx.geometry.physics.world.WorldController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import com.nbicocchi.javafx.geometry.physics.world.WorldClock;

public class App extends Application {
    private UIComponents uiComponents;
    private WorldController worldController;
    private WorldClock worldClock;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        worldController = new WorldController(uiComponents);
        uiComponents = new UIComponents(worldController);
        worldController.setUIComponents(uiComponents);

        BorderPane root = new BorderPane();
        root.setBottom(uiComponents.getHBox());
        root.setCenter(uiComponents.getCanvasPane());

        Scene scene = new Scene(root);
        primaryStage.setTitle("SOLID Refactored Geometry App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

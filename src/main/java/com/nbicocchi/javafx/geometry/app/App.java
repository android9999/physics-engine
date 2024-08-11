package com.nbicocchi.javafx.geometry.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class App extends Application {
    private UIComponents uiComponents;
    private WorldController worldController;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        worldController = new WorldController();
        uiComponents = new UIComponents(worldController);

        BorderPane root = new BorderPane();
        root.setBottom(uiComponents.getHBox());
        root.setCenter(uiComponents.getCanvasPane());

        Scene scene = new Scene(root);
        primaryStage.setTitle("SOLID Refactored Geometry App");
        primaryStage.setScene(scene);
        primaryStage.show();

        uiComponents.startAnimationTimer();
    }
}

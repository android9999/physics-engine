package com.nbicocchi.javafx.geometry;

import com.nbicocchi.javafx.geometry.physics.World;
import com.nbicocchi.javafx.geometry.shapes.Circle;
import com.nbicocchi.javafx.geometry.shapes.DrawableShape;
import com.nbicocchi.javafx.geometry.shapes.Rectangle;
import com.nbicocchi.javafx.geometry.shapes.Triangle;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class App extends Application {
    Canvas canvas;
    Pane canvasPane;
    ColorPicker cpForeground;
    ColorPicker cpBackground;
    Slider slider;
    ChoiceBox<String> shapeChooser;
    DrawableShape selected;

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) {

        HBox hbox = new HBox();
        hbox.setSpacing(10);
        hbox.setAlignment(Pos.BOTTOM_CENTER);
        hbox.setPadding(new Insets(20));
        shapeChooser = new ChoiceBox<>();
        shapeChooser.getItems().addAll("Circle", "Rectangle", "Triangle");
        shapeChooser.getSelectionModel().select("Circle");
        slider = new Slider(50, 150, 100);
        slider.setTooltip(new Tooltip("Circle Radius"));
        slider.setShowTickMarks(true);
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (selected != null) {
                Rectangle2D bounds = new Rectangle2D(selected.getBounds().getMinX(), selected.getBounds().getMinY(), (double) newValue, (double) newValue);
                selected.setBounds(bounds);
                paint();
            }
        });
        cpForeground = new ColorPicker();
        cpForeground.setTooltip(new Tooltip("Circle Color"));
        cpForeground.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (selected != null) {
                selected.setColor(newValue);
            }
            paint();
        });
        cpBackground = new ColorPicker();
        cpBackground.valueProperty().addListener((observable, oldValue, newValue) -> paint());
        cpBackground.setTooltip(new Tooltip("Background Color"));
        cpBackground.setValue(Color.web("#000000"));
        hbox.getChildren().addAll(shapeChooser, cpBackground, cpForeground, slider);
        canvas = new Canvas(600, 600);
        canvasPane = new Pane();
        canvasPane.getChildren().add(canvas);
        canvasPane.setOnMousePressed(event -> {
            selected = null;
            for (DrawableShape shape : World.shapes) {
                if (shape.getBounds().contains(event.getX(), event.getY())) {
                    selected = shape;
                }
            }
            if (selected == null) {


                World.addShape(event.getX(), event.getY(), shapeChooser.getValue(), slider.getValue(), slider.getValue(), cpForeground.getValue());
            }
            paint();
        });
        canvasPane.setOnMouseDragged(event -> {
            if (selected != null) {
                Rectangle2D bounds = new Rectangle2D(event.getX(), event.getY(), selected.getBounds().getWidth(), selected.getBounds().getHeight());
                selected.setBounds(bounds);
                paint();
            }
        });
        BorderPane bp = new BorderPane();
        bp.setBottom(hbox);
        bp.setCenter(canvasPane);
        bp.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.BACK_SPACE) {
                if (selected != null) {
                    World.shapes.remove(selected);
                    selected = null;
                }
            }
            paint();
        });

        Scene scene = new Scene(bp);
        paint();


        Pane root = new Pane();

        primaryStage.setTitle("Animation Timer Example");
        primaryStage.setScene(scene);
        primaryStage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                // Code to be executed on every frame
                update(now);
            }
        };

        timer.start();
    }

    private void update(long now) {
        // Your code here
        World.Update();
    }


    public void paint() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        // background
        gc.setFill(cpBackground.getValue());
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        // shapes
        for (DrawableShape s : World.shapes) {
            s.paint(gc);
        }
        // selected shape
        if (selected != null) {
            gc.setStroke(Color.YELLOW);
            gc.setFill(Color.YELLOW);
            gc.setLineWidth(5);
            gc.strokeRect(selected.getBounds().getMinX(), selected.getBounds().getMinY(), selected.getBounds().getWidth(), selected.getBounds().getHeight());
        }
    }
}



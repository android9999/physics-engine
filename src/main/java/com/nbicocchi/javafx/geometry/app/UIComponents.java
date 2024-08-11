package com.nbicocchi.javafx.geometry.app;

import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class UIComponents {
    private final Canvas canvas;
    private final Pane canvasPane;
    private final ColorPicker cpForeground;
    private final ColorPicker cpBackground;
    private final Slider slider;
    private final ChoiceBox<String> shapeChooser;
    private final HBox hbox;
    private final WorldController worldController;

    public UIComponents(WorldController worldController) {
        this.worldController = worldController;

        canvas = new Canvas(600, 600);
        canvasPane = new Pane(canvas);

        shapeChooser = new ChoiceBox<>();
        shapeChooser.setItems(FXCollections.observableArrayList(worldController.getShapeTypes()));
        shapeChooser.getSelectionModel().select("Circle");

        slider = new Slider(10, 25, 10);
        slider.setTooltip(new Tooltip("Circle Radius"));
        slider.setShowTickMarks(true);

        cpForeground = new ColorPicker();
        cpForeground.setTooltip(new Tooltip("Circle Color"));

        cpBackground = new ColorPicker();
        cpBackground.setTooltip(new Tooltip("Background Color"));
        cpBackground.setValue(Color.web("#000000"));

        hbox = new HBox(shapeChooser, cpBackground, cpForeground, slider);
        hbox.setSpacing(10);
        hbox.setAlignment(Pos.BOTTOM_CENTER);
        hbox.setPadding(new Insets(20));

        setupEventHandlers();
    }

    public HBox getHBox() {
        return hbox;
    }

    public Pane getCanvasPane() {
        return canvasPane;
    }

    public void startAnimationTimer() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                worldController.update(now);
                paint();
            }
        };
        timer.start();
    }

    private void setupEventHandlers() {
        canvasPane.setOnMousePressed(event -> {
            if (!worldController.selectShape(event.getX(), event.getY())) {
                worldController.addShape(shapeChooser.getValue(), slider.getValue(), event.getX(), event.getY(), cpForeground.getValue());
            }
            paint();
        });

        canvasPane.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.BACK_SPACE) {
                worldController.removeSelectedShape();
                paint();
            }
        });

        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            worldController.updateSelectedShapeSize(newValue.doubleValue());
            paint();
        });

        cpForeground.valueProperty().addListener((observable, oldValue, newValue) -> {
            worldController.updateSelectedShapeColor(newValue);
            paint();
        });

        cpBackground.valueProperty().addListener((observable, oldValue, newValue) -> paint());
    }

    private void paint() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(cpBackground.getValue());
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        worldController.render(gc);
    }
}

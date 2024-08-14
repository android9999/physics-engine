package com.nbicocchi.javafx.geometry.app;

import com.nbicocchi.javafx.geometry.physics.world.WorldController;
import com.nbicocchi.javafx.geometry.physics.world.WorldProperties;
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

    private final Slider areaSlider;
    private final Slider angleSlider;
    private final Slider speedSlider;

    private final ChoiceBox<String> shapeChooser;
    private final HBox hboxLow;
    private final HBox hboxHigh;

    private final WorldController worldController;


    public UIComponents(WorldController worldController) {
        this.worldController = worldController;

        canvas = new Canvas(WorldProperties.WIDTH, WorldProperties.HEIGHT);
        canvasPane = new Pane(canvas);

        shapeChooser = new ChoiceBox<>();
        importShapeTypes();
        shapeChooser.getSelectionModel().select("Circle");

        areaSlider = new Slider(WorldProperties.MINSIZE, WorldProperties.MAXSIZE, 10);
        areaSlider.setTooltip(new Tooltip("Circle Radius"));
        areaSlider.setShowTickMarks(true);

        angleSlider = new Slider(0, Math.TAU, 1);
        angleSlider.setTooltip(new Tooltip("velocity's angle"));
        angleSlider.setShowTickMarks(true);

        speedSlider = new Slider(WorldProperties.MINSPEED, WorldProperties.MAXSPEED, 5);
        speedSlider.setTooltip(new Tooltip("velocity's magnitude"));
        speedSlider.setShowTickMarks(true);

        cpForeground = new ColorPicker();
        cpForeground.setTooltip(new Tooltip("Circle Color"));

        cpBackground = new ColorPicker();
        cpBackground.setTooltip(new Tooltip("Background Color"));
        cpBackground.setValue(Color.web("#000000"));

        hboxLow = new HBox(shapeChooser, cpBackground, cpForeground, areaSlider);
        hboxLow.setSpacing(10);
        hboxLow.setAlignment(Pos.BOTTOM_CENTER);
        hboxLow.setPadding(new Insets(20));

        hboxHigh = new HBox(angleSlider, speedSlider);
        hboxHigh.setSpacing(10);
        hboxHigh.setAlignment(Pos.TOP_CENTER);
        hboxHigh.setPadding(new Insets(20));

        setupEventHandlers();
    }

    public HBox getHBoxLow() {
        return hboxLow;
    }

    public HBox getHBoxHigh() {
        return hboxHigh;
    }

    public Pane getCanvasPane() {
        return canvasPane;
    }

    private void setupEventHandlers() {
        canvasPane.setOnMousePressed(event -> {
            if (!worldController.selectShape(event.getX(), event.getY())) {
                worldController.addShape(shapeChooser.getValue(), areaSlider.getValue(), event.getX(), event.getY(), cpForeground.getValue(), angleSlider.getValue(), speedSlider.getValue());
            }
            paint();
        });

        canvasPane.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.BACK_SPACE) {
                worldController.removeSelectedShape();
                paint();
            }
        });

        areaSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            worldController.updateSelectedShapeSize(newValue.doubleValue());
            paint();
        });

        cpForeground.valueProperty().addListener((observable, oldValue, newValue) -> {
            worldController.updateSelectedShapeColor(newValue);
            paint();
        });

        cpBackground.valueProperty().addListener((observable, oldValue, newValue) -> paint());
    }

    public void paint() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(cpBackground.getValue());
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        worldController.render(gc);
    }

    private void importShapeTypes()
    {
        shapeChooser.setItems(FXCollections.observableArrayList(worldController.getShapeTypes()));
    }

}

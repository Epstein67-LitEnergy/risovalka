package org.example.model.factory;

import java.awt.*;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShapeState {
    private  ShapeType shapeType;
    private Color color;
    private boolean isFill;

    public ShapeState(ShapeType shapeType, Color color, boolean isFill) {
        this.shapeType = Objects.requireNonNullElse(shapeType, ShapeType.RECTANGULAR_SHAPE);
        this.color =Objects.requireNonNullElse(color, Color.BLACK);
        this.isFill = isFill;
    }
}

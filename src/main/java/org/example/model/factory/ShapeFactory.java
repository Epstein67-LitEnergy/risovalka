package org.example.model.factory;

import org.example.model.MyShape;
import org.example.model.fill.Fill;
import org.example.model.fill.FillBehavior;
import org.example.model.fill.NoFill;

import java.awt.*;

public class ShapeFactory {
    public MyShape createShape(ShapeType shapeType, Color color, boolean isFill) {
        FillBehavior fillBehavior = isFill ? new Fill() : new NoFill();
        return shapeType.createShape(fillBehavior, color);
    }
}

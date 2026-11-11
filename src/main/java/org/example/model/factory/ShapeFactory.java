package org.example.model.factory;

import org.example.model.MyShape;
import org.example.model.fill.Fill;
import org.example.model.fill.FillBehavior;
import org.example.model.fill.NoFill;

import java.awt.*;

public class ShapeFactory {
    private static ShapeFactory instance;
    private  ShapeState defaultShape;

    public ShapeFactory() {
        defaultShape = new ShapeState(ShapeType.RECTANGULAR_SHAPE, Color.RED, false);

    }

    public static  ShapeFactory getInstance(){
        synchronized (ShapeFactory.class) {
            if (instance == null) {
                instance = new ShapeFactory();
            }
            return instance;
        }
    }

    public MyShape createShape(ShapeType shapeType, Color color, boolean isFill) {
        FillBehavior fillBehavior = isFill ? new Fill() : new NoFill();
        return shapeType.createShape(fillBehavior, color);
    }

    public MyShape createShape(ShapeState shapeState) {
        return createShape(shapeState.getShapeType(), shapeState.getColor(), shapeState.isFill());
    }

    public MyShape createDefaultShape() {
        return createShape(defaultShape);
    }
}

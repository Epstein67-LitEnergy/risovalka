package org.example.controller.action;

import org.example.model.Model;
import org.example.model.MyShape;
import org.example.model.factory.ShapeFactory;

import java.awt.*;

public class ActionDraw implements AppAction{
    private Point firstPoint;
    private Point secondPoint;
    private final Model model;
    private final ShapeFactory shapeFactory;

    public ActionDraw(Model model) {
        this.model = model;
        shapeFactory = ShapeFactory.getInstance();
        model.setDrawableShape(shapeFactory.createDefaultShape());
    }

    @Override
    public void mousePressed(Point point) {
        firstPoint = point;
        MyShape shape =shapeFactory.createDefaultShape();
        model.setDrawableShape(shape);
        model.addShape(shape);
    }

    @Override
    public void mouseDragged(Point point) {
        model.changeShape(firstPoint, point);

    }
}

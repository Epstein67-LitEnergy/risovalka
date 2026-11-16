package org.example.controller.action;

import org.example.model.Model;
import org.example.model.MyShape;
import org.example.model.factory.ShapeFactory;
import org.example.model.factory.ShapeState;

import java.awt.*;

public class ActionDraw implements AppAction{
    private Point firstPoint;
    private Point secondPoint;
    private final Model model;
    private final ShapeFactory shapeFactory;
    private  final  ShapeState state;

    public ActionDraw(Model model, ShapeState state) {
        this.model = model;
        this.state = state;
        shapeFactory = ShapeFactory.getInstance();
        model.setDrawableShape(shapeFactory.createShape(state));
    }

    @Override
    public void mousePressed(Point point) {
        firstPoint = point;
        MyShape shape =shapeFactory.createShape(state);
        model.setDrawableShape(shape);
        model.addShape(shape);
    }

    @Override
    public void mouseDragged(Point point) {
        model.changeShape(firstPoint, point);

    }
}

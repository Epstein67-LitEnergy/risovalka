package org.example.controller.action;

import org.example.model.Model;
import org.example.model.MyShape;

import java.awt.*;

public class ActionMove implements AppAction {
    private final Model model;
    private MyShape movingShape;
    private Point lastPoint;

    public ActionMove(Model model) {
        this.model = model;
    }

    @Override
    public void mousePressed(Point point) {
        movingShape = model.findShape(point);
        lastPoint = point;
    }

    @Override
    public void mouseDragged(Point point) {
        if (movingShape != null && lastPoint != null) {
            double dx = point.getX() - lastPoint.getX();
            double dy = point.getY() - lastPoint.getY();
            model.moveShape(movingShape, dx, dy);
            lastPoint = point;
        }
    }
}

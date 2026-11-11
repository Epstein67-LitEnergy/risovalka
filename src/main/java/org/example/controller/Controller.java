package org.example.controller;

import org.example.model.Model;
import org.example.model.MyShape;
import org.example.model.factory.ShapeFactory;
import org.example.model.factory.ShapeType;
import org.example.model.fill.NoFill;
import org.example.view.MyFrame;
import org.example.view.MyPanel;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Controller {
    private static Controller instance;
    private final Model model;
    private final MyFrame frame;
    private final MyPanel panel;
    private Point2D firstPoint;
    private Point2D secondPoint;
    private ShapeFactory shapeFactory;

    public Controller() {
        model = new Model();
        shapeFactory=ShapeFactory.getInstance();

        MyShape shape = shapeFactory.createDefaultShape();
        model.setMyShape(shape);

        panel = new MyPanel(this);
        // TODO: Поменять наблюдатель на более современную реализацию
        model.addObserver(panel);

        frame = new MyFrame();
        frame.setPanel(panel);
        panel.revalidate();
        panel.repaint();
    }

    public static synchronized Controller getInstance( ) {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;

    }
    public void mousePressed(Point2D p){
        firstPoint = p;
        MyShape shape =shapeFactory.createDefaultShape();
        model.addShape(shape);
        model.setDrawableShape(shape);
        model.addShape(shape);


    }
    public void mouseDragged(Point2D p){
        secondPoint = p;
        model.changeShape(firstPoint, secondPoint);
    }

    public void draw(Graphics2D g2) {
        model.draw(g2);
    }
}

package org.example.controller;

import org.example.controller.action.ActionDraw;
import org.example.controller.action.ActionMove;
import org.example.controller.action.AppAction;
import org.example.model.Model;
import org.example.model.MyShape;
import org.example.model.factory.ShapeFactory;
import org.example.model.factory.ShapeState;
import org.example.model.factory.ShapeType;
import org.example.model.fill.NoFill;
import org.example.view.MenuCreator;
import org.example.view.MyFrame;
import org.example.view.MyPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Controller {
    private static Controller instance;
    private final Model model;
    private final MyFrame frame;
    private final MyPanel panel;
    private AppAction action;
    private ShapeState shapeState;

    public Controller() {
        model = new Model();

        panel = new MyPanel(this);
        model.addObserver(panel);

        shapeState = new ShapeState(ShapeType.RECTANGULAR_SHAPE, Color.RED, true);
        action = new ActionDraw(model, shapeState);
        frame = new MyFrame();
        frame.setPanel(panel);

        MenuCreator menuCreator = MenuCreator.getInstance();
        menuCreator.setController(this);
        menuCreator.setShapeState(shapeState);

        JMenuBar menuBar = menuCreator.createMenu();
        frame.setJMenuBar(menuBar);
        JToolBar jToolBar =menuCreator.crateToolBar(JToolBar.VERTICAL);
        frame.add(jToolBar, BorderLayout.EAST);

        panel.revalidate();
        panel.repaint();
    }

    public static synchronized Controller getInstance( ) {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;

    }
    public void mousePressed(Point point){
        action.mousePressed(point);

    }
    public void mouseDragged(Point point){
        action.mouseDragged(point);
    }

    public void draw(Graphics2D g2) {
        model.draw(g2);
    }

    public void switchActionDraw() {
        action = new ActionDraw(model, shapeState);
    }

    public void switchActionMove() {
        action = new ActionMove(model);
    }
}

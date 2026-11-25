package org.example.view;

import lombok.Setter;
import org.example.controller.Controller;
import org.example.controller.action.AppAction;
import org.example.view.menu.*;
import org.example.model.factory.ShapeState;
import org.example.model.factory.ShapeType;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RectangularShape;
import java.net.URL;
import java.util.ArrayList;

public class MenuCreator {
    private  static MenuCreator instance;

    @Setter
    private ShapeState shapeState;
    @Setter
    private Controller controller;

    private  JRadioButtonMenuItem fillItem;
    private JRadioButtonMenuItem noFillItem;

    private  JRadioButtonMenuItem squareItem;
    private JRadioButtonMenuItem circleItem;

    private JRadioButtonMenuItem drawItem;
    private JRadioButtonMenuItem moveItem;

    private JRadioButtonMenuItem rgbItem;

    private  MenuCreator() {

    }

    public static  MenuCreator getInstance() {
        synchronized (MenuCreator.class) {
            if (instance == null) {
                instance = new MenuCreator();
            }
            return instance;
        }
    }

    public JMenuBar createMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu shapeMenu = createShapeMenu();
        menuBar.add(shapeMenu);

        JMenu colorMenu = createColorMenu();
        menuBar.add(colorMenu);

        JMenu fillMenu = createFillMenu();
        menuBar.add(fillMenu);

        JMenu actionMenu = createActionMenu();
        menuBar.add(actionMenu);

        return menuBar;
    }

    public JToolBar crateToolBar(int orientation) {
        ArrayList<Action> actionArrayList = createToolBarItems();
        JToolBar jToolBar = new JToolBar(orientation);
        actionArrayList.forEach(jToolBar::add);

        return jToolBar;
    }

    private ArrayList<Action> createToolBarItems() {
        ArrayList<Action> menuItems = new ArrayList<>();

        URL drawUrl = getClass().getClassLoader().getResource("ico/draw_16x16.png");
        ImageIcon drawIco = drawUrl == null ? null : new ImageIcon(drawUrl);
        AppCommand drawCommand = new SwitchAction(controller, SwitchAction.ActionType.DRAW, drawItem);
        menuItems.add(new CommandActionListener("Рисовать", drawIco, drawCommand));

        URL moveUrl = getClass().getClassLoader().getResource("ico/move_16x16.png");
        ImageIcon moveIco = moveUrl == null ? null : new ImageIcon(moveUrl);
        AppCommand moveCommand = new SwitchAction(controller, SwitchAction.ActionType.MOVE, moveItem);
        menuItems.add(new CommandActionListener("Перемещать", moveIco, moveCommand));

        URL fillUrl = getClass().getClassLoader().getResource("ico/fill_16x16.png");
        ImageIcon fillIco = fillUrl == null ? null : new ImageIcon(fillUrl);
        AppCommand fillCommand = new SwitchFill(true, shapeState, fillItem, noFillItem);
        menuItems.add(new CommandActionListener("С заливкой", fillIco, fillCommand));

        URL noFillUrl = getClass().getClassLoader().getResource("ico/no_fill_16x16.png");
        ImageIcon noFillIco = noFillUrl == null ? null : new ImageIcon(noFillUrl);
        AppCommand noFillCommand = new SwitchFill(false, shapeState, fillItem, noFillItem);
        menuItems.add(new CommandActionListener("Без заливки", noFillIco, noFillCommand));

        URL rectangularUrl = getClass().getClassLoader().getResource("ico/rectangular_16x16.png");
        ImageIcon rectangularIco = rectangularUrl == null ? null : new ImageIcon(rectangularUrl);
        AppCommand rectangularCommand = new SwitchShape(shapeState, ShapeType.RECTANGULAR_SHAPE, squareItem);
        menuItems.add(new CommandActionListener("Прмоугольник", rectangularIco, rectangularCommand));

        URL circleUrl = getClass().getClassLoader().getResource("ico/ellipse_16x16.png");
        ImageIcon circleIco = circleUrl == null ? null : new ImageIcon(circleUrl);
        AppCommand circleCommand = new SwitchShape(shapeState, ShapeType.ELLIPSE_SHAPE, circleItem);
        menuItems.add(new CommandActionListener("Круг", circleIco, circleCommand));

        URL rgbUrl = getClass().getClassLoader().getResource("ico/color_16x16.png");
        ImageIcon rgbIco = rgbUrl == null ? null : new ImageIcon(rgbUrl);
        AppCommand rgbCommand = new SwitchColor(shapeState, false, Color.BLACK, rgbItem);
        menuItems.add(new CommandActionListener("Выбор цвета", rgbIco, rgbCommand));

        return menuItems;
    }

    private JMenu createActionMenu() {
        ButtonGroup group = new ButtonGroup();
        JMenu actionMenu = new JMenu("Действие");

        drawItem = new JRadioButtonMenuItem("Рисовать", true);
        drawItem.addActionListener(action -> controller.switchActionDraw());
        actionMenu.add(drawItem);
        group.add(drawItem);

        moveItem = new JRadioButtonMenuItem("Перемещать");
        moveItem.addActionListener(new CommandActionListener(
                new SwitchAction(controller, SwitchAction.ActionType.MOVE, moveItem)));
        actionMenu.add(moveItem);
        group.add(moveItem);

        return actionMenu;
    }

    private JMenu createColorMenu() {
        ButtonGroup group = new ButtonGroup();
        JMenu shapeMenu = new JMenu("Цвет");

        JRadioButtonMenuItem redItem = new JRadioButtonMenuItem("Красный", true);
        redItem.addActionListener(action -> shapeState.setColor(Color.RED));
        shapeMenu.add(redItem);
        group.add(redItem);

        JRadioButtonMenuItem blueItem = new JRadioButtonMenuItem("Синий");
        blueItem.addActionListener(action -> shapeState.setColor(Color.BLUE));
        shapeMenu.add(blueItem);
        group.add(blueItem);

        rgbItem = new JRadioButtonMenuItem("Выбор цвета");
        rgbItem.addActionListener(action -> shapeState.setColor(JColorChooser.showDialog(null,"Выбор цвета", Color.BLACK)));
        shapeMenu.add(rgbItem);
        group.add(rgbItem);

        return shapeMenu;
    }

    private JMenu createFillMenu() {
        ButtonGroup group = new ButtonGroup();
        JMenu fillMenu = new JMenu("Заливка");

        fillItem = new JRadioButtonMenuItem("С заливкой");
        noFillItem = new JRadioButtonMenuItem("Без заливки");
        fillItem.addActionListener(new CommandActionListener(new SwitchFill(true, shapeState, fillItem, noFillItem)));
        noFillItem.addActionListener(new CommandActionListener(new SwitchFill(false, shapeState, fillItem, noFillItem)));
        fillMenu.add(fillItem);
        group.add(fillItem);

        fillMenu.add(noFillItem);
        group.add(noFillItem);

        return fillMenu;
    }

    private JMenu createShapeMenu() {
        ButtonGroup group = new ButtonGroup();
        JMenu shapeMenu = new JMenu("Фигура");

        squareItem = new JRadioButtonMenuItem("Прямоугольник", true);
        circleItem = new JRadioButtonMenuItem("Круг", true);
        squareItem.addActionListener(new CommandActionListener(new SwitchShape(shapeState, ShapeType.RECTANGULAR_SHAPE, squareItem)));
        circleItem.addActionListener(new CommandActionListener(new SwitchShape(shapeState, ShapeType.ELLIPSE_SHAPE, circleItem)));

        shapeMenu.add(squareItem);
        group.add(squareItem);

        shapeMenu.add(circleItem);
        group.add(circleItem);

        return shapeMenu;
    }
}

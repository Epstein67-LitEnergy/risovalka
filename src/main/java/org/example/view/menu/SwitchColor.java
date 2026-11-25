package org.example.view.menu;

import lombok.AllArgsConstructor;
import org.example.model.factory.ShapeState;

import javax.swing.*;
import java.awt.*;

@AllArgsConstructor
public class SwitchColor implements AppCommand{
    private ShapeState shapeState;
    private boolean useDefault;
    private Color defaultColor;
    private JRadioButtonMenuItem radioButton;


    @Override
    public void execute() {
        radioButton.setSelected(!useDefault || defaultColor != null);

        Color color  = useDefault ? defaultColor : JColorChooser.showDialog(null, "Выбор цвета", Color.BLACK);

        shapeState.setColor(color != null ? color : defaultColor);
    }
}

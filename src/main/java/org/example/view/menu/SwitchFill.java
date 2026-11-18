package org.example.view.menu;

import lombok.AllArgsConstructor;
import org.example.model.factory.ShapeState;

import javax.swing.*;

@AllArgsConstructor
public class SwitchFill implements  AppCommand{
    private  boolean fill;
    private ShapeState shapeState;

    private JRadioButtonMenuItem fillButton;
    private JRadioButtonMenuItem noFillButton;

    @Override
    public void execute(){
        shapeState.setFill(fill);
        fillButton.setSelected(fill);
        noFillButton.setSelected(!fill);
    }
}

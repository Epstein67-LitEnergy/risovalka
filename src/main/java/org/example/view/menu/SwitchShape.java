package org.example.view.menu;

import lombok.AllArgsConstructor;
import org.example.model.factory.ShapeState;
import org.example.model.factory.ShapeType;

import javax.swing.*;

public class SwitchShape implements AppCommand {
    private ShapeState shapeState;
    private ShapeType shapeType;
    private JRadioButtonMenuItem radioButton;


    public SwitchShape(ShapeState shapeState, ShapeType shapeType, JRadioButtonMenuItem radioButton) {
        this.shapeState = shapeState;
        this.shapeType = shapeType;
        this.radioButton = radioButton;
    }

    @Override
    public void execute() {
        shapeState.setShapeType(shapeType);
        radioButton.setSelected(true);
    }
}

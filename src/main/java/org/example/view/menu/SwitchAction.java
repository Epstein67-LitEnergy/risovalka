package org.example.view.menu;

import lombok.AllArgsConstructor;
import org.example.controller.Controller;

import javax.swing.*;

@AllArgsConstructor
public class  SwitchAction implements AppCommand {
    public enum ActionType {
        DRAW, MOVE
    }

    private Controller controller;
    private ActionType actionType;
    private JRadioButtonMenuItem radioButton;

    @Override
    public void execute() {
        switch (actionType) {
            case DRAW:
                controller.switchActionDraw();
                break;
            case MOVE:
                controller.switchActionMove();
                break;
        }
        radioButton.setSelected(true);
    }
}

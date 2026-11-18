package org.example.view.menu;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CommandActionListener extends AbstractAction {
    private static final String COMMAND = "Command";

    public CommandActionListener(AppCommand appCommand) {
        putValue(COMMAND, appCommand);
    }

    public CommandActionListener(String name, AppCommand appCommand) {
        super(name);
        putValue(COMMAND, appCommand);
    }

    public CommandActionListener(String name, Icon icon, AppCommand appCommand) {
        super(name, icon);
        putValue(COMMAND, appCommand);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        AppCommand command = (AppCommand) getValue(COMMAND);
        command.execute();
    }

}

package org.epoxide.ld44.client.gui;

import org.epoxide.ld44.LD44;
import org.epoxide.ld44.client.gui.components.GuiButton;

public class GuiBountyBoard extends Gui {

    public GuiBountyBoard(){
        this.components.add(new GuiButton(LD44.getInstance().getFont(), "TestingABC", 100, 100, 500,50));
    }
}

package org.epoxide.ld44.client.gui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.epoxide.ld44.LD44;
import org.epoxide.ld44.client.gui.components.GuiComponent;

import java.util.ArrayList;
import java.util.List;


public abstract class Gui {

    public List<GuiComponent> components = new ArrayList<GuiComponent>();

    public void render(SpriteBatch batch) {

        for (GuiComponent component : this.components) {
            component.render(batch);
        }
    }

    public void touchDown(int screenX, int screenY, int pointer, int button) {
    }

    public void keyUp(int keycode) {
        LD44.getInstance().setCurrentGUI(null);
    }

    public void keyDown(int keycode) {
    }

    public void mouseMoved(int screenX, int screenY) {
    }
}

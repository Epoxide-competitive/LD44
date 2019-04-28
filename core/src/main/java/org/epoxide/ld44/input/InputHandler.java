package org.epoxide.ld44.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import org.epoxide.ld44.LD44;
import org.epoxide.ld44.client.gui.Gui;
import org.epoxide.ld44.entity.EntityPlayer;
import org.epoxide.ld44.tile.Tile;
import org.epoxide.ld44.tile.TileMap;
import org.epoxide.ld44.tile.Tiles;

import java.util.List;

import static org.epoxide.ld44.client.world.RenderWorld.TILE_SIZE;

public class InputHandler extends InputAdapter {

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        
        LD44 game = LD44.getInstance();
        Gui gui = game.getCurrentGUI();
        
        if (gui != null) {
            gui.touchDown(screenX, screenY, pointer, button);
        } else if (LD44.EDITOR) {
            TileMap tileMap = game.getClientPlayer().getTileMap();
            float width = Gdx.graphics.getWidth() / TILE_SIZE;
            float height = Gdx.graphics.getHeight() / TILE_SIZE;

            float offsetWidth = width / 2.0f - 0.5f;
            float offsetHeight = height / 2.0f - 0.5f;

            int x = (int) ((screenX / TILE_SIZE + game.getClientPlayer().getX()) - offsetWidth);
            int y = (int) ((screenY / TILE_SIZE + game.getClientPlayer().getY()) - offsetHeight);
            if (x >= 0 && x < tileMap.getWidth() && y >= 0 && y < tileMap.getHeight()) {
                LD44.EDITOR_X = x;
                LD44.EDITOR_Y = y;
            }
        }
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        if (LD44.getInstance().getCurrentGUI() != null) {
            LD44.getInstance().getCurrentGUI().mouseMoved(screenX, screenY);
            return true;
        }
        return super.mouseMoved(screenX, screenY);
    }

    @Override
    public boolean keyDown(int keycode) {
        if (LD44.getInstance().getCurrentGUI() != null) {
            LD44.getInstance().getCurrentGUI().keyDown(keycode);
            return true;
        }
        return super.keyDown(keycode);
    }

    @Override
    public boolean keyUp(int keycode) {
        if (LD44.getInstance().getCurrentGUI() != null) {
            LD44.getInstance().getCurrentGUI().keyUp(keycode);
            return true;
        }
        
        EntityPlayer player = LD44.getInstance().getClientPlayer();
        
        switch (keycode) {
            case Input.Keys.W:
                player.setY(player.getY() - 1);
                break;
            case Input.Keys.A:
                player.setX(player.getX() - 1);
                break;
            case Input.Keys.S:
                player.setY(player.getY() + 1);
                break;
            case Input.Keys.D:
                player.setX(player.getX() + 1);
                break;

            case Input.Keys.TAB:
                if (LD44.EDITOR) {
                    Tile t = player.getTileMap().getTile(LD44.EDITOR_X, LD44.EDITOR_Y);
                    List<Tile> l = Tiles.REGISTRY.getValues();
                    boolean isPressed = Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT);
                    int i = l.indexOf(t) + (isPressed ? -1 : 1);
                    if (i < 0)
                        i = l.size();
                    if (i >= l.size())
                        i = 0;
                    player.getTileMap().setTile(LD44.EDITOR_X, LD44.EDITOR_Y, l.get(i));
                }
                break;
        }
        return true;
    }
}

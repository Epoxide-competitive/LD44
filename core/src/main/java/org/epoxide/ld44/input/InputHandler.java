package org.epoxide.ld44.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import org.epoxide.ld44.LD44;
import org.epoxide.ld44.tile.Tile;
import org.epoxide.ld44.tile.TileMap;
import org.epoxide.ld44.tile.Tiles;

import java.util.List;

import static org.epoxide.ld44.client.world.RenderWorld.TILE_SIZE;

public class InputHandler extends InputAdapter {

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (LD44.CURRENT_GUI != null) {
            LD44.CURRENT_GUI.touchDown(screenX, screenY, pointer, button);
        } else if (LD44.EDITOR) {
            TileMap tileMap = LD44.ENTITYPLAYER.getTileMap();
            float width = Gdx.graphics.getWidth() / TILE_SIZE;
            float height = Gdx.graphics.getHeight() / TILE_SIZE;

            float offsetWidth = width / 2.0f - 0.5f;
            float offsetHeight = height / 2.0f - 0.5f;

            int x = (int) ((screenX / TILE_SIZE + LD44.ENTITYPLAYER.getX()) - offsetWidth);
            int y = (int) ((screenY / TILE_SIZE + LD44.ENTITYPLAYER.getY()) - offsetHeight);
            if (x >= 0 && x < tileMap.getWidth() && y >= 0 && y < tileMap.getHeight()) {
                LD44.EDITOR_X = x;
                LD44.EDITOR_Y = y;
            }
        }
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        if (LD44.CURRENT_GUI != null) {
            LD44.CURRENT_GUI.mouseMoved(screenX, screenY);
            return true;
        }
        return super.mouseMoved(screenX, screenY);
    }

    @Override
    public boolean keyDown(int keycode) {
        if (LD44.CURRENT_GUI != null) {
            LD44.CURRENT_GUI.keyDown(keycode);
            return true;
        }
        return super.keyDown(keycode);
    }

    @Override
    public boolean keyUp(int keycode) {
        if (LD44.CURRENT_GUI != null) {
            LD44.CURRENT_GUI.keyUp(keycode);
            return true;
        }
        switch (keycode) {
            case Input.Keys.W:
                LD44.ENTITYPLAYER.setY(LD44.ENTITYPLAYER.getY() - 1);
                break;
            case Input.Keys.A:
                LD44.ENTITYPLAYER.setX(LD44.ENTITYPLAYER.getX() - 1);
                break;
            case Input.Keys.S:
                LD44.ENTITYPLAYER.setY(LD44.ENTITYPLAYER.getY() + 1);
                break;
            case Input.Keys.D:
                LD44.ENTITYPLAYER.setX(LD44.ENTITYPLAYER.getX() + 1);
                break;

            case Input.Keys.TAB:
                if (LD44.EDITOR) {
                    Tile t = LD44.ENTITYPLAYER.getTileMap().getTile(LD44.EDITOR_X, LD44.EDITOR_Y);
                    List<Tile> l = Tiles.REGISTRY.getValues();
                    boolean isPressed = Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT);
                    int i = l.indexOf(t) + (isPressed ? -1 : 1);
                    if (i < 0)
                        i = l.size();
                    if (i >= l.size())
                        i = 0;
                    LD44.ENTITYPLAYER.getTileMap().setTile(LD44.EDITOR_X, LD44.EDITOR_Y, l.get(i));
                }
                break;
        }
        return true;
    }
}

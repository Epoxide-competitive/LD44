package org.epoxide.ld44.client.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.epoxide.ld44.LD44;
import org.epoxide.ld44.client.tile.Quad;
import org.epoxide.ld44.client.tile.RenderTile;
import org.epoxide.ld44.tile.TileMap;

import java.util.List;

public class RenderWorld {
    public static final float TILE_SIZE = 64.0f;
    private final RenderTile renderTile;

    public RenderWorld() {
        this.renderTile = new RenderTile();
        this.renderTile.registerTextures();
    }

    public void render(SpriteBatch batch, TileMap tileMap) {
        float width = Gdx.graphics.getWidth() / TILE_SIZE;
        float height = Gdx.graphics.getHeight() / TILE_SIZE;

        float offsetWidth = width / 2.0f - 0.5f;
        float offsetHeight = height / 2.0f - 0.5f;

        batch.begin();
        for (int x = 0; x < tileMap.getWidth(); x++) {
            for (int y = 0; y < tileMap.getHeight(); y++) {
                List<Quad> quads = tileMap.getTile(x, y).getQuads(tileMap, x, y);
                for (Quad quad : quads) {
                    if (LD44.EDITOR && LD44.EDITOR_X == x && LD44.EDITOR_Y == y)
                        quad.render(batch, (x - LD44.ENTITYPLAYER.getX() + offsetWidth) * TILE_SIZE, (y - LD44.ENTITYPLAYER.getY() + offsetHeight) * TILE_SIZE, TILE_SIZE, TILE_SIZE, batch.getColor().LIGHT_GRAY.toFloatBits());
                    else
                        quad.render(batch, (x - LD44.ENTITYPLAYER.getX() + offsetWidth) * TILE_SIZE, (y - LD44.ENTITYPLAYER.getY() + offsetHeight) * TILE_SIZE, TILE_SIZE);
                }
            }
        }
        batch.end();
    }
}

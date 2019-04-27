package org.epoxide.ld44.client.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.epoxide.ld44.client.tile.RenderTile;
import org.epoxide.ld44.tile.TileMap;
import org.epoxide.ld44.tile.Tiles;
import org.epoxide.ld44.world.World;

public class RenderWorld {
    private final int TILE_SIZE = 16;
    private final RenderTile renderTile;

    public RenderWorld() {
        this.renderTile = new RenderTile();
        this.renderTile.registerTextures();
    }

    public void render(SpriteBatch batch, World world) {
        TileMap tileMap = world.getTileMaps()[0];

        batch.begin();
        for (int x = 0; x < tileMap.getWidth(); x++) {
            for (int y = 0; y < tileMap.getHeight(); y++) {
                this.renderTile.getQuads(Tiles.STONE).render(batch, x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE);
            }
        }
        batch.end();
    }
}

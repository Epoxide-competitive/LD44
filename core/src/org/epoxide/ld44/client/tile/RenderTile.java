package org.epoxide.ld44.client.tile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PixmapPacker;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import org.epoxide.ld44.tile.Tile;
import org.epoxide.ld44.tile.Tiles;

import java.util.HashMap;
import java.util.Map;

public class RenderTile {

    private Map<Tile, Quad> quads;
    private TextureAtlas atlas;

    public void registerTextures() {
        PixmapPacker packer = new PixmapPacker(4096, 4096, Pixmap.Format.RGB565, 0, false);

        for (Tile tile : Tiles.REGISTRY) {
            packer.pack(tile.getIdentifier().toString(), new Pixmap(Gdx.files.internal("assets/" + tile.getIdentifier().getDomain() + "/textures/tiles/" + tile.getIdentifier().getName() + ".png")));
        }
        this.atlas = packer.generateTextureAtlas(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest, false);
        packer.dispose();

        this.quads = new HashMap<Tile, Quad>();
        for (Tile tile : Tiles.REGISTRY) {
            this.quads.put(tile, new Quad(atlas.findRegion(tile.getIdentifier().toString())));
        }
    }

    public Quad getQuads(Tile tile) {
        return this.quads.get(tile);
    }
}
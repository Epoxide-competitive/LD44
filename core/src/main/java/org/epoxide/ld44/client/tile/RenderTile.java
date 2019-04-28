package org.epoxide.ld44.client.tile;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PixmapPacker;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import org.epoxide.ld44.tile.Tile;
import org.epoxide.ld44.tile.Tiles;

public class RenderTile {
    
    private TextureAtlas atlas;

    public void registerTextures() {
        
        PixmapPacker packer = new PixmapPacker(4096, 4096, Pixmap.Format.RGB565, 0, false);

        for (Tile tile : Tiles.REGISTRY) {
            
            tile.registerTextures(packer);
        }
        
        this.atlas = packer.generateTextureAtlas(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest, false);
        packer.dispose();

        for (Tile tile : Tiles.REGISTRY) {
            
            tile.retrieveQuads(this.atlas);
        }
    }
}

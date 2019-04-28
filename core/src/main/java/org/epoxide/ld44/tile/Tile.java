package org.epoxide.ld44.tile;

import org.epoxide.ld44.client.tile.Quad;
import org.epoxide.ld44.registry.Identifier;
import org.epoxide.ld44.registry.Registerable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.PixmapPacker;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Tile extends Registerable<Tile> {

    public Tile(String id) {

        this.setIdentifier(new Identifier(id));
    }

    public void registerTextures(PixmapPacker packer) {

        packer.pack(this.getIdentifier().toString(), getTileSpriteData());
    }

    private Quad quad;

    public void retrieveQuads(TextureAtlas atlas) {

        quad = new Quad(atlas.findRegion(this.getIdentifier().toString()));
    }

    public Quad getQuad(int x, int y) {

        return quad;
    }

    private Pixmap getTileSpriteData() {

        FileHandle file = Gdx.files.internal("assets/" + this.getIdentifier().getDomain() + "/textures/tiles/" + this.getIdentifier().getName() + ".png");

        if (!file.exists()) {

            Gdx.app.error("LD44", "Missing sprite: " + file.path());
            file = Gdx.files.internal("assets/ld44/textures/tiles/missing_tile.png");
        }

        return new Pixmap(file);
    }
}
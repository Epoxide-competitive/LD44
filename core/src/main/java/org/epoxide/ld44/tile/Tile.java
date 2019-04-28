package org.epoxide.ld44.tile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.PixmapPacker;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import org.epoxide.ld44.client.tile.Quad;
import org.epoxide.ld44.registry.Identifier;
import org.epoxide.ld44.registry.Registerable;

import java.awt.*;
import java.util.Collections;
import java.util.List;

public class Tile extends Registerable<Tile> {

    protected Rectangle boundingBox = new Rectangle(0, 0, 1, 1);
    private List<Quad> quads;

    public Tile(String id) {

        this.setIdentifier(new Identifier(id));
    }

    public Rectangle getBoundingBox() {
        return boundingBox;
    }

    public void registerTextures(PixmapPacker packer) {

        packer.pack(this.getIdentifier().toString(), getTileSpriteData(this.getIdentifier().getName()));
    }

    public void retrieveQuads(TextureAtlas atlas) {

        this.quads = Collections.singletonList(new Quad(atlas.findRegion(this.getIdentifier().toString())));
    }

    public List<Quad> getQuads(TileMap tileMap, int x, int y) {

        return this.quads;
    }

    protected Pixmap getTileSpriteData(String name) {

        FileHandle file = Gdx.files.internal("assets/" + this.getIdentifier().getDomain() + "/textures/tiles/" + name + ".png");

        if (!file.exists()) {

            Gdx.app.error("LD44", "Missing sprite: " + file.path());
            file = Gdx.files.internal("assets/ld44/textures/tiles/missing_tile.png");
        }

        return new Pixmap(file);
    }
}
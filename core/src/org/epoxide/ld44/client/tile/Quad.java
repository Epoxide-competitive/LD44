package org.epoxide.ld44.client.tile;

import static com.badlogic.gdx.graphics.g2d.Batch.C1;
import static com.badlogic.gdx.graphics.g2d.Batch.C2;
import static com.badlogic.gdx.graphics.g2d.Batch.C3;
import static com.badlogic.gdx.graphics.g2d.Batch.C4;
import static com.badlogic.gdx.graphics.g2d.Batch.U1;
import static com.badlogic.gdx.graphics.g2d.Batch.U2;
import static com.badlogic.gdx.graphics.g2d.Batch.U3;
import static com.badlogic.gdx.graphics.g2d.Batch.U4;
import static com.badlogic.gdx.graphics.g2d.Batch.V1;
import static com.badlogic.gdx.graphics.g2d.Batch.V2;
import static com.badlogic.gdx.graphics.g2d.Batch.V3;
import static com.badlogic.gdx.graphics.g2d.Batch.V4;
import static com.badlogic.gdx.graphics.g2d.Batch.X1;
import static com.badlogic.gdx.graphics.g2d.Batch.X2;
import static com.badlogic.gdx.graphics.g2d.Batch.X3;
import static com.badlogic.gdx.graphics.g2d.Batch.X4;
import static com.badlogic.gdx.graphics.g2d.Batch.Y1;
import static com.badlogic.gdx.graphics.g2d.Batch.Y2;
import static com.badlogic.gdx.graphics.g2d.Batch.Y3;
import static com.badlogic.gdx.graphics.g2d.Batch.Y4;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Quad {

    private static final int NUM_VERTICES = 20;
    private final TextureRegion texture;
    private final float[] vertices = new float[NUM_VERTICES];

    public Quad(TextureRegion texture) {

        this.texture = texture;

        final float u1 = texture.getU();
        final float v1 = texture.getV2();
        final float u2 = texture.getU2();
        final float v2 = texture.getV();

        this.vertices[U1] = u1;
        this.vertices[V1] = v1;

        this.vertices[U2] = u1;
        this.vertices[V2] = v2;

        this.vertices[U3] = u2;
        this.vertices[V3] = v2;

        this.vertices[U4] = u2;
        this.vertices[V4] = v1;
    }

    public void render(SpriteBatch batch, float x, float y, float unitScale) {

        this.render(batch, x, y, unitScale, batch.getColor().toFloatBits());
    }

    public void render(SpriteBatch batch, float x, float y, float unitScale, float colorBits) {

        final float x2 = x + texture.getRegionWidth() * unitScale;
        final float y2 = y + texture.getRegionHeight() * unitScale;

        this.vertices[X1] = x;
        this.vertices[Y1] = y;
        this.vertices[C1] = colorBits;

        this.vertices[X2] = x;
        this.vertices[Y2] = y2;
        this.vertices[C2] = colorBits;

        this.vertices[X3] = x2;
        this.vertices[Y3] = y2;
        this.vertices[C3] = colorBits;

        this.vertices[X4] = x2;
        this.vertices[Y4] = y;
        this.vertices[C4] = colorBits;

        batch.draw(this.texture.getTexture(), this.vertices, 0, NUM_VERTICES);
    }
}

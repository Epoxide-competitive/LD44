package org.epoxide.ld44.client.tile;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static com.badlogic.gdx.graphics.g2d.Batch.*;

public class Quad {

    protected static final int NUM_VERTICES = 20;
    protected final TextureRegion texture;
    protected final float[] vertices = new float[NUM_VERTICES];

    public Quad(TextureRegion texture) {

        this.texture = texture;

        final float u1 = texture.getU();
        final float v1 = texture.getV();
        final float u2 = texture.getU2();
        final float v2 = texture.getV2();

        this.setUV(u1, v1, u2, v2);
    }


    public Quad setUV(float minU, float minV, float maxU, float maxV) {
        this.vertices[U1] = minU;
        this.vertices[V1] = minV;

        this.vertices[U2] = minU;
        this.vertices[V2] = maxV;

        this.vertices[U3] = maxU;
        this.vertices[V3] = maxV;

        this.vertices[U4] = maxU;
        this.vertices[V4] = minV;
        return this;
    }

    public void render(SpriteBatch batch, float x, float y, float unitScale) {

        this.render(batch, x, y, unitScale, unitScale);
    }

    public void render(SpriteBatch batch, float x, float y, float width, float height) {

        this.render(batch, x, y, width, height, batch.getColor().toFloatBits());
    }

    public void render(SpriteBatch batch, float x, float y, float width, float height, float colorBits) {

        final float x2 = x + width;
        final float y2 = y + height;

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

package org.epoxide.ld44.client.tile;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static com.badlogic.gdx.graphics.g2d.Batch.*;

public class ScaledQuad extends Quad {

    private final float width;
    private final float height;
    private final float offsetX;
    private final float offsetY;

    public ScaledQuad(TextureRegion texture, float offsetX, float offsetY, float width, float height) {
        super(texture);
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.width = width;
        this.height = height;

        final float u1 = texture.getU();
        final float v1 = texture.getV();
        final float u2 = texture.getU2();
        final float v2 = texture.getV2();

        setUV(u1, v1, u2, v2);
    }

    public void render(SpriteBatch batch, float x, float y, float unitScale) {

        this.render(batch, x, y, unitScale, unitScale);
    }

    public void render(SpriteBatch batch, float x, float y, float width, float height) {

        this.render(batch, x, y, width, height, batch.getColor().toFloatBits());
    }

    public void render(SpriteBatch batch, float x, float y, float width, float height, float colorBits) {

        float offsetX2 = this.offsetX * (width);
        float offsetY2 = this.offsetY * (height);

        final float x2 = x + offsetX2 + (width * this.width);
        final float y2 = y + offsetY2 + (height * this.height);


        this.vertices[X1] = x + offsetX2;
        this.vertices[Y1] = y + offsetY2;
        this.vertices[C1] = colorBits;

        this.vertices[X2] = x + offsetX2;
        this.vertices[Y2] = y2;
        this.vertices[C2] = colorBits;

        this.vertices[X3] = x2;
        this.vertices[Y3] = y2;
        this.vertices[C3] = colorBits;

        this.vertices[X4] = x2;
        this.vertices[Y4] = y + offsetY2;
        this.vertices[C4] = colorBits;

        batch.draw(this.texture.getTexture(), this.vertices, 0, NUM_VERTICES);
    }

}
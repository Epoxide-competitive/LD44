package org.epoxide.ld44.client.tile;

import com.badlogic.gdx.graphics.g2d.*;

import static com.badlogic.gdx.graphics.g2d.Batch.*;

public class ScaledQuad extends Quad {
    
    private final float width;
    private final float height;
    
    public ScaledQuad(TextureRegion texture, float width, float height) {
        super(texture);
        this.width = width;
        this.height = height;
        
        final float u1 = texture.getU();
        final float v1 = texture.getV();
        final float u2 = texture.getU2();
        final float v2 = texture.getV2();
        
        setUV(texture.getRegionX()/texture.getTexture().getWidth(), texture.getRegionY()/texture.getTexture().getHeight(), texture.getRegionWidth()/texture.getTexture().getWidth(), texture.getRegionHeight()/texture.getTexture().getHeight());
    }
    
    public void render(SpriteBatch batch, float x, float y, float unitScale) {
        
        this.render(batch, x, y, unitScale, unitScale);
    }
    
    public void render(SpriteBatch batch, float x, float y, float width, float height) {
        
        this.render(batch, x, y, width, height, batch.getColor().toFloatBits());
    }
    
    public void render(SpriteBatch batch, float x, float y, float width, float height, float colorBits) {
        
        final float x2 = x + (width * this.width);
        final float y2 = y + (height * this.height);
        
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

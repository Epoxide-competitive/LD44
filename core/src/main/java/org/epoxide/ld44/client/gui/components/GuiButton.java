package org.epoxide.ld44.client.gui.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.epoxide.ld44.LD44;

public class GuiButton extends GuiComponent {

    private final String text;
    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private final ShapeRenderer shapeRenderer;
    private final GlyphLayout layout;

    public GuiButton(String text, int x, int y, int width, int height) {

        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.shapeRenderer = new ShapeRenderer();
        this.layout = new GlyphLayout(LD44.FONT, this.text);
    }

    @Override
    public void render(SpriteBatch batch) {
        this.shapeRenderer.setProjectionMatrix(LD44.CAMERA.combined);
        this.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        this.shapeRenderer.setColor(Color.LIGHT_GRAY);
        this.shapeRenderer.rect(this.x, this.y, this.width, this.height);
        this.shapeRenderer.end();

        batch.begin();
        final float fontX = this.x + (this.width - this.layout.width) / 2;
        final float fontY = this.y + (this.height - this.layout.height) / 2;

        LD44.FONT.draw(batch, this.layout, fontX, fontY);
        batch.end();
    }
}

package org.epoxide.ld44;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.epoxide.ld44.client.world.RenderWorld;
import org.epoxide.ld44.entity.EntityPlayer;
import org.epoxide.ld44.tile.Tiles;
import org.epoxide.ld44.world.Town;

import java.util.ArrayList;
import java.util.List;

public class LD44 extends ApplicationAdapter {
    SpriteBatch batch;
    private BitmapFont font;

    private Town town;
    private EntityPlayer entityPlayer;
    private OrthographicCamera camera;
    private float renderDelta = 0f;
    private RenderWorld renderWorld;

    @Override
    public void create() {
        Gdx.graphics.setVSync(false);

        Tiles.register();
        this.renderWorld = new RenderWorld();

        this.batch = new SpriteBatch();
        this.town = new Town();
        this.entityPlayer = new EntityPlayer();
        this.entityPlayer.setWorld(this.town);

        this.font = new BitmapFont();

        // Gets the width and height of the display
        final float w = Gdx.graphics.getWidth();
        final float h = Gdx.graphics.getHeight();

        // Creates the OrthographicCamera
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, w / h * 160, 160);
        this.camera.update();
    }

    @Override
    public void render() {
        this.renderDelta = Gdx.graphics.getDeltaTime() * 1000f;
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.camera.update();
        this.batch.setProjectionMatrix(camera.combined);
        this.renderWorld.render(this.batch, this.entityPlayer.getWorld());

        this.batch.begin();
        int lineNum = 1;

        for (String line : generateDebugInfo()) {

            this.font.draw(this.batch, line, 10, Gdx.graphics.getHeight() - (this.font.getLineHeight() * lineNum));
            lineNum++;
        }

        this.batch.end();
    }

    private final List<String> debugInfo = new ArrayList<String>();

    public List<String> generateDebugInfo() {
        debugInfo.clear();
        debugInfo.add("FPS: " + Gdx.graphics.getFramesPerSecond());
        debugInfo.add("Ram: " + (Gdx.app.getJavaHeap() / 1024f / 1024f) + " Mb");
        debugInfo.add("Delta: " + this.renderDelta);
        return debugInfo;
    }

    @Override
    public void resize(int width, int height) {
        // Gets the width and height of the display
        final float w = Gdx.graphics.getWidth();
        final float h = Gdx.graphics.getHeight();
        this.camera.setToOrtho(false, w / h * 160, 160);
        this.camera.update();
    }

    @Override
    public void dispose() {
        this.batch.dispose();
    }
}

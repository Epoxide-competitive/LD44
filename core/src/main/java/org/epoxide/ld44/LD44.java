package org.epoxide.ld44;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

import org.epoxide.ld44.client.world.RenderWorld;
import org.epoxide.ld44.entity.EntityPlayer;
import org.epoxide.ld44.input.InputHandler;
import org.epoxide.ld44.tile.Tiles;
import org.epoxide.ld44.world.Town;
import org.epoxide.ld44.world.locations.Locations;

import java.util.ArrayList;
import java.util.List;

public class LD44 extends ApplicationAdapter {

    public static final boolean EDITOR = true;
    public static int EDITOR_X;
    public static int EDITOR_Y;

    SpriteBatch batch;
    private BitmapFont font;

    private Town town;
    public static EntityPlayer ENTITYPLAYER;
    private OrthographicCamera camera;
    private float renderDelta = 0f;
    private RenderWorld renderWorld;
    private ShapeRenderer debugRenderer;
    
    private static final double STEP = 1d / 20d;
    private double prevTime;
    private double accumulator = 0;

    @Override
    public void create() {
        
        Locations location = new Locations(1, 5, 15);
        location.generate();

        Gdx.graphics.setVSync(false);

        Tiles.register();
        this.renderWorld = new RenderWorld();

        this.batch = new SpriteBatch();
        this.town = new Town();
        ENTITYPLAYER = new EntityPlayer();
        ENTITYPLAYER.setWorld(this.town);

        this.font = new BitmapFont(true);

        // Creates the OrthographicCamera
        this.camera = new OrthographicCamera();
        resetCamera();


        Gdx.input.setInputProcessor(new InputHandler());

        debugRenderer = new ShapeRenderer();
    }
    
    public void updateLogic(double delta) {

    }
    
    @Override
    public void render() {

        double currentTime = TimeUtils.millis() / 1000.0;
        double frameTime = Math.min(currentTime - prevTime, 0.25);
        
        prevTime = currentTime;
        accumulator += frameTime;
        
        while (accumulator >= STEP) {
            
            accumulator -= STEP;
            updateLogic(frameTime);
        }
        
        ENTITYPLAYER.update();

        this.renderDelta = Gdx.graphics.getDeltaTime() * 1000f;
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.camera.update();
        this.batch.setProjectionMatrix(camera.combined);
        this.renderWorld.render(this.batch, ENTITYPLAYER.getTileMap());

        Gdx.gl.glLineWidth(4);
        debugRenderer.setProjectionMatrix(this.camera.combined);
        debugRenderer.begin(ShapeRenderer.ShapeType.Line);
        debugRenderer.setColor(Color.WHITE);
        debugRenderer.line(new Vector2(Gdx.graphics.getWidth() / 2.0f - 2f, Gdx.graphics.getHeight() / 2.0f - 32.0f), new Vector2(Gdx.graphics.getWidth() / 2.0f - 2f, Gdx.graphics.getHeight() / 2.0f + 32.0f));
        debugRenderer.line(new Vector2(Gdx.graphics.getWidth() / 2.0f - 32.0f, Gdx.graphics.getHeight() / 2.0f - 2f), new Vector2(Gdx.graphics.getWidth() / 2.0f + 32.0f, Gdx.graphics.getHeight() / 2.0f - 2f));
        debugRenderer.end();
        Gdx.gl.glLineWidth(1);

        this.batch.begin();
        int lineNum = 1;

        for (String line : generateDebugInfo()) {

            this.font.draw(this.batch, line, 10, this.font.getLineHeight() * lineNum);
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
        resetCamera();
    }

    public void resetCamera() {
        final float w = Gdx.graphics.getWidth();
        final float h = Gdx.graphics.getHeight();
        this.camera.setToOrtho(true, w + 0.5f, h + 0.5f);
        this.camera.update();
    }

    @Override
    public void dispose() {
        this.batch.dispose();
    }
}

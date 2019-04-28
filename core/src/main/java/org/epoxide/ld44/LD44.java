package org.epoxide.ld44;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import org.epoxide.ld44.client.gui.Gui;
import org.epoxide.ld44.client.gui.GuiBountyBoard;
import org.epoxide.ld44.client.world.RenderWorld;
import org.epoxide.ld44.entity.EntityPlayer;
import org.epoxide.ld44.input.InputHandler;
import org.epoxide.ld44.tile.Tiles;
import org.epoxide.ld44.utilities.Debug;
import org.epoxide.ld44.world.Town;

public class LD44 extends ApplicationAdapter {

    public static final boolean EDITOR = true;
    public static int EDITOR_X;
    public static int EDITOR_Y;

    public static BitmapFont FONT;
    public static EntityPlayer ENTITYPLAYER;

    public static double DELTA = 0f;

    public static Gui CURRENT_GUI;

    private Town town;
    public static OrthographicCamera CAMERA;
    private SpriteBatch batch;
    private RenderWorld renderWorld;

    private Debug debug;

    private static final double STEP = 1d / 20d;
    private double prevTime;
    private double accumulator = 0;

    @Override
    public void create() {

        Gdx.graphics.setVSync(false);

        FONT = new BitmapFont(Gdx.files.internal("assets/ld44/textures/fonts/pixel_operator.fnt"), true);

        CURRENT_GUI = new GuiBountyBoard();

        Tiles.register();
        this.renderWorld = new RenderWorld();
        this.debug = new Debug();
        this.batch = new SpriteBatch();
        this.town = new Town();
        ENTITYPLAYER = new EntityPlayer();
        ENTITYPLAYER.setWorld(this.town);

        CAMERA = new OrthographicCamera();
        resetCamera();

        Gdx.input.setInputProcessor(new InputHandler());
    }

    public void updateLogic(double delta) {

        ENTITYPLAYER.update(delta);
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
            DELTA = frameTime;
        }

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        CAMERA.update();
        this.batch.setProjectionMatrix(CAMERA.combined);
        this.renderWorld.render(this.batch, ENTITYPLAYER.getTileMap());

        this.debug.drawCrosshair();
        this.debug.drawDebugStats(this.batch);

        if (CURRENT_GUI != null)
            CURRENT_GUI.render(this.batch);
    }


    @Override
    public void resize(int width, int height) {
        resetCamera();
    }

    public void resetCamera() {
        final float w = Gdx.graphics.getWidth();
        final float h = Gdx.graphics.getHeight();
        CAMERA.setToOrtho(true, w + 0.5f, h + 0.5f);
        CAMERA.update();
    }

    @Override
    public void dispose() {
        this.batch.dispose();
    }
}

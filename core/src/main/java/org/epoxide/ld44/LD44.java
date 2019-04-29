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
import org.epoxide.ld44.entity.EntityRat;
import org.epoxide.ld44.input.InputHandler;
import org.epoxide.ld44.tile.Tiles;
import org.epoxide.ld44.utilities.Debug;
import org.epoxide.ld44.world.Town;
import org.epoxide.ld44.world.World;

public class LD44 extends ApplicationAdapter {

    private static LD44 instance;
    
    public static final boolean EDITOR = true;
    public static int EDITOR_X;
    public static int EDITOR_Y;
    public static double DELTA = 0f;
    public static OrthographicCamera CAMERA;
    
    private BitmapFont font;
    private EntityPlayer clientPlayer;
    private Gui currentGUI;
    private World currentWorld;
    private SpriteBatch batch;
    private RenderWorld renderWorld;

    private Debug debug;

    private static final double STEP = 1d / 20d;
    private double prevTime;
    private double accumulator = 0;

    @Override
    public void create() {

        instance = this;
        Gdx.graphics.setVSync(false);

        font = new BitmapFont(Gdx.files.internal("assets/ld44/textures/fonts/pixel_operator.fnt"), true);

        currentGUI = new GuiBountyBoard();

        Tiles.register();
        this.renderWorld = new RenderWorld();
        this.debug = new Debug();
        this.batch = new SpriteBatch();
        clientPlayer = new EntityPlayer();
        this.currentWorld = new Town();
        clientPlayer.setWorld(this.currentWorld);
        this.currentWorld.addEntity(clientPlayer);

        for (int i = 0; i < 5; i++) {
            
            EntityRat rat = new EntityRat();
            rat.setWorld(this.currentWorld);
            this.currentWorld.addEntity(rat);
        }
        
        CAMERA = new OrthographicCamera();
        resetCamera();

        Gdx.input.setInputProcessor(new InputHandler());
    }

    public void updateLogic(double delta) {

        this.currentWorld.update(delta);
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
        this.renderWorld.render(this.batch, clientPlayer.getTileMap());

        this.debug.drawCrosshair();
        this.debug.drawDebugStats(this.batch);

        if (currentGUI != null)
            currentGUI.render(this.batch);
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

    public static LD44 getInstance() {
        
        return LD44.instance;
    }
    
    public BitmapFont getFont () {
        
        return font;
    }

    public EntityPlayer getClientPlayer () {
        
        return clientPlayer;
    }

    public Gui getCurrentGUI () {
        
        return currentGUI;
    }
    
    
    public void setCurrentGUI(Gui currentGUI) {
        this.currentGUI = currentGUI;
    }
    
    public World getCurrentWorld () {
        
        return currentWorld;
    }

    public SpriteBatch getBatch () {
        
        return batch;
    }

    public RenderWorld getRenderWorld () {
        
        return renderWorld;
    }

    public Debug getDebug () {
        
        return debug;
    }

    public double getPrevTime () {
        
        return prevTime;
    }

    public double getAccumulator () {
        
        return accumulator;
    }
}

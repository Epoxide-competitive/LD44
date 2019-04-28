package org.epoxide.ld44.utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import org.epoxide.ld44.LD44;

import java.util.ArrayList;
import java.util.List;

public class Debug {
    private static final List<String> debugInfo = new ArrayList<String>();
    private ShapeRenderer debugRenderer;

    public Debug() {
        this.debugRenderer = new ShapeRenderer();
    }

    public static List<String> generateDebugInfo() {
        debugInfo.clear();
        debugInfo.add("FPS: " + Gdx.graphics.getFramesPerSecond());
        debugInfo.add(String.format("Ram: %.4f Mb", (Gdx.app.getJavaHeap() / 1024f / 1024f)));
        debugInfo.add(String.format("Delta: %.4f", LD44.DELTA * 1000f));
        return debugInfo;
    }

    public void drawCrosshair(OrthographicCamera camera) {
        Gdx.gl.glLineWidth(4);
        debugRenderer.setProjectionMatrix(camera.combined);
        debugRenderer.begin(ShapeRenderer.ShapeType.Line);
        debugRenderer.setColor(Color.WHITE);
        debugRenderer.line(new Vector2(Gdx.graphics.getWidth() / 2.0f - 2f, Gdx.graphics.getHeight() / 2.0f - 32.0f), new Vector2(Gdx.graphics.getWidth() / 2.0f - 2f, Gdx.graphics.getHeight() / 2.0f + 32.0f));
        debugRenderer.line(new Vector2(Gdx.graphics.getWidth() / 2.0f - 32.0f, Gdx.graphics.getHeight() / 2.0f - 2f), new Vector2(Gdx.graphics.getWidth() / 2.0f + 32.0f, Gdx.graphics.getHeight() / 2.0f - 2f));
        debugRenderer.end();
        Gdx.gl.glLineWidth(1);
    }

    public void drawDebugStats(SpriteBatch batch) {
        batch.begin();
        int lineNum = 1;
        for (String line : generateDebugInfo()) {

            LD44.FONT.draw(batch, line, 10, LD44.FONT.getLineHeight() * lineNum);
            lineNum++;
        }
        batch.end();
    }
}

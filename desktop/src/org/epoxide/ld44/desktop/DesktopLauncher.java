package org.epoxide.ld44.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.epoxide.ld44.LD44;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.backgroundFPS = 240;
        config.foregroundFPS = 240;
		new LwjglApplication(new LD44(), config);
	}
}

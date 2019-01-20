package com.craig.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.craig.game.CraigGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new CraigGame(true), config);

		config.width = 1920;
		config.height = 1080;
		config.fullscreen = true;
		config.foregroundFPS = 60;
	}
}

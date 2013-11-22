package com.aehooo.tdsqn;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import com.aehooo.tdsqn.resources.ImageAlligator3000;
import com.aehooo.tdsqn.resources.TextureName;
import com.aehooo.tdsqn.scenes.LevelScene;

public class MainActivity extends SimpleBaseGameActivity {

	private Camera camera;
	private static final int CAMERA_WIDTH = 800;
	private static final int CAMERA_HEIGHT = 480;

	@Override
	public EngineOptions onCreateEngineOptions() {
		this.camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		EngineOptions engineOptions = new EngineOptions(true,
				ScreenOrientation.LANDSCAPE_FIXED, new FillResolutionPolicy(),
				this.camera);
		return engineOptions;
	}

	@Override
	protected void onCreateResources() {
		// TODO Auto-generated method stub
		ImageAlligator3000.initialize(this);
	}

	@Override
	protected Scene onCreateScene() {
		LevelScene scene = new LevelScene(TextureName.MAPA1_BG);

		return scene;
	}

}

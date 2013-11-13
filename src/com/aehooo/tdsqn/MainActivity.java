package com.aehooo.tdsqn;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import android.util.Log;

import com.aehooo.tdsqn.resources.ImageAlligator3000;
import com.aehooo.tdsqn.resources.TextureName;
import com.aehooo.tdsqn.scenes.LevelScene;

public class MainActivity extends SimpleBaseGameActivity {

	private Camera camera;
	private static final int CAMERA_WIDTH = 800;
	private static final int CAMERA_HEIGHT = 480;
	
	@Override
	public EngineOptions onCreateEngineOptions() {
		camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
	    EngineOptions engineOptions = new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, 
	    new FillResolutionPolicy(), camera);
	    return engineOptions;
	}

	@Override
	protected void onCreateResources() {
		// TODO Auto-generated method stub
		ImageAlligator3000.initialize(this);
	}

	@Override
	protected Scene onCreateScene() {
		Sprite bgmapa1 = new Sprite(0, 0,
				ImageAlligator3000.getTexture(TextureName.MAPA1_BG),
				ImageAlligator3000.getVertexBufferObjectManager()) {
			
			float antx = 0;
			float anty = 0;
			
			float posx = 0;
			float posy = 0;
			
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				if (pSceneTouchEvent.isActionDown()) {
					Log.i("AEHO.MainActivity", "ActionDown");
					antx = pSceneTouchEvent.getX();
					anty = pSceneTouchEvent.getY();
					
					posx = this.getX();
					posy = this.getY();
				}
				else if(pSceneTouchEvent.isActionUp()) {
					Log.i("AEHO.MainActivity", "ActionUp");
				}
				else if(pSceneTouchEvent.isActionMove()) {
					Log.i("AEHO.MainActivity", "ActionMove");
					float newx = posx + (pSceneTouchEvent.getX() - antx);
					float newy = posy + (pSceneTouchEvent.getY() - anty);
					
					if (newx > 0)
						newx = 0;
					if (newy > 0)
						newy = 0;
					
					this.setPosition(newx, newy);
				}
				
				return true;
			}
		};
		
		LevelScene scene = new LevelScene(bgmapa1);
		
		
		
	    return scene;
	}


}

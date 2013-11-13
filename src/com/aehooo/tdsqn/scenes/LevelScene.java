package com.aehooo.tdsqn.scenes;

import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;

import android.util.Log;

import com.aehooo.tdsqn.resources.ImageAlligator3000;
import com.aehooo.tdsqn.resources.TextureName;

public class LevelScene extends Scene {
	
	public LevelScene(String bgname) {
		super();
		
		Sprite bg = new Sprite(0, 0,
				ImageAlligator3000.getTexture(bgname),
				ImageAlligator3000.getVertexBufferObjectManager()) {
			
			float antx = 0;
			float anty = 0;
			
			float posx = 0;
			float posy = 0;
			
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				if (pSceneTouchEvent.isActionDown()) {
					Log.i("AEHO.LevelScene", "ActionDown");
					antx = pSceneTouchEvent.getX();
					anty = pSceneTouchEvent.getY();
					
					posx = this.getX();
					posy = this.getY();
				}
				else if(pSceneTouchEvent.isActionUp()) {
					Log.i("AEHO.LevelScene", "ActionUp");
				}
				else if(pSceneTouchEvent.isActionMove()) {
					Log.i("AEHO.LevelScene", "ActionMove");
					float newx = posx + (pSceneTouchEvent.getX() - antx);
					float newy = posy + (pSceneTouchEvent.getY() - anty);
					
					if (newx > 0)
						newx = 0;
					if (newy > 0)
						newy = 0;
					
					if (newx - 800 < -1400) {
						newx = -600;
					}
					if (newy - 480 < -960) {
						newy = -480;
					}
					
					this.setPosition(newx, newy);
				}
				
				return true;
			}
		};
		
		this.attachChild(bg);
		registerTouchArea(bg);
		this.setTouchAreaBindingOnActionDownEnabled(true);
		this.setTouchAreaBindingOnActionMoveEnabled(true);
	}
}

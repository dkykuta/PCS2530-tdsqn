package com.aehooo.tdsqn.scenes;

import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.sprite.Sprite;

public class LevelScene extends Scene {
	
	public LevelScene(Sprite bg) {
		super();
		this.attachChild(bg);
		registerTouchArea(bg);
		this.setTouchAreaBindingOnActionDownEnabled(true);
		this.setTouchAreaBindingOnActionMoveEnabled(true);
	}
}

package com.aehooo.tdsqn.scenes;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.sprite.Sprite;

public class LevelScene extends Scene {
	
	public LevelScene(Sprite bg) {
		super();
		this.setBackground(new SpriteBackground(bg));
	}
}

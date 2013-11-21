package com.aehooo.tdsqn.scenes;

import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;

import com.aehooo.tdsqn.entity.unit.Zombie;
import com.aehooo.tdsqn.resources.ImageAlligator3000;
import com.aehooo.tdsqn.resources.TextureName;
import com.aehooo.tdsqn.scenes.composition.LevelBackground;
import com.aehooo.tdsqn.utils.Vector2D;

public class LevelScene extends Scene {

	private Sprite bg;
	private Sprite barraLateral;

	public LevelScene(final TextureName bgname) {
		super();

		this.setTouchAreaBindingOnActionDownEnabled(true);
		this.setTouchAreaBindingOnActionMoveEnabled(true);

		this.bg = new LevelBackground(-600, -480,
				ImageAlligator3000.getTexture(bgname),
				ImageAlligator3000.getVertexBufferObjectManager());

		this.bg.registerEntityModifier(new MoveModifier(3, -600, 100, -480, 0));

		this.attachChild(this.bg);

		this.barraLateral = new Sprite(0, 0,
				ImageAlligator3000.getTexture(TextureName.BARRA_LATERAL),
				ImageAlligator3000.getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
					final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				return true;
			}
		};
		this.attachChild(this.barraLateral);

		/*
		 * AnimatedSprite zz = new AnimatedSprite(200, 200,
		 * ImageAlligator3000.getTiledTexture(TextureName.ZOMBIE),
		 * ImageAlligator3000.getVertexBufferObjectManager()); zz.animate(100);
		 */

		Zombie zz = new Zombie(new Vector2D(500, 500));
		zz.animateLinha(0);

		this.bg.attachChild(zz.getSprite());
		this.registerTouchArea(zz.getSprite());

		// this.bg.attachChild(zz);

		this.registerTouchArea(this.barraLateral);
		this.registerTouchArea(this.bg);
	}
}

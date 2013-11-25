package com.aehooo.tdsqn.scenes;

import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;

import android.util.Log;

import com.aehooo.tdsqn.entity.group.Group;
import com.aehooo.tdsqn.entity.tower.OneTower;
import com.aehooo.tdsqn.entity.unit.Zombie;
import com.aehooo.tdsqn.manager.UpdateManager;
import com.aehooo.tdsqn.path.Path;
import com.aehooo.tdsqn.resources.ImageAlligator3000;
import com.aehooo.tdsqn.resources.TextureName;
import com.aehooo.tdsqn.scenes.composition.LevelBackground;
import com.aehooo.tdsqn.utils.Vector2D;

public class LevelScene extends Scene {

	private Sprite bg;
	private Sprite barraLateral;
	private Path path;
	private UpdateManager updateManager;

	public LevelScene(final TextureName bgname) {
		super();

		this.initializeBasicScreen(bgname);

		this.path = new Path();

		this.path.addPonto(0, 60);
		this.path.addPonto(500, 60);
		this.path.addPonto(500, 800);
		this.path.addPonto(1000, 800);
		this.path.addPonto(1000, 600);
		this.path.addPonto(800, 600);
		this.path.addPonto(800, 400);
		this.path.addPonto(1340, 400);

		this.updateManager = new UpdateManager();
		this.createTestUnits();

		this.registerUpdateHandler(this.updateManager);

		//
		// SETTING TOUCH
		//

		this.setTouchAreaBindingOnActionDownEnabled(true);
		this.setTouchAreaBindingOnActionMoveEnabled(true);
		this.registerTouchArea(this.barraLateral);
		this.registerTouchArea(this.bg);
	}

	private void createTestUnits() {
		Zombie zz;
		try {
			zz = new Zombie(this, new Vector2D(500, 500));
		} catch (Exception e) {
			Log.e("LevelScene", "Zombie com erro", e);
			return;
		}

		Group g;
		try {
			g = new Group(this, this.path);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		this.updateManager.addUpdatable(g);

		g.addUnit(zz);

		this.bg.attachChild(g.getSprite());
		g.getSprite().attachChild(zz.getSprite());

		OneTower t;
		try {
			t = new OneTower(this, 200, 200);
		} catch (Exception e) {
			Log.i("LevelScene", "OneTower exception", e);
			return;
		}
		
		this.bg.attachChild(t.getSprite());
	}

	private void initializeBasicScreen(final TextureName bgname) {
		this.bg = new LevelBackground(-600, -480, ImageAlligator3000
				.getTexture(bgname));

		// Movimentação inicial: efeito
		this.bg.registerEntityModifier(new SequenceEntityModifier(
				new MoveModifier(2, -600, -600, 0, -480), new MoveModifier(2,
						-600, 100, -480, 0)));

		this.attachChild(this.bg);

		this.barraLateral = new Sprite(0, 0, ImageAlligator3000
				.getTexture(TextureName.BARRA_LATERAL), ImageAlligator3000
				.getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
					final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				return true;
			}
		};
		this.attachChild(this.barraLateral);
	}
}

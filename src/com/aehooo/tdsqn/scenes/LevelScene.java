package com.aehooo.tdsqn.scenes;

import java.util.List;

import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;

import android.util.Log;

import com.aehooo.tdsqn.entity.group.Group;
import com.aehooo.tdsqn.entity.impl.Action;
import com.aehooo.tdsqn.entity.impl.ListOfEntity;
import com.aehooo.tdsqn.entity.tower.BasicTower;
import com.aehooo.tdsqn.entity.tower.OneTower;
import com.aehooo.tdsqn.entity.unit.Zombie;
import com.aehooo.tdsqn.manager.LevelManager;
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
	private ListOfEntity<Action> actions;
	private ListOfEntity<Group> groups;
	private ListOfEntity<BasicTower> towers;

	public LevelScene(final TextureName bgname) {
		super();
		LevelManager.changeLevelScene(this);

		this.updateManager = new UpdateManager();

		LevelManager.setUpdateManager(this.updateManager);

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

		this.actions = new ListOfEntity<Action>();

		this.groups = new ListOfEntity<Group>();

		this.towers = new ListOfEntity<BasicTower>();

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

	public List<Group> getGroups() {
		return this.groups.getList();
	}

	public void addGroup(final Group g) {
		this.groups.addEntity(g);
	}

	public List<Action> getActions() {
		return this.actions.getList();
	}

	public void addAction(final Action action) {
		this.actions.addEntity(action);
	}

	public Sprite getGameWindow() {
		return this.bg;
	}

	public Sprite getSidebar() {
		return this.barraLateral;
	}

	public UpdateManager getUpdateManager() {
		return this.updateManager;
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
		this.groups.addEntity(g);

		g.addUnit(zz);

		g.attachChild(zz);

		OneTower t;
		try {
			t = new OneTower(this, 200, 200);
		} catch (Exception e) {
			Log.i("LevelScene", "OneTower exception", e);
			return;
		}
		this.towers.addEntity(t);
		// this.bg.attachChild(t.getSprite());
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

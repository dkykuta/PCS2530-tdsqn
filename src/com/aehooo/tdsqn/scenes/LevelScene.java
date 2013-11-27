package com.aehooo.tdsqn.scenes;

import java.util.List;

import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;

import android.util.Log;

import com.aehooo.tdsqn.entity.IUpdatable;
import com.aehooo.tdsqn.entity.action.Action;
import com.aehooo.tdsqn.entity.button.LostTheGameBanner;
import com.aehooo.tdsqn.entity.button.UnitButton;
import com.aehooo.tdsqn.entity.group.Group;
import com.aehooo.tdsqn.entity.impl.ListOfEntity;
import com.aehooo.tdsqn.entity.tower.BasicTower;
import com.aehooo.tdsqn.entity.tower.TowerOne;
import com.aehooo.tdsqn.entity.tower.TowerTwo;
import com.aehooo.tdsqn.entity.unit.Healer;
import com.aehooo.tdsqn.entity.unit.Zombie;
import com.aehooo.tdsqn.manager.LevelManager;
import com.aehooo.tdsqn.manager.UpdateManager;
import com.aehooo.tdsqn.path.Path;
import com.aehooo.tdsqn.resources.ImageAlligator3000;
import com.aehooo.tdsqn.resources.TextureName;
import com.aehooo.tdsqn.scenes.composition.LevelBackground;

public class LevelScene extends Scene implements IUpdatable {

	private static final int MAX_GROUPS = 3;

	private Sprite bg;
	private Sprite barraLateral;
	private Path path;
	private UpdateManager updateManager;
	private ListOfEntity<Action> actions;
	private ListOfEntity<Group> groups;
	private ListOfEntity<BasicTower> towers;

	private int nGroups;

	public LevelScene(final TextureName bgname) {
		super();
		LevelManager.changeLevelScene(this);

		this.updateManager = new UpdateManager();
		this.nGroups = 0;

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

		this.updateManager.addUpdatable(this);

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

	public List<BasicTower> getTowers() {
		return this.towers.getList();
	}

	public void addGroup(final Group g) {
		this.nGroups++;
		this.groups.addEntity(g);
	}

	public Group getUninitializedGroup() throws Exception {
		Group g = this.groups.getLast();
		if (((g == null) || g.isInitialized()) && (this.nGroups < MAX_GROUPS)) {
			g = new Group(this, this.path);
			this.addGroup(g);
		}
		return g;
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

		try {
			BasicTower t = new TowerOne(this, 200, 200);
			this.towers.addEntity(t);
			t = new TowerTwo(this, 300, 200);
			this.towers.addEntity(t);
		} catch (Exception e) {
			Log.i("LevelScene", "OneTower exception", e);
			return;
		}

		try {
			UnitButton b = new UnitButton(this, Zombie.class, 10, 10);
			LevelManager.attachOnSideBar(b);

			b = new UnitButton(this, Healer.class, 10, 100);
			b.getSprite().setGreen(0.2f);
			LevelManager.attachOnSideBar(b);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initializeBasicScreen(final TextureName bgname) {
		this.bg = new LevelBackground(-600, 0, ImageAlligator3000
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

	@Override
	public void onFrameUpdate() {
		if ((this.nGroups == MAX_GROUPS) && this.groups.getList().isEmpty()
				&& !LevelManager.isWinner()) {
			try {
				LostTheGameBanner lost = new LostTheGameBanner(this, 200, 140);
				LevelManager.attachOnScreen((Sprite) lost.getSprite());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void onCheckDeadChildren() {
		// TODO Auto-generated method stub

	}
}

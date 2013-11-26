package com.aehooo.tdsqn.entity.impl;

import org.andengine.entity.scene.Scene;

import com.aehooo.tdsqn.annotations.TextureInfo;
import com.aehooo.tdsqn.entity.IUpdatable;
import com.aehooo.tdsqn.manager.LevelManager;
import com.aehooo.tdsqn.resources.TextureName;
import com.aehooo.tdsqn.utils.Vector2D;

@TextureInfo(name = TextureName.TIRO, linhas = { "" })
public class Action extends GameEntity implements IUpdatable {
	private double vel;
	private GameEntity origin;
	private GameEntity target;

	public Action(final Scene fScene, final GameEntity origin,
			final GameEntity target) throws Exception {
		super(fScene, origin.getCenter());
		this.origin = origin;
		this.target = target;
		this.vel = 5;

		LevelManager.getCurrentLevelScene().addAction(this);
	}

	public void walk(final double dRemain) {
		Vector2D direction = this.target.getCenter().sub(this.getPos())
				.normalize();
		Vector2D newpos = this.getPos().add(direction.mul(dRemain));

		if (this.target.getCenter().isBetween(this.getPos(), newpos)) {
			this.shouldDie();
		}
		else {
			this.setPos(newpos);
		}
	}

	@Override
	public void onFrameUpdate() {
		this.walk(this.vel);
	}

	public void assembly() {

	}

	public void damage(final int dano, final int mpCost) {

	}

	public void slow(final double pctg, final int framesDuracao,
			final int mpCost) {

	}

}

package com.aehooo.tdsqn.entity.action;

import java.util.ArrayList;
import java.util.List;

import org.andengine.entity.scene.Scene;

import com.aehooo.tdsqn.annotations.TextureInfo;
import com.aehooo.tdsqn.entity.ILiveEntity;
import com.aehooo.tdsqn.entity.ITargetEntity;
import com.aehooo.tdsqn.entity.IUpdatable;
import com.aehooo.tdsqn.entity.impl.GameEntity;
import com.aehooo.tdsqn.manager.LevelManager;
import com.aehooo.tdsqn.resources.TextureName;
import com.aehooo.tdsqn.utils.Vector2D;

@TextureInfo(name = TextureName.TIRO, linhas = { "" })
public class Action extends GameEntity implements IUpdatable {
	private double vel;
	private ILiveEntity origin;
	private ITargetEntity target;

	private List<ActionPart> parts;

	public Action(final Scene fScene, final ILiveEntity origin,
			final ITargetEntity target) throws Exception {
		super(fScene, origin.getCenter());
		this.origin = origin;
		this.target = target;
		this.vel = 5;

		this.parts = new ArrayList<ActionPart>();

		LevelManager.getCurrentLevelScene().addAction(this);
	}

	public void walk(final double dRemain) {
		Vector2D direction = this.target.getCenter().sub(this.getPos())
				.normalize();
		Vector2D newpos = this.getPos().add(direction.mul(dRemain));

		if (this.target.getCenter().isBetween(this.getPos(), newpos)) {
			this.execute();
			this.shouldDie();
		} else {
			this.setPos(newpos);
		}
	}

	@Override
	public void onFrameUpdate() {
		this.walk(this.vel);
	}

	public void assembly() {
		if (this.parts.isEmpty()) {
			this.shouldDie();
		}
	}

	public void execute() {
		for (ActionPart part : this.parts) {
			part.execute();
		}
	}

	public void damage(final int dano, final int mpCost) {

		this.parts.add(new ActionPart(this.origin, this.target) {

			@Override
			public void execute() {
				Action.this.target.takeDamage(dano);
			}
		});

	}

	public void slow(final double pctg, final int framesDuracao,
			final int mpCost) {
		this.parts.add(new ActionPart(this.origin, this.target) {

			@Override
			public void execute() {
				Action.this.target
						.slow(Action.this.origin, pctg, framesDuracao);
			}
		});
	}
}

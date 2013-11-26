package com.aehooo.tdsqn.entity.group;

import java.util.ArrayList;
import java.util.List;

import org.andengine.entity.scene.Scene;
import org.andengine.input.touch.TouchEvent;

import com.aehooo.tdsqn.annotations.TextureInfo;
import com.aehooo.tdsqn.entity.IUpdatable;
import com.aehooo.tdsqn.entity.impl.GameEntity;
import com.aehooo.tdsqn.entity.unit.BasicUnit;
import com.aehooo.tdsqn.path.Path;
import com.aehooo.tdsqn.resources.TextureName;
import com.aehooo.tdsqn.utils.Vector2D;

@TextureInfo(name = TextureName.GROUP, linhas = { "normal" })
public class Group extends GameEntity implements IUpdatable {

	private Vector2D direction;
	private double vel;
	private Path path;
	private boolean walking;
	private boolean initialized;
	private int nextPointIdx;

	private List<BasicUnit> unidades;

	public Group(final Scene fScene, final Path path) throws Exception {
		super(fScene, path.getPoint(0));
		this.direction = path.getDir(0, 1);
		this.nextPointIdx = 1;
		this.vel = 2.0;
		this.path = path;
		this.walking = false;
		this.initialized = false;
		this.unidades = new ArrayList<BasicUnit>();
	}

	@Override
	public void animateLinha(final int linha) {
		long[] duracoes = new long[4];
		for (int i = 0; i < 4; i++) {
			duracoes[i] = 400;
		}
		int[] frames = { 0, 1, 2, 1 };
		this.animate(duracoes, frames);
	}

	@Override
	public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
			final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
		if (pSceneTouchEvent.isActionDown()) {
			this.walking = !this.walking;
			this.initialized = true;
			return true;
		}
		return false;
	}

	@Override
	public void setPos(final Vector2D v) {
		Vector2D diff = v.sub(this.getPos());
		super.setPos(v);
		if (this.unidades != null) {
			for (BasicUnit u : this.unidades) {
				u.selectRotation(diff);
			}
		}
	}

	public void walk(final double dRemain) {
		if (this.direction == null) {
			this.walking = false;
			return;
		}
		Vector2D newpos = this.getPos().add(this.direction.mul(dRemain));
		Vector2D nextPoint = this.path.getPoint(this.nextPointIdx);

		if (this.path.isBeyond(this.nextPointIdx, newpos)) {
			double dist = this.getPos().distTo(nextPoint);

			double remaining = dRemain - dist;

			this.setPos(nextPoint);
			this.nextPointIdx++;
			if (this.nextPointIdx >= this.path.size()) {
				this.direction = null;
				this.shouldDie();
				return;
			}
			this.direction = this.path.getDir(this.nextPointIdx - 1,
					this.nextPointIdx);

			this.walk(remaining);
		}
		else {
			this.setPos(newpos);
		}
	}

	public void attachChild(final GameEntity e) {
		if (e != null) {
			this.getSprite().attachChild(e.getSprite());
		}
	}

	public void addUnit(final BasicUnit u) {
		if (this.initialized) {
			return;
		}

		u.setPos(new Vector2D(0, 0));

		this.unidades.add(u);

	}

	public List<BasicUnit> getUnits() {
		return this.unidades;
	}

	@Override
	public void onFrameUpdate() {
		if (this.walking) {
			this.walk(this.vel);
		}
	}

}

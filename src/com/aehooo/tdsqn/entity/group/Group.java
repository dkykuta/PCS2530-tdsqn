package com.aehooo.tdsqn.entity.group;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.andengine.entity.scene.Scene;
import org.andengine.input.touch.TouchEvent;

import com.aehooo.tdsqn.annotations.TextureInfo;
import com.aehooo.tdsqn.entity.GameEntity;
import com.aehooo.tdsqn.entity.IUpdatable;
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

	private Map<String, List<BasicUnit>> agrupamentos;

	public Group(final Scene fScene, final Path path) throws Exception {
		super(fScene, path.getPoint(0));
		this.direction = path.getDir(0, 1);
		this.nextPointIdx = 1;
		this.vel = 1.0;
		this.path = path;
		this.walking = false;
		this.initialized = false;
		this.agrupamentos = new HashMap<String, List<BasicUnit>>();
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
		if (this.agrupamentos != null) {
			for (List<BasicUnit> list : this.agrupamentos.values()) {
				for (BasicUnit u : list) {
					u.selectRotation(diff);
				}
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

	public void addUnit(final BasicUnit u) {
		if (this.initialized) {
			return;
		}

		u.setPos(new Vector2D(0, 0));

		List<BasicUnit> list = this.agrupamentos.get(u.getName());
		if (list == null) {
			list = new ArrayList<BasicUnit>();
			this.agrupamentos.put(u.getName(), list);
		}
		list.add(u);

	}

	@Override
	public void onFrameUpdate() {
		if (this.walking) {
			this.walk(this.vel);
		}
	}

}

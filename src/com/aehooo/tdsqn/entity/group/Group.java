package com.aehooo.tdsqn.entity.group;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.andengine.entity.scene.Scene;
import org.andengine.input.touch.TouchEvent;

import com.aehooo.tdsqn.annotations.TextureInfo;
import com.aehooo.tdsqn.entity.ILiveEntity;
import com.aehooo.tdsqn.entity.ITargetEntity;
import com.aehooo.tdsqn.entity.IUpdatable;
import com.aehooo.tdsqn.entity.impl.AttrModifier;
import com.aehooo.tdsqn.entity.impl.GameEntity;
import com.aehooo.tdsqn.entity.unit.BasicUnit;
import com.aehooo.tdsqn.path.Path;
import com.aehooo.tdsqn.resources.TextureName;
import com.aehooo.tdsqn.utils.Vector2D;

@TextureInfo(name = TextureName.GROUP, linhas = { "normal" })
public class Group extends GameEntity implements IUpdatable, ITargetEntity {

	private Vector2D direction;
	private double vel;
	private Path path;
	private boolean walking;
	private boolean initialized;
	private int nextPointIdx;
	private Map<String, AttrModifier> modificadores;

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
		this.modificadores = new HashMap<String, AttrModifier>();

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
		} else {
			this.setPos(newpos);
		}
	}

	public void addUnit(final BasicUnit u) {
		int n = this.unidades.size();
		if (this.initialized || (u == null) || (n >= 4)) {
			return;
		}

		u.setPos(((n / 2) * 30), ((n % 2) * 30));

		this.unidades.add(u);

		this.getSprite().attachChild(u.getSprite());

	}

	public List<BasicUnit> getUnits() {
		return this.unidades;
	}

	public double calculateVel() {
		double vel = this.vel;

		return vel;
	}

	@Override
	public void onFrameUpdate() {
		List<BasicUnit> mortos = new ArrayList<BasicUnit>();
		if (this.walking) {
			this.walk(this.calculateVel());
		}
		for (BasicUnit u : this.unidades) {
			u.onFrameUpdate();
			if (u.isDead()) {
				mortos.add(u);
			}
		}

		this.unidades.removeAll(mortos);
	}

	@Override
	public boolean takeDamage(final int amount) {
		for (BasicUnit u : this.unidades) {
			u.getSprite().detachSelf();
		}
		this.unidades.clear();
		return false;
	}

	@Override
	public boolean receiveHeal(final int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean slow(final ILiveEntity origin, final double pctg,
			final int framesDur) {
		AttrModifier attrModifier = this.modificadores.get("vel");
		if (attrModifier == null) {
			attrModifier = new AttrModifier();
			this.modificadores.put("vel", attrModifier);
		}
		attrModifier.put(origin, pctg, framesDur);
		return false;
	}
}

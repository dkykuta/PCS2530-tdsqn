package com.aehooo.tdsqn.entity.barragem;

import org.andengine.entity.scene.Scene;

import com.aehooo.tdsqn.annotations.APS;
import com.aehooo.tdsqn.entity.ILiveEntity;
import com.aehooo.tdsqn.entity.impl.ActionEntity;
import com.aehooo.tdsqn.utils.Vector2D;

@APS(60)
public abstract class BasicBarragem extends ActionEntity implements ILiveEntity {
	private int hp;
	private int maxHp;

	public BasicBarragem(final Scene fScene, final Vector2D pos)
			throws Exception {
		super(fScene, pos);

		this.initializeConstants();
	}

	public BasicBarragem(final Scene fScene, final float pX, final float pY)
			throws Exception {
		this(fScene, new Vector2D(pX, pY));
	}

	private void initializeConstants() {
		this.hp = 10;
		this.maxHp = this.hp;
	}

	@Override
	public int getHP() {
		return this.hp;
	}

	@Override
	public double getPorcentagemHP() {
		if (this.maxHp == 0) {
			return 0;
		}

		return ((double) this.hp) / (this.maxHp);
	}

	@Override
	public boolean takeDamage(final int amount) {
		return false;
	}

	@Override
	public boolean receiveHeal(final int amount) {
		return false;
	}

	@Override
	public boolean slow(final ILiveEntity origin, final double pctg,
			final int framesDur) {
		return false;
	}

	/*
	 * Meu
	 */
	public abstract String getName();

	public void selectRotation(final Vector2D diff) {

	}
}

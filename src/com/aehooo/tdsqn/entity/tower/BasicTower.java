package com.aehooo.tdsqn.entity.tower;

import org.andengine.entity.scene.Scene;

import com.aehooo.tdsqn.entity.ILiveEntity;
import com.aehooo.tdsqn.entity.impl.ActionEntity;
import com.aehooo.tdsqn.utils.Vector2D;

public abstract class BasicTower extends ActionEntity implements ILiveEntity {
	private int hp;
	private int maxHp;

	public static final int NORMAL_BAIXO = 0;
	public static final int NORMAL_DIREITA = 1;
	public static final int NORMAL_CIMA = 2;
	public static final int NORMAL_ESQUERDA = 3;

	public BasicTower(final Scene fScene, final Vector2D pos) throws Exception {
		super(fScene, pos);

		this.initializeConstants();
	}

	public BasicTower(final Scene fScene, final float pX, final float pY)
			throws Exception {
		this(fScene, new Vector2D(pX, pY));
	}

	private void initializeConstants() {
		this.hp = 200;
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
		if (this.isDead()) {
			return false;
		}
		this.hp -= amount;
		return true;
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

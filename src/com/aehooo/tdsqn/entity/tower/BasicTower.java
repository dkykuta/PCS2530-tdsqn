package com.aehooo.tdsqn.entity.tower;

import org.andengine.entity.scene.Scene;

import com.aehooo.tdsqn.entity.ActionEntity;
import com.aehooo.tdsqn.entity.ICasterEntity;
import com.aehooo.tdsqn.entity.ILiveEntity;
import com.aehooo.tdsqn.utils.Vector2D;

public abstract class BasicTower extends ActionEntity implements ILiveEntity,
		ICasterEntity {
	private int hp;
	private int maxHp;
	private int mp;
	private int maxMp;
	private double aps;
	private int range;

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
		this.hp = 10;
		this.maxHp = this.hp;
		this.mp = 10;
		this.maxMp = this.mp;
		this.aps = 2;
		this.range = 0;
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

	/*
	 * Parte referente a ICasterEntity
	 */

	@Override
	public int getMP() {
		return this.mp;
	}

	@Override
	public double getPorcentagemMP() {
		if (this.maxMp == 0) {
			return 0;
		}
		return ((double) this.mp) / (this.maxMp);
	}

	@Override
	public boolean spendMP(final int amount) {
		return false;
	}

	@Override
	public boolean receiveMP(final int amount) {
		return false;
	}

	@Override
	public void onFrameUpdate() {
		// TODO Auto-generated method stub
	}

	/*
	 * Meu
	 */
	public abstract String getName();

	public void selectRotation(final Vector2D diff) {

	}
}

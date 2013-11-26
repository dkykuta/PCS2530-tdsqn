package com.aehooo.tdsqn.entity.unit;

import org.andengine.entity.scene.Scene;

import android.util.Log;

import com.aehooo.tdsqn.annotations.Vel;
import com.aehooo.tdsqn.entity.ICasterEntity;
import com.aehooo.tdsqn.entity.ILiveEntity;
import com.aehooo.tdsqn.entity.impl.ActionEntity;
import com.aehooo.tdsqn.manager.LevelManager;
import com.aehooo.tdsqn.utils.Vector2D;

public abstract class BasicUnit extends ActionEntity implements ILiveEntity,
		ICasterEntity {
	private int hp;
	private int maxHp;
	private int mp;
	private int maxMp;

	private double vel;

	public static final int NORMAL_BAIXO = 0;
	public static final int NORMAL_DIREITA = 1;
	public static final int NORMAL_CIMA = 2;
	public static final int NORMAL_ESQUERDA = 3;

	private BasicUnit(final Scene fScene, final Vector2D pos) throws Exception {
		super(fScene, pos);

		this.initializeConstants();
	}

	public BasicUnit() throws Exception {
		this(LevelManager.getCurrentLevelScene(), new Vector2D(0, 0));
	}

	private void initializeConstants() {
		this.hp = 10;
		this.maxHp = this.hp;
		this.mp = 10;
		this.maxMp = this.mp;

		this.vel = 2;
		Vel velAnn = this.getClass().getAnnotation(Vel.class);
		if (velAnn != null) {
			this.vel = velAnn.value();
		}
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
		Log.i("BasicUnit", "takeDamage 1");
		if (this.isDead()) {
			return false;
		}
		Log.i("BasicUnit", "takeDamage 2");
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
		if (this.hp <= 0) {
			this.shouldDie();
		}
	}

	/*
	 * Meu
	 */
	public abstract String getName();

	public double getVel() {
		return this.vel;
	}

	public void selectRotation(final Vector2D diff) {
		if (diff.getY() == 0) {
			// movimento na horizontal
			if (diff.getX() > 0) {
				// movimento para a direita
				this.animateLinha(BasicUnit.NORMAL_DIREITA);
			} else if (diff.getX() < 0) {
				// movimento para a esquerda
				this.animateLinha(BasicUnit.NORMAL_ESQUERDA);
			}
		} else if (diff.getX() == 0) {
			// movimento na vertical
			if (diff.getY() > 0) {
				// movimento para baixo
				this.animateLinha(BasicUnit.NORMAL_BAIXO);
			} else if (diff.getY() < 0) {
				// movimento para cima
				this.animateLinha(BasicUnit.NORMAL_CIMA);
			}
		}
	}
}

package com.aehooo.tdsqn.entity.unit;

import org.andengine.entity.scene.Scene;

import com.aehooo.tdsqn.entity.GameEntity;
import com.aehooo.tdsqn.entity.ICasterEntity;
import com.aehooo.tdsqn.entity.ILiveEntity;
import com.aehooo.tdsqn.utils.Constants;
import com.aehooo.tdsqn.utils.Vector2D;

public abstract class BasicUnit extends GameEntity implements ILiveEntity,
		ICasterEntity {
	private int hp;
	private int maxHp;
	private int mp;
	private int maxMp;
	private double aps;
	private int range;
	private double cd;

	public static final String NORMAL = "";

	public BasicUnit(final Scene fScene, final Vector2D pos) throws Exception {
		super(fScene, pos);

		this.initializeConstants();
	}

	public BasicUnit(final Scene fScene, final float pX, final float pY)
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
		this.cd = 0;
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
	public double getAPS() {
		return this.aps;
	}

	@Override
	public void cooldown() {
		this.cd += (Constants.fps / this.aps);
	}

	@Override
	public int getRange() {
		return this.range;
	}

	public boolean isOnCooldown() {
		return this.cd > 0;
	}

	/*
	 * Meu
	 */
	public abstract String getName();
	
	public void selectRotation(Vector2D diff) {
		if (diff.getY() == 0) {
			// movimento na horizontal
			if (diff.getX() > 0) {
				// movimento para a direita
				this.animateLinha(1);
			} else if (diff.getX() < 0) {
				// movimento para a esquerda
				this.animateLinha(3);
			}
		}
		else if (diff.getX() == 0) {
			//movimento na vertical
			if (diff.getY() > 0) {
				// movimento para baixo
				this.animateLinha(0);
			}
			else if (diff.getY() < 0) {
				// movimento para cima
				this.animateLinha(2);
			}
		}
	}
}

package com.aehooo.tdsqn.entity;

public interface ILiveEntity {
	public int getHP();

	public double getPorcentagemHP();

	public boolean takeDamage(int amount);

	public boolean receiveHeal(int amount);
}

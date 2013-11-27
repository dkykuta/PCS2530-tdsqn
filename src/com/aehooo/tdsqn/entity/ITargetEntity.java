package com.aehooo.tdsqn.entity;

public interface ITargetEntity extends IGameEntity {

	public boolean takeDamage(int amount);

	public boolean receiveHeal(int amount);

	public boolean slow(final ILiveEntity origin, final double pctg,
			final int framesDur);

}

package com.aehooo.tdsqn.entity;

public interface ICasterEntity {
	public int getMP();

	public double getPorcentagemMP();

	public boolean spendMP(int amount);

	public boolean receiveMP(int amount);
}

package com.aehooo.tdsqn.path;

import java.util.ArrayList;
import java.util.List;

import com.aehooo.tdsqn.utils.Vector2D;

public class Path {
	
	private List<Vector2D> pontos;
	
	public Path() {
		this.pontos = new ArrayList<Vector2D>();
	}
	
	public void addPonto(int x, int y) {
		this.addPonto(new Vector2D(x, y));
	}
	
	public void addPonto(Vector2D ponto) {
		this.pontos.add(ponto);
	}
	
	public int size() {
		return this.pontos.size();
	}
	
	public List<Vector2D> getList() {
		return this.pontos;
	}
	
	public Vector2D getPoint(int i) {
		return this.pontos.get(i);
	}
	
	public Vector2D getDir(int from, int to) {
		return this.pontos.get(to).sub(this.pontos.get(from)).normalize();
	}
	
	public boolean isBeyond(int nextPoint, Vector2D newpos) {
		Vector2D before = this.pontos.get(nextPoint - 1);
		Vector2D after = this.pontos.get(nextPoint);
		
		double dx = (after.getX() - before.getX()) * (after.getX() - newpos.getX());
		double dy = (after.getY() - before.getY()) * (after.getY() - newpos.getY());
		
		return dx <= 0 && dy <= 0;
	}
}

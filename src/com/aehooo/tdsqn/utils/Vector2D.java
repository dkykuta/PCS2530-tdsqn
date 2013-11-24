package com.aehooo.tdsqn.utils;

public class Vector2D {
	private int x;
	private int y;

	public Vector2D(final int x, final int y) {
		this.x = x;
		this.y = y;
	}

	public Vector2D(final double x, final double y) {
		this((int) Math.floor(x), (int) Math.floor(y));
	}

	public int getX() {
		return this.x;
	}

	public void setX(final int x) {
		this.x = x;
	}

	public int getY() {
		return this.y;
	}

	public void setY(final int y) {
		this.y = y;
	}
	
	public double distTo(final Vector2D other) {
		double dx = this.x - other.x;
		double dy = this.y - other.y;
		return Math.sqrt(dx * dx - dy * dy);
	}
	
	public Vector2D add(final Vector2D other) {
		if (other == null)
			return this;
		return new Vector2D(this.x + other.x, this.y + other.y);
	}

	public Vector2D sub(final Vector2D other) {
		if (other == null)
			return this;
		return new Vector2D(this.x - other.x, this.y - other.y);
	}

	public Vector2D mul(final double scalar) {
		return new Vector2D(this.x * scalar, this.y * scalar);
	}

	public Vector2D div(final double scalar) {
		return new Vector2D(this.x / scalar, this.y / scalar);
	}
	
	public Vector2D normalize() {
		double norma = Math.sqrt(this.x * this.x + this.y * this.y);
		return this.div(norma);
	}
}

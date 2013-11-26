package com.aehooo.tdsqn.utils;

public class Vector2D {
	private double x;
	private double y;

	public Vector2D(final double x, final double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return this.x;
	}

	public int getXasInt() {
		return (int) Math.floor(this.x);
	}

	public void setX(final double x) {
		this.x = x;
	}

	public double getY() {
		return this.y;
	}

	public int getYasInt() {
		return (int) Math.floor(this.y);
	}

	public void setY(final double y) {
		this.y = y;
	}

	public double distTo(final Vector2D other) {
		double dx = this.x - other.x;
		double dy = this.y - other.y;
		return Math.sqrt((dx * dx) + (dy * dy));
	}

	public Vector2D add(final Vector2D other) {
		if (other == null) {
			return this;
		}
		return new Vector2D(this.x + other.x, this.y + other.y);
	}

	public Vector2D add(final double x, final double y) {
		return new Vector2D(this.x + x, this.y + y);
	}

	public Vector2D sub(final Vector2D other) {
		if (other == null) {
			return this;
		}
		return new Vector2D(this.x - other.x, this.y - other.y);
	}

	public Vector2D mul(final double scalar) {
		return new Vector2D(this.x * scalar, this.y * scalar);
	}

	public Vector2D div(final double scalar) {
		return new Vector2D(this.x / scalar, this.y / scalar);
	}

	public Vector2D normalize() {
		double norma = Math.sqrt((this.x * this.x) + (this.y * this.y));
		return this.div(norma);
	}

	public boolean isBetween(final Vector2D p1, final Vector2D p2) {
		double dx = (this.getX() - p1.getX()) * (this.getX() - p2.getX());
		double dy = (this.getY() - p1.getY()) * (this.getY() - p2.getY());

		return (dx <= 0) && (dy <= 0);
	}

	public static double dist(final Vector2D p1, final Vector2D p2) {
		double auxX = p1.x - p2.x;
		double auxY = p1.y - p2.y;
		return Math.sqrt((auxX * auxX) + (auxY * auxY));
	}

	@Override
	public String toString() {
		return "(" + this.x + ", " + this.y + ")";
	}
}

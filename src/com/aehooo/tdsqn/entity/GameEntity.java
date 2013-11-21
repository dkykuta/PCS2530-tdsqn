package com.aehooo.tdsqn.entity;

import com.aehooo.tdsqn.utils.Vector2D;

public class GameEntity {

	private ThisGameAnimatedSprite sprite;
	private Vector2D pos;

	public GameEntity(final float pX, final float pY) {
		this(new Vector2D(pX, pY));
	}

	public GameEntity(final Vector2D pos) {
		this.setPos(pos);
	}

	public Vector2D getPos() {
		return this.pos;
	}

	public void setPos(final Vector2D v) {
		this.pos = v;
		if (this.sprite != null) {
			this.sprite.setPosition(v.getX(), v.getY());
		}
	}

	public void setPos(final float x, final float y) {
		this.setPos(new Vector2D(x, y));
	}

	public ThisGameAnimatedSprite getSprite() {
		return this.sprite;
	}

	public void setSprite(final ThisGameAnimatedSprite sprite) {
		this.sprite = sprite;
		this.sprite.setPosition(this.pos.getX(), this.pos.getY());
	}

	public void setTouchHandler(final ITouchHandler touchHandler) {
		if (this.sprite != null) {
			this.sprite.setTouchHandler(touchHandler);
		}
	}
}

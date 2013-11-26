package com.aehooo.tdsqn.entity;

import org.andengine.input.touch.TouchEvent;

import com.aehooo.tdsqn.utils.Vector2D;

public interface IGameEntity {
	public void shouldDie();

	public boolean isDead();

	public Vector2D getPos();

	public void setPos(final Vector2D v);

	public void setPos(final float x, final float y);

	public void changePos(final Vector2D change);

	public Vector2D getCenterInGameWindow();

	public IThisGameSprite getSprite();

	public void setSprite(final IThisGameSprite sprite);

	public void setTouchHandler(final ITouchHandler touchHandler);

	public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
			final float pTouchAreaLocalX, final float pTouchAreaLocalY);

	public void animateLinha(final int line);
}

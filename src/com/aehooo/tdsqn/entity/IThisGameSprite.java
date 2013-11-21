package com.aehooo.tdsqn.entity;

import org.andengine.entity.shape.IAreaShape;
import org.andengine.input.touch.TouchEvent;

public interface IThisGameSprite extends IAreaShape {

	public void removeTouchHandler();

	public void setTouchHandler(final ITouchHandler touchHandler);

	public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
			final float pTouchAreaLocalX, final float pTouchAreaLocalY);
}

package com.aehooo.tdsqn.entity;

import org.andengine.input.touch.TouchEvent;

public interface ITouchHandler {
	public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
			final float pTouchAreaLocalX, final float pTouchAreaLocalY);
}

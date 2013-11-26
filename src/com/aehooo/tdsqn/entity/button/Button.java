package com.aehooo.tdsqn.entity.button;

import org.andengine.entity.scene.Scene;
import org.andengine.input.touch.TouchEvent;

import com.aehooo.tdsqn.entity.impl.GameEntity;
import com.aehooo.tdsqn.utils.Vector2D;

public class Button extends GameEntity {

	public Button(final Scene fScene, final Vector2D pos) throws Exception {
		super(fScene, pos);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onFrameUpdate() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
			final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
		return true;
	}

}

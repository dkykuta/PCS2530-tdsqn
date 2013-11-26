package com.aehooo.tdsqn.entity.impl;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITiledTextureRegion;

import com.aehooo.tdsqn.entity.IThisGameSprite;
import com.aehooo.tdsqn.entity.ITouchHandler;
import com.aehooo.tdsqn.resources.ImageAlligator3000;

public class ThisGameAnimatedSprite extends AnimatedSprite implements
		IThisGameSprite {

	private ITouchHandler touchHandler;

	public ThisGameAnimatedSprite(final float pX, final float pY,
			final ITiledTextureRegion pTiledTextureRegion) {
		super(pX, pY, pTiledTextureRegion, ImageAlligator3000
				.getVertexBufferObjectManager());
	}

	@Override
	public void removeTouchHandler() {
		this.touchHandler = null;
	}

	@Override
	public void setTouchHandler(final ITouchHandler touchHandler) {
		this.touchHandler = touchHandler;
	}

	@Override
	public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
			final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
		if (this.touchHandler != null) {
			return this.touchHandler.onAreaTouched(pSceneTouchEvent,
					pTouchAreaLocalX, pTouchAreaLocalY);
		}
		return false;
	}
}

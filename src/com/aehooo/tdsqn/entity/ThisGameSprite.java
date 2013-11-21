package com.aehooo.tdsqn.entity;

import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;

import com.aehooo.tdsqn.resources.ImageAlligator3000;

public class ThisGameSprite extends Sprite implements IThisGameSprite {
	private ITouchHandler touchHandler;

	public ThisGameSprite(final float pX, final float pY,
			final ITextureRegion pTextureRegion) {
		super(pX, pY, pTextureRegion, ImageAlligator3000
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

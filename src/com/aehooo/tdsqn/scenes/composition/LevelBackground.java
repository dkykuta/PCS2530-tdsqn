package com.aehooo.tdsqn.scenes.composition;

import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.util.Log;

public class LevelBackground extends Sprite {
	public LevelBackground(final float pX, final float pY,
			final ITextureRegion pTextureRegion,
			final VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
	}

	private float antx = 0;
	private float anty = 0;

	private float posx = 0;
	private float posy = 0;

	@Override
	public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
			final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
		if (pSceneTouchEvent.isActionDown()) {
			Log.i("AEHO.LevelScene", "ActionDown");
			this.antx = pSceneTouchEvent.getX();
			this.anty = pSceneTouchEvent.getY();

			this.posx = this.getX();
			this.posy = this.getY();
		} else if (pSceneTouchEvent.isActionUp()) {
			Log.i("AEHO.LevelScene", "ActionUp");
		} else if (pSceneTouchEvent.isActionMove()) {
			Log.i("AEHO.LevelScene", "ActionMove");
			float newx = this.posx + (pSceneTouchEvent.getX() - this.antx);
			float newy = this.posy + (pSceneTouchEvent.getY() - this.anty);

			if (newx > 100) {
				newx = 100;
			}
			if (newy > 0) {
				newy = 0;
			}

			if ((newx - 800) < -1400) {
				newx = -600;
			}
			if ((newy - 480) < -960) {
				newy = -480;
			}

			this.setPosition(newx, newy);
		}

		return true;
	}
}

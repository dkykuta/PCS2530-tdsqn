package com.aehooo.tdsqn.entity.button;

import org.andengine.entity.scene.Scene;
import org.andengine.input.touch.TouchEvent;

import com.aehooo.tdsqn.annotations.TextureInfo;
import com.aehooo.tdsqn.entity.unit.BasicUnit;
import com.aehooo.tdsqn.manager.LevelManager;
import com.aehooo.tdsqn.resources.TextureName;
import com.aehooo.tdsqn.utils.Vector2D;

@TextureInfo(name = TextureName.BUTTON)
public class ReiButton extends UnitButton {

	public ReiButton(final Scene fScene,
			final Class<? extends BasicUnit> clazz, final Vector2D pos)
			throws Exception {
		super(fScene, clazz, pos);
	}

	public ReiButton(final Scene fScene,
			final Class<? extends BasicUnit> clazz, final int x, final int y)
			throws Exception {
		this(fScene, clazz, new Vector2D(x, y));
	}

	@Override
	public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
			final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
		if (!pSceneTouchEvent.isActionDown()) {
			return true;
		}
		if (super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX,
				pTouchAreaLocalY)) {
			this.getSprite().setColor(0.3f, 0.3f, 0.3f);
			LevelManager.getCurrentLevelScene().unregisterTouchArea(
					this.getSprite());
			return true;
		}
		return false;
	}
}

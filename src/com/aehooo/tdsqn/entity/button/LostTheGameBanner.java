package com.aehooo.tdsqn.entity.button;

import org.andengine.entity.scene.Scene;
import org.andengine.input.touch.TouchEvent;

import com.aehooo.tdsqn.annotations.TextureInfo;
import com.aehooo.tdsqn.entity.impl.GameEntity;
import com.aehooo.tdsqn.manager.GameManager;
import com.aehooo.tdsqn.resources.TextureName;
import com.aehooo.tdsqn.utils.Vector2D;

@TextureInfo(name = TextureName.LOST)
public class LostTheGameBanner extends GameEntity {

	public LostTheGameBanner(final Scene fScene, final Vector2D pos)
			throws Exception {
		super(fScene, pos);
	}

	public LostTheGameBanner(final Scene fScene, final int x, final int y)
			throws Exception {
		this(fScene, new Vector2D(x, y));
	}

	@Override
	public void onFrameUpdate() {

	}

	@Override
	public void onCheckDeadChildren() {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
			final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
		if (!pSceneTouchEvent.isActionDown()) {
			return true;
		}

		GameManager.exit();

		return true;
	}
}

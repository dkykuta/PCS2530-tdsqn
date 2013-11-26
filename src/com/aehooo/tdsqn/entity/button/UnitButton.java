package com.aehooo.tdsqn.entity.button;

import org.andengine.entity.scene.Scene;
import org.andengine.input.touch.TouchEvent;

import android.util.Log;

import com.aehooo.tdsqn.annotations.TextureInfo;
import com.aehooo.tdsqn.entity.impl.GameEntity;
import com.aehooo.tdsqn.entity.unit.BasicUnit;
import com.aehooo.tdsqn.entity.unit.Zombie;
import com.aehooo.tdsqn.manager.LevelManager;
import com.aehooo.tdsqn.resources.TextureName;
import com.aehooo.tdsqn.scenes.LevelScene;
import com.aehooo.tdsqn.utils.Vector2D;

@TextureInfo(linhas = { "" }, name = TextureName.BUTTON)
public class UnitButton extends GameEntity {

	private Class<? extends BasicUnit> clazz;

	public UnitButton(final Scene fScene,
			final Class<? extends BasicUnit> clazz, final Vector2D pos)
			throws Exception {
		super(fScene, pos);
		this.clazz = clazz;
	}

	public UnitButton(final Scene fScene,
			final Class<? extends BasicUnit> clazz, final int x, final int y)
			throws Exception {
		this(fScene, clazz, new Vector2D(x, y));
	}

	@Override
	public void onFrameUpdate() {

	}

	@Override
	public void onCheckDead() {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
			final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
		if (!pSceneTouchEvent.isActionDown()) {
			return true;
		}
		LevelScene level = LevelManager.getCurrentLevelScene();
		Zombie zz;
		BasicUnit u;
		try {
			u = this.clazz.newInstance();
			// zz = new Zombie();
		} catch (Exception e) {
			Log.e("LevelScene", "Zombie com erro", e);
			return false;
		}
		level.getGroups().get(0).addUnit(u);
		return true;
	}
}

package com.aehooo.tdsqn.entity.button;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.TiledTextureRegion;

import android.util.Log;

import com.aehooo.tdsqn.annotations.TextureInfo;
import com.aehooo.tdsqn.entity.group.Group;
import com.aehooo.tdsqn.entity.impl.GameEntity;
import com.aehooo.tdsqn.entity.unit.BasicUnit;
import com.aehooo.tdsqn.manager.LevelManager;
import com.aehooo.tdsqn.resources.ImageAlligator3000;
import com.aehooo.tdsqn.resources.TextureName;
import com.aehooo.tdsqn.scenes.LevelScene;
import com.aehooo.tdsqn.utils.Vector2D;

@TextureInfo(name = TextureName.BUTTON)
public class UnitButton extends GameEntity {

	private Class<? extends BasicUnit> clazz;

	public UnitButton(final Scene fScene,
			final Class<? extends BasicUnit> clazz, final Vector2D pos)
			throws Exception {
		super(fScene, pos);
		this.clazz = clazz;
		TextureInfo annotation = this.clazz.getAnnotation(TextureInfo.class);
		if (annotation != null) {
			TiledTextureRegion texture = ImageAlligator3000
					.getTiledTexture(annotation.name());
			TiledSprite unitSprite = new TiledSprite(12, 12, texture,
					ImageAlligator3000.getVertexBufferObjectManager());
			unitSprite.setCurrentTileIndex(0);
			this.getSprite().attachChild(unitSprite);
		}
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
	public void onCheckDeadChildren() {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
			final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
		if (!pSceneTouchEvent.isActionDown()) {
			return false;
		}
		LevelScene level = LevelManager.getCurrentLevelScene();
		BasicUnit u;
		try {
			u = this.clazz.newInstance();
			Group g = level.getUninitializedGroup();
			if (g != null) {
				return g.addUnit(u);
			}
		} catch (Exception e) {
			Log.e("LevelScene", "Zombie com erro", e);
			return false;
		}
		return false;
	}
}

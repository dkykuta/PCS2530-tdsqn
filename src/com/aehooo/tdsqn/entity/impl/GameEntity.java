package com.aehooo.tdsqn.entity.impl;

import org.andengine.entity.IEntity;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;

import com.aehooo.tdsqn.annotations.TextureInfo;
import com.aehooo.tdsqn.entity.IThisGameSprite;
import com.aehooo.tdsqn.entity.ITouchHandler;
import com.aehooo.tdsqn.entity.IUpdatable;
import com.aehooo.tdsqn.manager.LevelManager;
import com.aehooo.tdsqn.resources.ImageAlligator3000;
import com.aehooo.tdsqn.resources.TextureName;
import com.aehooo.tdsqn.scenes.LevelScene;
import com.aehooo.tdsqn.utils.Vector2D;

public abstract class GameEntity implements IUpdatable {

	private IThisGameSprite sprite;
	private Vector2D pos;
	protected int colunas;
	private int lastAnimatedLine;
	private boolean dead;

	private boolean animated;

	public GameEntity(final Scene fScene, final float pX, final float pY)
			throws Exception {
		this(fScene, new Vector2D(pX, pY));
	}

	public GameEntity(final Scene fScene, final Vector2D pos) throws Exception {
		this.setPos(pos);
		this.lastAnimatedLine = -1;
		this.initializeSprite(fScene, pos);
		this.dead = false;
	}

	private void initializeSprite(final Scene fScene, final Vector2D pos)
			throws Exception {
		TextureInfo annotation = this.getClass().getAnnotation(
				TextureInfo.class);
		if (annotation == null) {
			this.setSprite(null);
		} else {
			TextureName textureName = annotation.name();

			TiledTextureRegion tiledTexture = ImageAlligator3000
					.getTiledTexture(textureName);

			TextureRegion texture = ImageAlligator3000.getTexture(textureName);

			if (tiledTexture != null) {
				this.colunas = tiledTexture.getTileCount()
						/ annotation.linhas().length;
				this.setSprite(new ThisGameAnimatedSprite(pos.getXasInt(), pos
						.getYasInt(), tiledTexture));
				this.animated = true;
				this.animateLinha(0);
			} else if (texture != null) {
				this.setSprite(new ThisGameSprite(pos.getXasInt(), pos
						.getYasInt(), texture));
				this.animated = false;
			} else {
				throw new Exception("Sprite '" + textureName
						+ "' não encontrado");
			}

			this.setTouchHandler(new ITouchHandler() {
				@Override
				public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
						final float pTouchAreaLocalX,
						final float pTouchAreaLocalY) {
					return GameEntity.this.onAreaTouched(pSceneTouchEvent,
							pTouchAreaLocalX, pTouchAreaLocalY);
				}
			});
			fScene.registerTouchArea(this.getSprite());
		}
	}

	public void shouldDie() {
		LevelScene scene = LevelManager.getCurrentLevelScene();
		scene.unregisterTouchArea(this.getSprite());
		this.getSprite().detachChildren();
		this.getSprite().detachSelf();
		this.dead = true;
	}

	public boolean isDead() {
		return this.dead;
	}

	public Vector2D getPos() {
		return this.pos;
	}

	public void setPos(final Vector2D v) {
		this.pos = v;
		if (this.sprite != null) {
			this.sprite.setPosition(v.getXasInt(), v.getYasInt());
		}
	}

	public void setPos(final float x, final float y) {
		this.setPos(new Vector2D(x, y));
	}

	public Vector2D getPosInGameWindow() {
		IEntity parent = this.getSprite().getParent();
		Vector2D ret = this.getPos();
		while (parent != LevelManager.getCurrentLevelScene().getGameWindow()) {
			if (parent instanceof IThisGameSprite) {
				IThisGameSprite entity = (IThisGameSprite) parent;
				ret = ret.add(entity.getX(), entity.getY());
				parent = parent.getParent();
			}
		}
		return ret;
	}

	public void changePos(final Vector2D change) {
		this.setPos(this.getPos().add(change));
	}

	public Vector2D getCenterInGameWindow() {
		return this.getPosInGameWindow().add(
				new Vector2D(this.getSprite().getWidthScaled() / 2, this
						.getSprite().getHeightScaled() / 2));
	}

	public IThisGameSprite getSprite() {
		return this.sprite;
	}

	public void setSprite(final IThisGameSprite sprite) {
		this.sprite = sprite;
		this.sprite.setPosition(this.pos.getXasInt(), this.pos.getYasInt());
	}

	public void setTouchHandler(final ITouchHandler touchHandler) {
		if (this.sprite != null) {
			this.sprite.setTouchHandler(touchHandler);
		}
	}

	public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
			final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
		return false;
	}

	protected void animate(final long[] pFrameDurations, final int[] pFrames) {
		if (this.animated) {
			AnimatedSprite aSprite = (AnimatedSprite) this.sprite;
			aSprite.animate(pFrameDurations, pFrames);
		}
	}

	public void animateLinha(final int line) {
		if (line == this.lastAnimatedLine) {
			return;
		}
		long[] duracoes = new long[this.colunas];
		for (int i = 0; i < this.colunas; i++) {
			duracoes[i] = 100;
		}
		int[] frames = new int[this.colunas];
		for (int i = 0; i < this.colunas; i++) {
			frames[i] = (line * this.colunas) + i;
		}
		this.lastAnimatedLine = line;
		this.animate(duracoes, frames);
	}

}

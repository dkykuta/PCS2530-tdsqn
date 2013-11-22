package com.aehooo.tdsqn.entity.unit;

import org.andengine.entity.scene.Scene;
import org.andengine.input.touch.TouchEvent;

import com.aehooo.tdsqn.annotations.TextureInfo;
import com.aehooo.tdsqn.resources.TextureName;
import com.aehooo.tdsqn.utils.Vector2D;

@TextureInfo(name = TextureName.ZOMBIE, linhas = { "normal-baixo",
		"normal-direita", "normal-cima", "normal-esquerda" })
public class Zombie extends BasicUnit {

	public Zombie(final Scene fScene, final Vector2D pos) throws Exception {
		super(fScene, pos);
	}

	int lastLinha = 0;

	@Override
	public void animateLinha(final int linha) {
		super.animateLinha(linha);
		this.lastLinha = linha;
	}

	@Override
	public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
			final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
		if (pSceneTouchEvent.isActionDown()) {
			this.animateLinha((this.lastLinha + 1) % 4);
		}
		return true;
	}

}

package com.aehooo.tdsqn.entity.unit;

import org.andengine.input.touch.TouchEvent;

import com.aehooo.tdsqn.annotations.TextureInfo;
import com.aehooo.tdsqn.resources.TextureName;
import com.aehooo.tdsqn.utils.Vector2D;

@TextureInfo(TextureName.ZOMBIE)
public class Zombie extends BasicUnit {

	public Zombie(final Vector2D pos) {
		super(pos);
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

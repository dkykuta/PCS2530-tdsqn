package com.aehooo.tdsqn.entity.group;

import org.andengine.entity.scene.Scene;

import com.aehooo.tdsqn.annotations.TextureInfo;
import com.aehooo.tdsqn.entity.GameEntity;
import com.aehooo.tdsqn.resources.TextureName;
import com.aehooo.tdsqn.utils.Vector2D;

@TextureInfo(name = TextureName.GROUP, linhas = { "normal" })
public class Group extends GameEntity {

	public Group(final Scene fScene, final Vector2D pos) throws Exception {
		super(fScene, pos);
	}

	public Group(final Scene fScene, final float pX, final float pY)
			throws Exception {
		this(fScene, new Vector2D(pX, pY));
	}

	@Override
	public void animateLinha(final int linha) {
		long[] duracoes = new long[4];
		for (int i = 0; i < 4; i++) {
			duracoes[i] = 400;
		}
		int[] frames = { 0, 1, 2, 1 };
		this.animate(duracoes, frames);
	}
}

package com.aehooo.tdsqn.entity.tower;

import org.andengine.entity.scene.Scene;

import com.aehooo.tdsqn.annotations.TextureInfo;
import com.aehooo.tdsqn.resources.TextureName;

@TextureInfo(linhas = { "" }, name = TextureName.ZOMBIE)
public class OneTower extends BasicTower {

	public OneTower(final Scene fScene, final float pX, final float pY)
			throws Exception {
		super(fScene, pX, pY);
	}

	@Override
	public String getName() {
		return "OneTower";
	}

}

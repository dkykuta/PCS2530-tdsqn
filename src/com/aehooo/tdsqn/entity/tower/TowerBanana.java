package com.aehooo.tdsqn.entity.tower;

import org.andengine.entity.scene.Scene;

import com.aehooo.tdsqn.annotations.APS;
import com.aehooo.tdsqn.annotations.BuildAction;
import com.aehooo.tdsqn.annotations.TextureInfo;
import com.aehooo.tdsqn.entity.action.Action;
import com.aehooo.tdsqn.enums.GameTargetType;
import com.aehooo.tdsqn.resources.TextureName;

@TextureInfo(name = TextureName.TORRE_BANANA)
@APS(2)
public class TowerBanana extends BasicTower {

	public TowerBanana(final Scene fScene, final float pX, final float pY)
			throws Exception {
		super(fScene, pX, pY);
	}

	@Override
	public String getName() {
		return "TowerBanana";
	}

	@BuildAction(targetType = GameTargetType.GROUP)
	public void buildAction(final Action action) {
		action.getSprite().setRed(0);
		action.slow(0.5, 30, 0);
	}
}

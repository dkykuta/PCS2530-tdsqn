package com.aehooo.tdsqn.entity.tower;

import org.andengine.entity.scene.Scene;

import com.aehooo.tdsqn.annotations.APS;
import com.aehooo.tdsqn.annotations.BuildAction;
import com.aehooo.tdsqn.annotations.TextureInfo;
import com.aehooo.tdsqn.entity.action.Action;
import com.aehooo.tdsqn.enums.GameTargetType;
import com.aehooo.tdsqn.resources.TextureName;

@TextureInfo(name = TextureName.TORRE_MELANCIA)
@APS(0.7)
public class TowerMelancia extends BasicTower {

	public TowerMelancia(final Scene fScene, final float pX, final float pY)
			throws Exception {
		super(fScene, pX, pY);
	}

	@Override
	public String getName() {
		return "TowerMelancia";
	}

	@BuildAction(targetType = GameTargetType.UNIT)
	public void buildAction(final Action action) {
		action.getSprite().setBlue(0);
		action.damage(10, 0);
	}
}

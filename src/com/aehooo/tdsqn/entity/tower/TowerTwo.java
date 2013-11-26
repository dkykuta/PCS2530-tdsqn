package com.aehooo.tdsqn.entity.tower;

import org.andengine.entity.scene.Scene;

import com.aehooo.tdsqn.annotations.APS;
import com.aehooo.tdsqn.annotations.BuildAction;
import com.aehooo.tdsqn.annotations.TextureInfo;
import com.aehooo.tdsqn.entity.action.Action;
import com.aehooo.tdsqn.enums.GameTargetType;
import com.aehooo.tdsqn.resources.TextureName;

@TextureInfo(linhas = { "" }, name = TextureName.TORRE1)
@APS(2)
public class TowerTwo extends BasicTower {

	public TowerTwo(final Scene fScene, final float pX, final float pY)
			throws Exception {
		super(fScene, pX, pY);
		this.getSprite().setBlue(0.2f);
		this.getSprite().setGreen(0.2f);
	}

	@Override
	public String getName() {
		return "OneTower";
	}

	@BuildAction(targetType = GameTargetType.UNIT)
	public void buildAction(final Action action) {
		action.getSprite().setBlue(0);
		action.damage(30, 0);
	}
}

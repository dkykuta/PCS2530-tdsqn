package com.aehooo.tdsqn.entity.barragem;

import org.andengine.entity.scene.Scene;

import com.aehooo.tdsqn.annotations.APS;
import com.aehooo.tdsqn.annotations.BuildAction;
import com.aehooo.tdsqn.annotations.TextureInfo;
import com.aehooo.tdsqn.entity.action.Action;
import com.aehooo.tdsqn.enums.GameTargetType;
import com.aehooo.tdsqn.resources.TextureName;

@TextureInfo(linhas = { "" }, name = TextureName.TORRE1)
@APS(2)
public class TowerOne extends BasicBarragem {

	public TowerOne(final Scene fScene, final float pX, final float pY)
			throws Exception {
		super(fScene, pX, pY);
	}

	@Override
	public String getName() {
		return "OneTower";
	}

	@BuildAction(targetType = GameTargetType.GROUP)
	public void buildAction(final Action action) {
		action.getSprite().setRed(0);
		action.slow(0.5, 30, 0);
	}
}

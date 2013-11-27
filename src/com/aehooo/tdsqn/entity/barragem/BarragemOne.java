package com.aehooo.tdsqn.entity.barragem;

import org.andengine.entity.scene.Scene;

import com.aehooo.tdsqn.annotations.BuildAction;
import com.aehooo.tdsqn.annotations.TextureInfo;
import com.aehooo.tdsqn.entity.action.Action;
import com.aehooo.tdsqn.enums.GameTargetType;
import com.aehooo.tdsqn.resources.TextureName;

@TextureInfo(linhas = { "" }, name = TextureName.TORRE1)
public class BarragemOne extends BasicBarragem {

	public BarragemOne(final Scene fScene, final float pX, final float pY)
			throws Exception {
		super(fScene, pX, pY);
	}

	@Override
	public String getName() {
		return "BarragemOne";
	}

	@BuildAction(targetType = GameTargetType.GROUP, AoE = true)
	public void buildAction(final Action action) {
		action.getSprite().setRed(0);
		action.slow(0.5, 30, 0);
	}
}

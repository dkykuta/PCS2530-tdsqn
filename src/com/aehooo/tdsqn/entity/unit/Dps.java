package com.aehooo.tdsqn.entity.unit;

import com.aehooo.tdsqn.annotations.APS;
import com.aehooo.tdsqn.annotations.BuildAction;
import com.aehooo.tdsqn.annotations.TextureInfo;
import com.aehooo.tdsqn.annotations.Vel;
import com.aehooo.tdsqn.entity.action.Action;
import com.aehooo.tdsqn.enums.GameTargetType;
import com.aehooo.tdsqn.resources.TextureName;

@TextureInfo(name = TextureName.DPS, linhas = 4)
@Vel(1)
@APS(1.2)
public class Dps extends BasicUnit {

	public Dps() throws Exception {
		super();
	}

	@Override
	public String getName() {
		return "DPS";
	}

	@BuildAction(targetType = GameTargetType.TOWER)
	public void buildAction(final Action action) {
		action.getSprite().setBlue(0);
		action.damage(10, 0);
	}
}

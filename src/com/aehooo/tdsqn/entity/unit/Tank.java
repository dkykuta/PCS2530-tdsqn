package com.aehooo.tdsqn.entity.unit;

import com.aehooo.tdsqn.annotations.TextureInfo;
import com.aehooo.tdsqn.annotations.Vel;
import com.aehooo.tdsqn.resources.TextureName;

@TextureInfo(name = TextureName.TANK, linhas = 4)
@Vel(0.7)
public class Tank extends BasicUnit {

	public Tank() throws Exception {
		super();
	}

	@Override
	public boolean takeDamage(final int amount) {
		return super.takeDamage(amount / 2);
	}

	@Override
	public String getName() {
		return "Tank";
	}
}

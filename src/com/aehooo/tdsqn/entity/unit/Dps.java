package com.aehooo.tdsqn.entity.unit;

import com.aehooo.tdsqn.annotations.TextureInfo;
import com.aehooo.tdsqn.annotations.Vel;
import com.aehooo.tdsqn.resources.TextureName;

@TextureInfo(name = TextureName.DPS, linhas = 4)
@Vel(0.5)
public class Dps extends BasicUnit {

	public Dps() throws Exception {
		super();
	}

	@Override
	public String getName() {
		return "DPS";
	}
}

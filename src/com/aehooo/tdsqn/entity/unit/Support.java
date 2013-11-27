package com.aehooo.tdsqn.entity.unit;

import com.aehooo.tdsqn.annotations.TextureInfo;
import com.aehooo.tdsqn.annotations.Vel;
import com.aehooo.tdsqn.resources.TextureName;

@TextureInfo(name = TextureName.SUPPORT, linhas = 4)
@Vel(0.5)
public class Support extends BasicUnit {

	public Support() throws Exception {
		super();
	}

	@Override
	public String getName() {
		return "Support";
	}
}

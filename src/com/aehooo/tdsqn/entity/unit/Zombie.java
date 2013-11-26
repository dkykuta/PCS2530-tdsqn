package com.aehooo.tdsqn.entity.unit;

import com.aehooo.tdsqn.annotations.APS;
import com.aehooo.tdsqn.annotations.TextureInfo;
import com.aehooo.tdsqn.annotations.Vel;
import com.aehooo.tdsqn.resources.TextureName;

@TextureInfo(name = TextureName.ZOMBIE, linhas = { "normal-baixo",
		"normal-direita", "normal-cima", "normal-esquerda" })
@APS(2)
@Vel(0.3)
public class Zombie extends BasicUnit {

	public Zombie() throws Exception {
		super();
	}

	@Override
	public String getName() {
		return "Zombie";
	}

}

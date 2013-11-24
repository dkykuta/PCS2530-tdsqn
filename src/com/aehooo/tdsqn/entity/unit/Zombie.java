package com.aehooo.tdsqn.entity.unit;

import org.andengine.entity.scene.Scene;
import org.andengine.input.touch.TouchEvent;

import com.aehooo.tdsqn.annotations.TextureInfo;
import com.aehooo.tdsqn.resources.TextureName;
import com.aehooo.tdsqn.utils.Vector2D;

@TextureInfo(name = TextureName.ZOMBIE, linhas = { "normal-baixo",
		"normal-direita", "normal-cima", "normal-esquerda" })
public class Zombie extends BasicUnit {

	public Zombie(final Scene fScene, final Vector2D pos) throws Exception {
		super(fScene, pos);
	}

	@Override
	public String getName() {
		return "Zombie";
	}

}

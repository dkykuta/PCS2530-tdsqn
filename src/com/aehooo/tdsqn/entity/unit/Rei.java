package com.aehooo.tdsqn.entity.unit;

import com.aehooo.tdsqn.annotations.TextureInfo;
import com.aehooo.tdsqn.annotations.Vel;
import com.aehooo.tdsqn.manager.LevelManager;
import com.aehooo.tdsqn.resources.TextureName;

@TextureInfo(name = TextureName.REI, linhas = 4)
@Vel(2)
public class Rei extends BasicUnit {

	public Rei() throws Exception {
		super();
	}

	@Override
	public void shouldDie() {
		LevelManager.setWinner(this.terminouPercurso());
		LevelManager.setGameEnd(true);
		super.shouldDie();
	}

	@Override
	public String getName() {
		return "Rei";
	}
}

package com.aehooo.tdsqn.entity.unit;

import com.aehooo.tdsqn.annotations.APS;
import com.aehooo.tdsqn.annotations.BuildAction;
import com.aehooo.tdsqn.annotations.FilterTargets;
import com.aehooo.tdsqn.annotations.TextureInfo;
import com.aehooo.tdsqn.annotations.Vel;
import com.aehooo.tdsqn.entity.ILiveEntity;
import com.aehooo.tdsqn.entity.ITargetEntity;
import com.aehooo.tdsqn.entity.action.Action;
import com.aehooo.tdsqn.enums.GameTargetType;
import com.aehooo.tdsqn.resources.TextureName;

@TextureInfo(name = TextureName.ZOMBIE, linhas = { "normal-baixo",
		"normal-direita", "normal-cima", "normal-esquerda" })
@APS(0.2)
@Vel(0.5)
public class Healer extends BasicUnit {

	public Healer() throws Exception {
		super();
		this.getSprite().setRed(0);
	}

	@Override
	public String getName() {
		return "Zombie";
	}

	@FilterTargets
	public boolean filter(final ITargetEntity target) {
		if (!(target instanceof ILiveEntity)) {
			return false;
		}
		ILiveEntity entity = (ILiveEntity) target;
		if (entity.getPorcentagemHP() == 1) {
			return false;
		}
		return true;
	}

	public boolean compare(final ITargetEntity e1, final ITargetEntity e2) {
		if (!(e1 instanceof ILiveEntity)) {
			return false;
		}
		if (!(e2 instanceof ILiveEntity)) {
			return true;
		}
		ILiveEntity u1 = (ILiveEntity) e1;
		ILiveEntity u2 = (ILiveEntity) e2;

		return u1.getPorcentagemHP() < u2.getPorcentagemHP();
	}

	@BuildAction(targetType = GameTargetType.UNIT)
	public void buildAction(final Action action) {
		action.getSprite().setBlue(0);
		this.animateOnce(3);
		action.heal(10, 0);
	}
}

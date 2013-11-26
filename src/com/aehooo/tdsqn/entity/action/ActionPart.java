package com.aehooo.tdsqn.entity.action;

import com.aehooo.tdsqn.entity.ILiveEntity;
import com.aehooo.tdsqn.entity.ITargetEntity;

public abstract class ActionPart {

	private ILiveEntity origin;
	private ITargetEntity target;

	public ActionPart(final ILiveEntity origin, final ITargetEntity target) {
		this.origin = origin;
		this.target = target;
	}

	public abstract void execute();

}

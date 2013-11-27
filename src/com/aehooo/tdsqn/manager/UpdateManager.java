package com.aehooo.tdsqn.manager;

import java.util.ArrayList;
import java.util.List;

import org.andengine.engine.handler.IUpdateHandler;

import com.aehooo.tdsqn.entity.IUpdatable;

public class UpdateManager implements IUpdateHandler {

	private float t;
	private List<IUpdatable> listUpdatable;

	private static final double frameTime = 1.0 / 60;

	public UpdateManager() {
		this.t = 0;
		this.listUpdatable = new ArrayList<IUpdatable>();
	}

	@Override
	public void onUpdate(final float pSecondsElapsed) {
		this.t += pSecondsElapsed;
		while (this.t >= UpdateManager.frameTime) {
			this.t -= UpdateManager.frameTime;
			List<IUpdatable> cpList = new ArrayList<IUpdatable>(
					this.listUpdatable);
			for (IUpdatable e : cpList) {
				e.onFrameUpdate();
			}
			for (IUpdatable e : cpList) {
				e.onCheckDeadChildren();
			}
		}
	}

	@Override
	public void reset() {

	}

	public void addUpdatable(final IUpdatable updatable) {
		this.listUpdatable.add(updatable);
	}

	public void removeUpdatable(final IUpdatable updatable) {
		this.listUpdatable.remove(updatable);
	}
}

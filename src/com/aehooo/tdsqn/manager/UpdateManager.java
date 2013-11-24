package com.aehooo.tdsqn.manager;

import java.util.ArrayList;
import java.util.List;

import org.andengine.engine.handler.IUpdateHandler;

import android.util.Log;

import com.aehooo.tdsqn.entity.IUpdatable;

public class UpdateManager implements IUpdateHandler {

	private float t;
	private List<IUpdatable> listUpdatable;
	
	private static final double frameTime = 1.0 / 60;
	
	
	public UpdateManager() {
		t = 0;
		listUpdatable = new ArrayList<IUpdatable>();
	}
	
	@Override
	public void onUpdate(float pSecondsElapsed) {
		this.t += pSecondsElapsed;
		while (this.t >= frameTime) {
			this.t -= frameTime;
			for (IUpdatable e : listUpdatable) {
				e.onFrameUpdate();
			}
		}
	}

	@Override
	public void reset() {
		
	}
	
	public void addUpdatable(IUpdatable updatable) {
		listUpdatable.add(updatable);
	}
}

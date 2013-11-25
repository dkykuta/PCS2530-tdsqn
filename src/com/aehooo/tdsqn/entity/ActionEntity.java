package com.aehooo.tdsqn.entity;

import java.lang.reflect.Method;
import java.util.List;

import org.andengine.entity.scene.Scene;

import com.aehooo.tdsqn.annotations.APS;
import com.aehooo.tdsqn.annotations.FilterTargets;
import com.aehooo.tdsqn.annotations.SortTargets;
import com.aehooo.tdsqn.utils.Vector2D;

public abstract class ActionEntity extends GameEntity implements ILiveEntity,
		IUpdatable {

	private double cd;
	private double aps;

	public ActionEntity(final Scene fScene, final float pX, final float pY)
			throws Exception {
		this(fScene, new Vector2D(pX, pY));
	}

	public ActionEntity(final Scene fScene, final Vector2D pos)
			throws Exception {
		super(fScene, pos);
		this.cd = 0;
		APS aps = this.getClass().getAnnotation(APS.class);
		if (aps != null) {
			this.aps = aps.value();
		}
		else {
			this.aps = 1;
		}
	}

	public List<? extends GameEntity> getPossibleTargets() {

	}

	public boolean wrapperDoAction() {
		Method[] declaredMethods = this.getClass().getDeclaredMethods();
		Method filter = null;
		Method sort = null;
		for (Method method : declaredMethods) {
			if (method.getAnnotation(FilterTargets.class) != null) {
				filter = method;
			}
			if (method.getAnnotation(SortTargets.class) != null) {
				sort = method;
			}
		}
		return false;
	}

	public void cooldown() {
		this.cd += 60 / this.aps;
	}

	@Override
	public void onFrameUpdate() {
		if (this.cd > 0) {
			this.cd = this.cd - 1;
		}
		else if (this.wrapperDoAction()) {
			this.cooldown();
		}
	}

}

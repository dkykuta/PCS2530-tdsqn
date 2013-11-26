package com.aehooo.tdsqn.entity.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.andengine.entity.scene.Scene;

import android.util.Log;

import com.aehooo.tdsqn.annotations.APS;
import com.aehooo.tdsqn.annotations.BuildAction;
import com.aehooo.tdsqn.annotations.FilterTargets;
import com.aehooo.tdsqn.annotations.SortTargets;
import com.aehooo.tdsqn.entity.ILiveEntity;
import com.aehooo.tdsqn.entity.ITargetEntity;
import com.aehooo.tdsqn.entity.IUpdatable;
import com.aehooo.tdsqn.entity.action.Action;
import com.aehooo.tdsqn.entity.group.Group;
import com.aehooo.tdsqn.enums.GameTargetType;
import com.aehooo.tdsqn.manager.LevelManager;
import com.aehooo.tdsqn.utils.Vector2D;

public abstract class ActionEntity extends GameEntity implements ILiveEntity,
		IUpdatable {

	private double cd;
	private double aps;
	private int range;

	public ActionEntity(final Scene fScene, final float pX, final float pY)
			throws Exception {
		this(fScene, new Vector2D(pX, pY));
	}

	public ActionEntity(final Scene fScene, final Vector2D pos)
			throws Exception {
		super(fScene, pos);
		this.cd = 0;
		this.range = 200;
		APS aps = this.getClass().getAnnotation(APS.class);
		if (aps != null) {
			this.aps = aps.value();
		} else {
			this.aps = 1;
		}
	}

	public List<? extends ITargetEntity> getPossibleTargets(
			final GameTargetType type) {
		List<ITargetEntity> pTargets = new ArrayList<ITargetEntity>();

		if (type == GameTargetType.SELF) {
			pTargets.add(this);
		} else if ((type == GameTargetType.MYGROUP)
				|| (type == GameTargetType.MYGROUPUNITS)) {

		} else if ((type == GameTargetType.GROUP)
				|| (type == GameTargetType.UNIT)) {
			List<Group> pGroups = new ArrayList<Group>();
			List<Group> grupos = LevelManager.getCurrentLevelScene()
					.getGroups();

			for (Group g : grupos) {
				if (Vector2D.dist(this.getCenter(), g.getCenter()) < this.range) {
					pGroups.add(g);
				}
			}

			if (type == GameTargetType.GROUP) {
				return pGroups;
			}

			// Entao eh UNIT
			for (Group g : grupos) {
				pTargets.addAll(g.getUnits());
			}
		} else if (type == GameTargetType.TOWER) {

		}

		return pTargets;
	}

	public boolean wrapperDoAction() throws Exception {
		Method[] declaredMethods = this.getClass().getDeclaredMethods();
		Method filter = null;
		Method sort = null;
		Method buildAction = null;
		GameTargetType type = null;
		for (Method method : declaredMethods) {
			if (method.getAnnotation(FilterTargets.class) != null) {
				filter = method;
			}
			if (method.getAnnotation(SortTargets.class) != null) {
				sort = method;
			}
			BuildAction baAnn = method.getAnnotation(BuildAction.class);
			if (baAnn != null) {
				buildAction = method;
				type = baAnn.targetType();
			}
		}
		if (buildAction == null) {
			return false;
		}
		List<? extends ITargetEntity> possibleTargets = this
				.getPossibleTargets(type);

		if (!possibleTargets.isEmpty()) {
			Action action = new Action(LevelManager.getCurrentLevelScene(),
					this, possibleTargets.get(0));
			buildAction.invoke(this, action);

			action.assembly();
			return true;
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
		} else {
			try {
				if (this.wrapperDoAction()) {
					this.cooldown();
				}
			} catch (Exception e) {
				e.printStackTrace();
				Log.e("ActionEntity", "wrapperDoAction lancou Exception", e);
			}
		}
	}

}

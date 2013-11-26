package com.aehooo.tdsqn.entity.impl;

import java.util.ArrayList;
import java.util.List;

import com.aehooo.tdsqn.entity.IUpdatable;
import com.aehooo.tdsqn.manager.LevelManager;

public class ListOfEntity<E extends GameEntity> implements
		IUpdatable {
	private List<E> list;

	public ListOfEntity() {
		this.list = new ArrayList<E>();
		LevelManager.getUpdateManager().addUpdatable(this);
	}

	public void addEntity(final E e) {
		LevelManager.attachOnGameWindow(e);
		this.list.add(e);
	}

	public void removeEntity(final E e) {
		e.getSprite().detachSelf();
		this.list.remove(e);
	}

	public List<E> getList() {
		return this.list;
	}

	@Override
	public void onFrameUpdate() {
		List<E> toRemove = new ArrayList<E>();
		for (E e : this.list) {
			e.onFrameUpdate();
			if (e.isDead()) {
				toRemove.add(e);
			}
		}
		for (E e : toRemove) {
			this.removeEntity(e);
		}
	}
}

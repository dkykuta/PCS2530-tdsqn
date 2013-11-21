package com.aehooo.tdsqn.scenes;

import java.util.List;

import org.andengine.entity.scene.Scene;

import com.aehooo.tdsqn.entity.GameEntity;

public class GameScene extends Scene {
	private List<GameEntity> entities;

	public void removeEntity(final GameEntity e) {
		// this.detachChild(e.getSprite());
		this.entities.remove(e);
	}

	public void addEntity(final GameEntity e) {
		this.entities.add(e);
		// this.attachChild(e.getSprite());
	}

}

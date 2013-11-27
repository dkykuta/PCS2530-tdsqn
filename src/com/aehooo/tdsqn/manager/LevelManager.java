package com.aehooo.tdsqn.manager;

import org.andengine.entity.Entity;

import com.aehooo.tdsqn.entity.impl.GameEntity;
import com.aehooo.tdsqn.scenes.LevelScene;

public class LevelManager {
	private static LevelScene currentLevel;
	private static UpdateManager updateManager;
	private static boolean gameEnd = false;
	private static boolean winner = false;

	public static void changeLevelScene(final LevelScene curr) {
		LevelManager.currentLevel = curr;
	}

	public static void attachOnScreen(final Entity entity) {
		LevelManager.currentLevel.attachChild(entity);
	}

	public static void attachOnGameWindow(final GameEntity entity) {
		LevelManager.currentLevel.getGameWindow().attachChild(
				entity.getSprite());
	}

	public static void attachOnSideBar(final GameEntity entity) {
		LevelManager.currentLevel.getSidebar().attachChild(entity.getSprite());
	}

	public static LevelScene getCurrentLevelScene() {
		return LevelManager.currentLevel;
	}

	public static UpdateManager getUpdateManager() {
		return LevelManager.updateManager;
	}

	public static void setUpdateManager(final UpdateManager updateManager) {
		LevelManager.updateManager = updateManager;
	}

	public static void setWinner(final boolean b) {
		winner = b;
	}

	public static boolean isWinner() {
		return winner;
	}

	public static void setGameEnd(final boolean b) {
		gameEnd = b;
	}

	public static boolean isGameEnd() {
		return gameEnd;
	}

}

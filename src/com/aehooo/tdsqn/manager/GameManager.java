package com.aehooo.tdsqn.manager;

import org.andengine.ui.activity.SimpleBaseGameActivity;

public class GameManager {
	private static SimpleBaseGameActivity bga;

	public static void registerMainActivity(final SimpleBaseGameActivity bga) {
		GameManager.bga = bga;
	}

	public static void exit() {
		bga.finish();
	}

}

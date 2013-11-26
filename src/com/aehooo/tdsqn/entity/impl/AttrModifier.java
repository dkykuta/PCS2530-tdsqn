package com.aehooo.tdsqn.entity.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.aehooo.tdsqn.entity.ILiveEntity;
import com.aehooo.tdsqn.entity.IUpdatable;

public class AttrModifier implements IUpdatable {

	public Map<ILiveEntity, AttrModifierPart> modifiers;

	public AttrModifier() {
		this.modifiers = new HashMap<ILiveEntity, AttrModifier.AttrModifierPart>();
	}

	public void put(final ILiveEntity origin, final double pctg,
			final int framesDur) {
		AttrModifierPart part = this.modifiers.get(origin);
		if (part == null) {
			part = new AttrModifierPart(pctg, framesDur);
			this.modifiers.put(origin, part);
		} else {
			part.updateInfo(pctg, framesDur);
		}
	}

	public double getPctgTotal() {
		double total = 1.0;
		for (AttrModifierPart part : this.modifiers.values()) {
			total *= part.pctg;
		}

		return total;
	}

	@Override
	public void onFrameUpdate() {
		List<ILiveEntity> toRemove = new ArrayList<ILiveEntity>();
		for (Entry<ILiveEntity, AttrModifierPart> entry : this.modifiers
				.entrySet()) {
			AttrModifierPart part = entry.getValue();
			part.onFrameUpdate();
			if (part.dead) {
				toRemove.add(entry.getKey());
			}
		}
		for (ILiveEntity e : toRemove) {
			this.modifiers.remove(e);
		}

	}

	@Override
	public void onCheckDead() {
		List<ILiveEntity> toRemove = new ArrayList<ILiveEntity>();
		for (Entry<ILiveEntity, AttrModifierPart> entry : this.modifiers
				.entrySet()) {
			AttrModifierPart part = entry.getValue();
			if (part.dead) {
				toRemove.add(entry.getKey());
			}
		}
		for (ILiveEntity e : toRemove) {
			this.modifiers.remove(e);
		}
	}

	private class AttrModifierPart implements IUpdatable {
		private int frames;
		private double pctg;
		private boolean dead;

		public AttrModifierPart(final double pctg, final int framesDur) {
			this.frames = framesDur;
			this.pctg = pctg;
			this.dead = false;
		}

		public void updateInfo(final double pctg, final int framesDur) {
			this.pctg = pctg;
			if (this.frames < framesDur) {
				this.frames = framesDur;
			}
		}

		@Override
		public void onFrameUpdate() {
			this.frames--;
			if (this.frames <= 0) {
				this.dead = true;
			}
		}

		@Override
		public void onCheckDead() {
		}
	}
}

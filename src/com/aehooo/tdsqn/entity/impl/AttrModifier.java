package com.aehooo.tdsqn.entity.impl;

import java.util.HashMap;
import java.util.Map;

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
			part.update(pctg, framesDur);
		}
	}

	@Override
	public void onFrameUpdate() {
	}

	private class AttrModifierPart {
		private int frames;
		private double pctg;

		public AttrModifierPart(final double pctg, final int framesDur) {
			this.frames = framesDur;
			this.pctg = pctg;
		}

		public void update(final double pctg, final int framesDur) {
			this.pctg = pctg;
			if (this.frames < framesDur) {
				this.frames = framesDur;
			}
		}
	}
}

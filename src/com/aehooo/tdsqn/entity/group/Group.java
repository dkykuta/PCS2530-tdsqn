package com.aehooo.tdsqn.entity.group;

import com.aehooo.tdsqn.annotations.TextureInfo;
import com.aehooo.tdsqn.entity.GameEntity;
import com.aehooo.tdsqn.resources.TextureName;
import com.aehooo.tdsqn.utils.Vector2D;

@TextureInfo(name = TextureName.GROUP)
public class Group extends GameEntity {

	public Group(final Vector2D pos) throws Exception {
		super(pos);
	}

	public Group(final float pX, final float pY) throws Exception {
		this(new Vector2D(pX, pY));
	}
}

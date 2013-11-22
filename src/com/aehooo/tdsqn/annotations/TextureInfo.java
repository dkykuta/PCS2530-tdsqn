package com.aehooo.tdsqn.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.aehooo.tdsqn.resources.TextureName;

@Retention(RetentionPolicy.RUNTIME)
public @interface TextureInfo {
	public TextureName name();

	public String[] linhas();
}

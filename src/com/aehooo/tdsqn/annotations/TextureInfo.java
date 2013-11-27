package com.aehooo.tdsqn.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.aehooo.tdsqn.resources.TextureName;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface TextureInfo {
	public TextureName name();

	public int linhas() default 1;
}

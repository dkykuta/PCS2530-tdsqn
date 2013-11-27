package com.aehooo.tdsqn.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.aehooo.tdsqn.enums.GameTargetType;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface BuildAction {
	public GameTargetType targetType();

	public boolean AoE() default false;

	public boolean instant() default false;
}

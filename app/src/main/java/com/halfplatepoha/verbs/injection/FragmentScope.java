package com.halfplatepoha.verbs.injection;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by surjo on 16/11/17.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface FragmentScope {}

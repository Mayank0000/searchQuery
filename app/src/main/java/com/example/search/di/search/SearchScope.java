package com.example.search.di.search;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

@Scope
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
public @interface SearchScope {
}
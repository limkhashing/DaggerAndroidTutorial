package com.limkhashing.daggerandroidtutorial.di.scope

import javax.inject.Scope


/**
 * AuthScope is strictly for login and registration
 *
 * @see javax.inject.Scope @Scope
 */
@Scope
@MustBeDocumented
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class AuthScope

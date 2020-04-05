package com.limkhashing.daggerandroidtutorial.di.scope



import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

@MustBeDocumented
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class AuthViewModelKey(val value: KClass<out ViewModel>)

//import java.lang.annotation.Documented
//import java.lang.annotation.ElementType
//import java.lang.annotation.Retention
//import java.lang.annotation.RetentionPolicy
//import java.lang.annotation.Target
//@MapKey
//@Documented
//@Target(ElementType.METHOD)
//@Retention(RetentionPolicy.RUNTIME)
//annotation class AuthViewModelKey(val value: KClass<out ViewModel>)
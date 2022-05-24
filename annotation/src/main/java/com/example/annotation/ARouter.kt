package com.example.annotation


@Target(AnnotationTarget.CLASS) //作用域在类上
@Retention(AnnotationRetention.SOURCE) //编译期生效
annotation class ARouter (val path : String,val group : String = "")
package com.example.annotation_processor

import com.example.annotation.ARouter
import com.google.auto.service.AutoService
import com.squareup.javapoet.JavaFile
import com.squareup.javapoet.MethodSpec
import com.squareup.javapoet.TypeSpec
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.Modifier
import javax.lang.model.element.TypeElement
import javax.lang.model.util.Elements
import javax.lang.model.util.Types
import javax.tools.Diagnostic


/**
 * 注解处理器
 */
@AutoService(Processor::class) //编译期绑定
@SupportedAnnotationTypes("com.example.annotation.ARouter") //处理对象
@SupportedSourceVersion(SourceVersion.RELEASE_8) //java8
class ARouterProcessor : AbstractProcessor(){
    private lateinit var elementsTool : Elements //操作Element的工具类，函数/类/属性都是Element
    private lateinit var typesTool : Types //类信息的工具类
    private lateinit var messager : Messager //编译期打印东西
    private lateinit var filer : Filer //java文件生成器

    override fun init(processingEnv: ProcessingEnvironment?) {
        super.init(processingEnv)
        elementsTool = processingEnv!!.elementUtils
        typesTool = processingEnv.typeUtils
        messager = processingEnv.messager
        filer = processingEnv.filer
    }

    override fun process(p0: MutableSet<out TypeElement>?, p1: RoundEnvironment?): Boolean {
        if(p0 == null || p1 == null){
            return false
        }
        if(0 == p0.size){
            messager.printMessage(Diagnostic.Kind.NOTE,"没有被ARouter处理的类")
            return false//处理器没有工作
        }
        //获得被ARouter处理的信息
        val elements = p1.getElementsAnnotatedWith(ARouter::class.java)
        for(e in elements){
            //获取类节点与包节点
            val packageElement = elementsTool.getPackageOf(e)
            val name = packageElement.simpleName
            messager.printMessage(Diagnostic.Kind.NOTE, "package name = $name")

            //获得全类名
            val className = e.simpleName
            messager.printMessage(Diagnostic.Kind.NOTE,"被ARouter注解的类有： $className")
            //val aRouter = e.getAnnotation(ARouter::class.java)

            //生成一段代码
            generateHelloWorld()
        }
        return false
    }

    private fun generateHelloWorld() {
        val main = MethodSpec.methodBuilder("main")
            .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
            .returns(Void.TYPE)
            .addParameter(Array<String>::class.java, "args")
            .addStatement("\$T.out.println(\$S)", System::class.java, "Hello, JavaPoet!")
            .build()

        val helloWorld = TypeSpec.classBuilder("HelloWorld")
            .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
            .addMethod(main)
            .build()

        val javaFile = JavaFile.builder("com.example.helloworld", helloWorld)
            .build()

        javaFile.writeTo(filer)
    }
}
package com.example.moduledemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.annotation.ARouter
import com.example.oldermainactivity.OrderMainActivity
import com.example.personal.PersonalMainActivity

@ARouter("/app/MainActivity")
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //错误写法： 显示调转增大了类与类之间的耦合程度
        //模块化中  同一层级的模块不能互相调用，只能依赖下层组件
        //解决方法： 可以使用路由来降低耦合
        findViewById<Button>(R.id.goOrder).setOnClickListener {
            startActivity(Intent(this,OrderMainActivity::class.java))
        }

        findViewById<Button>(R.id.goPersonal).setOnClickListener {
            startActivity(Intent(this,PersonalMainActivity::class.java))
        }
    }
}
package com.fhypayaso.account

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 *
 * @author fanhongyu
 * @since 2019/5/8 1:50 AM
 */
class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, LoginActivity.javaClass))
        }
    }
}
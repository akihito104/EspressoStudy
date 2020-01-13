package com.freshdigitable.espressostudy

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.button
import kotlinx.android.synthetic.main.activity_main.textView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.rotation = intent.getFloatExtra("rotate", 0f)

        button.setOnClickListener {
            textView.visibility = View.VISIBLE
        }
    }

    companion object {
        fun getIntent(context: Context, rotate: Float): Intent {
            return Intent(context, MainActivity::class.java).apply {
                putExtra("rotate", rotate)
            }
        }
    }
}

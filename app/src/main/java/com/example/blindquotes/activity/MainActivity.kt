package com.example.blindquotes.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.blindquotes.R
import com.example.blindquotes.fragment.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }
    }
}
package com.gusakov.pulsecountdownview

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startBtn.setOnClickListener {
            pulseCountDown.start {
                Toast.makeText(this, "Bammmm!!!", Toast.LENGTH_LONG).show()
            }
        }
    }
}

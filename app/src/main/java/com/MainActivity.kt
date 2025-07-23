package com.yugpatel.mirrorcastlite

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val castButton: Button = findViewById(R.id.btn_cast)

        castButton.setOnClickListener {
            try {
                val intent = Intent(Settings.ACTION_CAST_SETTINGS)
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(this, "Casting not supported on this device.", Toast.LENGTH_LONG).show()
            }
        }
    }
}

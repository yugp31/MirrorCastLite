package com.yug.mirrorcastlite

import android.content.Context
import android.content.Intent
import android.media.MediaRouter
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var startCastingButton: Button
    private lateinit var mediaRouter: MediaRouter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startCastingButton = findViewById(R.id.start_casting_button)
        mediaRouter = getSystemService(Context.MEDIA_ROUTER_SERVICE) as MediaRouter

        startCastingButton.setOnClickListener {
            startCasting()
        }
    }

    private fun startCasting() {
        if (isCastingSupported()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                startActivity(Intent(Settings.ACTION_CAST_SETTINGS))
            } else {
                // For older Android versions, try to find a route
                val route = mediaRouter.selectedRoute
                if (route != null && route.supportsCategories(MediaRouter.ROUTE_CATEGORY_LIVE_VIDEO)) {
                    // This part might require more specific implementation depending on the device and Android version
                    // and may not work universally. The ACTION_CAST_SETTINGS is the preferred way for newer devices.
                    Toast.makeText(this, "Attempting to cast...", Toast.LENGTH_SHORT).show()
                    // You might need to programmatically select the route here if ACTION_CAST_SETTINGS is not available
                    // This is a complex area and often requires System APIs or specific vendor implementations.
                } else {
                    Toast.makeText(this, "No casting routes found.", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            showCastingNotSupportedToast()
        }
    }

    private fun isCastingSupported(): Boolean {
        // A basic check. More robust checks might involve
        // scanning for available routes.
        return mediaRouter.isRouteAvailable(MediaRouter.ROUTE_TYPE_LIVE_VIDEO, 0)
    }

    private fun showCastingNotSupportedToast() {
        Toast.makeText(this, "Casting not supported on this device.", Toast.LENGTH_SHORT).show()
    }
}

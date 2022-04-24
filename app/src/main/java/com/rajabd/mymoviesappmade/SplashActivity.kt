package com.rajabd.mymoviesappmade

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.rajabd.core.utils.startActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    companion object {
        private const val DELAY_MOVE = 1200L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        lifecycleScope.launch {
            moveToHome()
        }
    }

    private suspend fun moveToHome() {
        lifecycleScope.launch {
            delay(DELAY_MOVE)
            withContext(Dispatchers.Main) {
                startActivity<MainActivity>()
                finish()
            }
        }
    }
}
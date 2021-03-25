package com.example.fundamentalandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.fundamentalandroid.databinding.ActivityMainBinding
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val executor = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener {
            executor.execute {
                try {
                    //simulate process in background thread
                    for (i in 0..10) {
                        Thread.sleep(500)
                        val percentage = i * 10
                        handler.post {
                            //update ui in main thread
                            if (percentage == 100) {
                                binding.tvStatus.setText(R.string.task_completed)
                            } else {
                                binding.tvStatus.text = String.format(getString(R.string.compressing), percentage)
                            }
                        }
                    }
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }
    }
}
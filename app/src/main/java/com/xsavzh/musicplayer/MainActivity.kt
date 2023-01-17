package com.xsavzh.musicplayer

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xsavzh.musicplayer.databinding.ActivityMainBinding
import java.util.*
import kotlin.concurrent.timerTask

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var mediaPlayer = MediaPlayer()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        mediaPlayer = MediaPlayer.create(applicationContext, R.raw.burzum)

        seekbar()
        buttonActions()
    }

    private fun seekbar()  = with(binding) {
        seekbar.max = mediaPlayer.duration

        Timer().scheduleAtFixedRate(timerTask {
            seekbar.progress = mediaPlayer.currentPosition
        }, 0, 1)
    }

     private fun buttonActions() = with(binding) {
         skipPreviousImageButton.setOnClickListener {

         }

         skipNextImageButton.setOnClickListener {

         }

         playImageButton.setOnClickListener {
             if (mediaPlayer.isPlaying) {
                 mediaPlayer.pause()
                 playImageButton.setImageResource(R.drawable.ic_baseline_play_arrow_75)
             } else {
                 mediaPlayer.start()
                 playImageButton.setImageResource(R.drawable.ic_baseline_pause_75)
             }
         }
     }
}
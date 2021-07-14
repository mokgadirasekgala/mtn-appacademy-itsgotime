package co.za.mtn.academy.itsgotime

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class AudioPlayActivity : AppCompatActivity(){
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_audio_play)
        val play = findViewById<Button>(R.id.playSound)

        mediaPlayer = MediaPlayer.create(this, R.raw.sound)

        play.setOnClickListener {
            if(mediaPlayer.isPlaying) {
                mediaPlayer.pause()
                play.text = "Play"
            } else {
                mediaPlayer.start()
                play.text = "Stop"
            }
        }
    }
}
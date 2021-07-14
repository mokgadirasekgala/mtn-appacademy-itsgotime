package co.za.mtn.academy.itsgotime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.birjuvachhani.locus.Locus

class LocationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)
        val location = findViewById<TextView>(R.id.location)

        Locus.startLocationUpdates(this) { result ->
            result.location?.let {
                location.text = result.location.toString()
            }
            result.error?.let {
                location.text = result.error.toString()
            }
        }
    }
}
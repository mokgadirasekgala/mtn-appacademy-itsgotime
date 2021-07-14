package co.za.mtn.academy.itsgotime

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import co.za.mtn.academy.itsgotime.core.model.User
import com.squareup.picasso.Picasso

class UserDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)

        // Link text view to code
        val textView = findViewById<TextView>(R.id.textView)
        val imageView = findViewById<ImageView>(R.id.profileImage)

        val takePicture = findViewById<Button>(R.id.takePicture)
        val shareProfile = findViewById<Button>(R.id.shareProfile)
        val playSound = findViewById<Button>(R.id.playSound)
        val getLocation = findViewById<Button>(R.id.getLocation)

        // get data
        val user = intent.getParcelableExtra<User>("User")

        // add user name to text view
        textView.text = user?.name
        // add user profile using picasso
        Picasso.get().load(user?.profileUrl).into(imageView)

        // set onclick listeners
        takePicture.setOnClickListener {
            val intent = Intent(this, TakePictureActivity::class.java)
            startActivity(intent)
        }

        shareProfile.setOnClickListener {
            if (user != null) {
                this.shareProfile(user)
            }
        }

        playSound.setOnClickListener {
            val intent = Intent(this, AudioPlayActivity::class.java)
            startActivity(intent)
        }

        getLocation.setOnClickListener {
            val intent = Intent(this, LocationActivity::class.java)
            startActivity(intent)
        }
    }

    fun shareProfile (user: User) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, user.name)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }
}
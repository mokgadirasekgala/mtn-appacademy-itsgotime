package co.za.mtn.academy.itsgotime

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.za.mtn.academy.itsgotime.core.adapter.UserAdapter
import co.za.mtn.academy.itsgotime.core.model.User

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // link recyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        // create a layout manager
        recyclerView.layoutManager = LinearLayoutManager(this)

        // create array or list adapter
        val users = ArrayList<User>()

        // add elements to array
        users.add(User("Mukondleteri 1", "UI Designer", "https://www.xitsonga.org/assets/images/Mukondleteri.jpg"))
        users.add(User("Mukondleteri 2", "Software Developer", "https://www.xitsonga.org/assets/images/Mukondleteri.jpg"))
        users.add(User("Mukondleteri 3", "Test Engineer", "https://www.xitsonga.org/assets/images/Mukondleteri.jpg"))

        // create an assign adapter
        val adapter = UserAdapter(users)
        recyclerView.adapter = adapter

        // add on click for elements
        adapter.onItemClick = { user ->

            val intent = Intent(this, UserDetailsActivity::class.java)
            intent.putExtra("User", user)
            startActivity(intent)
        }
    }
}
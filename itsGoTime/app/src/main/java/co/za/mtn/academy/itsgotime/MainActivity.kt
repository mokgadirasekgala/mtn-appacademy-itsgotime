package co.za.mtn.academy.itsgotime

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.za.mtn.academy.itsgotime.core.adapter.UserAdapter
import co.za.mtn.academy.itsgotime.core.model.User
import com.google.firebase.firestore.FirebaseFirestore


class MainActivity : AppCompatActivity() {

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    private lateinit var adapter: UserAdapter
    private lateinit var recyclerView: RecyclerView
    val facilitators = mutableListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)

        // create a layout manager
        recyclerView.layoutManager = LinearLayoutManager(this)

        val firebaseFirestore = FirebaseFirestore.getInstance()
        firebaseFirestore.collection("facilitators")
            .get()
            .addOnSuccessListener { documents ->
                if (documents != null) {
                    for (document in documents) {
                        Log.d(TAG, "${document.id} => ${document.data.getValue("first_name")}")
                        val facilitatorName = "${document.data.getValue("first_name")} ${document.data.getValue("last_name")}"
                        val user = User(
                            id = document.id,
                            name = facilitatorName,
                            roleName = document.data.getValue("role").toString(),
                            profileUrl = document.data.getValue("profile_pic").toString()
                        )

                        facilitators.add(user)
                    }

                    if (facilitators.isNotEmpty()) {
                        setupRecyclerView(facilitators)
                    }
                } else {
                    Log.d(TAG, "facilitators is empty")
                }
            }.addOnFailureListener { exception ->
                Log.d(TAG, "get failed with $exception")
            }
    }

    private fun setupRecyclerView(users: List<User>) {
        adapter = UserAdapter(users)
        recyclerView.adapter = adapter

        // add on click for elements
        adapter.onItemClick = { user ->

            val intent = Intent(this, UserDetailsActivity::class.java)
            intent.putExtra("User", user)
            startActivity(intent)
        }
    }
}
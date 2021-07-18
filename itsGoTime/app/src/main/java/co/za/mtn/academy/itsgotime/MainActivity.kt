package co.za.mtn.academy.itsgotime

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.za.mtn.academy.itsgotime.core.adapter.UserAdapter
import co.za.mtn.academy.itsgotime.core.api.APIService
import co.za.mtn.academy.itsgotime.core.api.RetrofitClient
import co.za.mtn.academy.itsgotime.core.extensions.showErrorMessage
import co.za.mtn.academy.itsgotime.core.extensions.toast
import co.za.mtn.academy.itsgotime.core.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class MainActivity : AppCompatActivity() {

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    private lateinit var adapter: UserAdapter
    private lateinit var recyclerView: RecyclerView
    private var facilitators: List<User> = mutableListOf()

    private val itsGoTimeAPIService: APIService by lazy {
        RetrofitClient.apiService
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // link recyclerView
        recyclerView = findViewById(R.id.recyclerView)

        // create a layout manager
        recyclerView.layoutManager = LinearLayoutManager(this)

        //fetch data from the backend server
        fetchFacilitators()

        /*
        // create array or list adapter
        val users = ArrayList<User>()

        // add elements to array
        users.add(User(1,"Mukondleteri 1", "UI Designer", "https://www.xitsonga.org/assets/images/Mukondleteri.jpg"))
        users.add(User(2,"Mukondleteri 2", "Software Developer", "https://www.xitsonga.org/assets/images/Mukondleteri.jpg"))
        users.add(User(3, "Mukondleteri 3", "Test Engineer", "https://www.xitsonga.org/assets/images/Mukondleteri.jpg"))

        // create an assign adapter
        adapter = UserAdapter(users)
        recyclerView.adapter = adapter

        // add on click for elements
        adapter.onItemClick = { user ->

            val intent = Intent(this, UserDetailsActivity::class.java)
            intent.putExtra("User", user)
            startActivity(intent)
        }
        */
    }

    private fun fetchFacilitators() {
        itsGoTimeAPIService.getAllFacilitators().enqueue(object : Callback<List<User>> {

            override fun onResponse(call: Call<List<User>>?, response: Response<List<User>>) {
                if (response.isSuccessful) {
                    Log.i(TAG, "facilitators loaded from API $response")

                    response.body()?.let {
                        facilitators = it
                    }

                    if (facilitators.isNotEmpty())
                        setupRecyclerView(facilitators)
                    else
                        toast("No Items Found")

                } else {
                    Log.i(TAG, "error $response")
                    showErrorMessage(response.errorBody()!!)
                }
            }

            override fun onFailure(call: Call<List<User>>?, t: Throwable) {
                toast(t.message ?: "Error Fetching Results")
            }

        })
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
package co.za.mtn.academy.itsgotime.core.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.za.mtn.academy.itsgotime.R
import co.za.mtn.academy.itsgotime.core.model.User

class UserAdapter(val users: List<User>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    var onItemClick: ((User) -> Unit)? = null

    // this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_users_layout_item, parent, false)
        return ViewHolder(v)
    }

    // this method is binding the data on the list
    override fun onBindViewHolder(holder: UserAdapter.ViewHolder, position: Int) {
        holder.bindItems(users[position])
    }

    // this method is returning the size of the list
    override fun getItemCount(): Int {
        return users.size
    }

    // the class is holding the listview
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // execute
        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(users[adapterPosition])
            }
        }

        fun bindItems(user: User) {
            val textViewName = itemView.findViewById(R.id.userName) as TextView
            val textViewRole  = itemView.findViewById(R.id.userRole) as TextView
            textViewName.text = user.name
            textViewRole.text = user.roleName
        }
    }
}
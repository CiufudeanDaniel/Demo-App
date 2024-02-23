package com.example.raiffeisentest.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.example.raiffeisentest.R
import com.example.raiffeisentest.databinding.ItemUserLayoutBinding
import com.example.raiffeisentest.models.User
import kotlin.collections.ArrayList

private const val TAG = "recycler_view_adapter"
class RecyclerViewAdapter(private val mUsers: ArrayList<User>) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d(TAG, "onCreateViewHolder: called.")
        val binding = ItemUserLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder: called.")
        holder.bind(user = mUsers[position])
    }

    override fun getItemCount(): Int = mUsers.size
}


class ViewHolder(private val itemBinding: ItemUserLayoutBinding) : RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(user: User) {
        val glideUrl = GlideUrl(user.picture.thumbnail, LazyHeaders.Builder()
            .addHeader("User-Agent",
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_2) AppleWebKit / 537.36(KHTML, like Gecko) Chrome  47.0.2526.106 Safari / 537.36")
            .build())

        Glide.with(itemBinding.image.context)
            .load("")
            .load(glideUrl)
            .placeholder(R.drawable.ic_launcher_background)
            .into(itemBinding.image)

        itemBinding.user = user
    }
}
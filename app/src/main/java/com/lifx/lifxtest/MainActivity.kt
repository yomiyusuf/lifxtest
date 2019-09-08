package com.lifx.lifxtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import android.os.AsyncTask
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.squareup.picasso.Picasso
import org.json.JSONObject


class MainActivity : AppCompatActivity() {

    lateinit var adapter: ListAdapter
    val listData = mutableListOf<ListItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val download = DownloadFileFromURL()
        download.execute("https://cloud.lifx.com/themes/v1/curated")

        adapter = ListAdapter(listData)
        list.adapter = adapter
        list.layoutManager = LinearLayoutManager(applicationContext)

        val dividerItemDecoration = DividerItemDecoration(this, VERTICAL)
        list.addItemDecoration(dividerItemDecoration)
    }

    class ListAdapter(private val listData: List<ListItem>): RecyclerView.Adapter<ListAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_row, parent, false))
        }

        override fun getItemCount(): Int = listData.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = listData[position]
            holder.blah.text = item.title
            Picasso.get()
                .load(item.imageUrl)
                .into(holder.image)
        }

        inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
            val blah: TextView = itemView.findViewById(R.id.textView)
            val image: ImageView = itemView.findViewById(R.id.imageView)
        }

    }

    inner class DownloadFileFromURL : AsyncTask<String, String, String>() {

        override fun doInBackground(vararg url: String): String? {
            val results =
                JsonParser().makeHttpRequest(url[0])

            runOnUiThread {

                for (i in 0 until (results?.length() ?: 0)) {
                    val result = results?.get(i) as JSONObject

                    listData.add(ListItem(result.getString("title"), result.getString("image_url")))
                }
                adapter.notifyDataSetChanged()
            }

            return null
        }
    }
}



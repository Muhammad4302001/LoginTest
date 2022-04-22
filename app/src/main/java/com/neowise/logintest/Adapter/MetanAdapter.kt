package com.neowise.logintest.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.neowise.logintest.R
import com.neowise.logintest.api.model.Metan

class MetanAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private var items: List<Metan> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MetanViewHolder {
        return MetanViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.metan_item,parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(blogList: List<Metan>){
        items = blogList
    }


    class MetanViewHolder constructor(
        itemView: View
    ): RecyclerView.ViewHolder(itemView){


        var headtitle = itemView.findViewById<TextView>(R.id.metan_header_title)
        var metanPrice = itemView.findViewById<TextView>(R.id.metan_sum)
        var metanOpenTime = itemView.findViewById<TextView>(R.id.mopentime)
        var mCloseTime = itemView.findViewById<TextView>(R.id.mclosetime)
        var imageMetan = itemView.findViewById<ImageView>(R.id.mImageView)
        var orientr = itemView.findViewById<TextView>(R.id.mOrienter)


        fun bind(blogPost: Metan){

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(blogPost.image)
                .into(imageMetan)
            headtitle.setText(blogPost.name)
            metanPrice.setText(blogPost.price)
            metanOpenTime.setText(blogPost.start_time)
            mCloseTime.setText(blogPost.end_time)
            orientr.setText(blogPost.orientr)

        }

    }
}
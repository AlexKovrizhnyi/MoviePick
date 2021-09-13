package com.axkov.moviepick.app.ui.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.axkov.moviepick.app.R
import com.axkov.moviepick.app.model.ItemPlaceholder
import com.axkov.moviepick.app.model.ListItem
import com.axkov.moviepick.app.model.MovieCategory
import com.axkov.moviepick.app.model.MovieItem
import java.lang.ClassCastException

private const val MOVIE_ITEM = 1
private const val ITEM_PLACEHOLDER = 2
private const val UNKNOWN_TYPE = 404

class MovieAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var data = listOf<ListItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            MOVIE_ITEM -> MovieViewHolder.from(parent)
            ITEM_PLACEHOLDER -> MoviePlaceholderViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MovieViewHolder -> {
                val movieItem = data[position] as MovieItem
                holder.bind(movieItem)
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (data[position]) {
            is MovieItem -> MOVIE_ITEM
            is ItemPlaceholder -> ITEM_PLACEHOLDER
            else -> UNKNOWN_TYPE
        }
    }

    class MovieViewHolder private constructor(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.item_movie_tv_title)
        private val thumbnail: ImageView = itemView.findViewById(R.id.item_movie_iv_poster)

        fun bind(item: MovieItem) {
            title.text = item.title

            // TODO: Download image for MovieItem using Glide
        }

        companion object {
            fun from(parent: ViewGroup): MovieViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.item_movie, parent, false)
                return MovieViewHolder(view)
            }
        }
    }

    class MoviePlaceholderViewHolder private constructor(itemView: View): RecyclerView.ViewHolder(itemView) {

        companion object {
            fun from(parent: ViewGroup): MoviePlaceholderViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.item_movie_placeholder, parent, false)
                return MoviePlaceholderViewHolder(view)
            }
        }
    }
}

//class MovieAdapter: ListAdapter<MovieItem, RecyclerView.ViewHolder>(MovieItemDiffCallback()) {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
////        return when (viewType) {
////            MOVIE_ITEM -> MovieViewHolder.from(parent)
////            else -> throw ClassCastException("Unknown viewType $viewType")
////        }
//        return MovieViewHolder.from(parent)
//    }
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        when (holder) {
//            is MovieViewHolder -> {
//                val item = getItem(position) as MovieItem
//                holder.bind(item)
//            }
//        }
//    }
//
//    class MovieViewHolder private constructor(itemView: View): RecyclerView.ViewHolder(itemView) {
//        private val title: TextView = itemView.findViewById(R.id.item_movie_tv_title)
//        private val thumbnail: ImageView = itemView.findViewById(R.id.item_movie_iv_poster)
//
//        fun bind(item: MovieItem) {
//            title.text = item.title
//
//            // TODO: Download image for MovieItem using Glide
//        }
//
//        companion object {
//            fun from(parent: ViewGroup): MovieViewHolder {
//                val layoutInflater = LayoutInflater.from(parent.context)
//                val view = layoutInflater.inflate(R.layout.item_movie, parent, false)
//                return MovieViewHolder(view)
//            }
//        }
//    }
//}
//
//class MovieItemDiffCallback: DiffUtil.ItemCallback<MovieItem>() {
//    override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
//        return oldItem.id == newItem.id
//    }
//
//    override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
//        return oldItem == newItem
//    }
//}


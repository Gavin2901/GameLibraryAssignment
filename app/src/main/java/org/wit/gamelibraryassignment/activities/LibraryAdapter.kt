package org.wit.gamelibraryassignment.activities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_library.view.*
import org.wit.gamelibraryassignment.R
import org.wit.gamelibraryassignment.helpers.readImageFromPath
import org.wit.gamelibraryassignment.models.LibraryModel

interface LibraryListener {
    fun onLibraryClick(library: LibraryModel)
}

class LibraryAdapter constructor(private var libraries: List<LibraryModel>,
                                   private val listener: LibraryListener) : RecyclerView.Adapter<LibraryAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(LayoutInflater.from(parent?.context).inflate(R.layout.card_library, parent, false))
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val library = libraries[holder.adapterPosition]
        holder.bind(library, listener)
    }

    override fun getItemCount(): Int = libraries.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(library: LibraryModel,  listener : LibraryListener) {
            itemView.gameTitle.text = library.title
            itemView.description.text = library.description
            itemView.imageIcon.setImageBitmap(readImageFromPath(itemView.context, library.image))
            itemView.setOnClickListener { listener.onLibraryClick(library) }
        }
    }
}
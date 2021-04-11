package org.wit.gamelibraryassignment.activities

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_library_list.*
import kotlinx.android.synthetic.main.card_library.view.*
import org.wit.gamelibraryassignment.R
import org.wit.gamelibraryassignment.main.MainApp
import org.wit.gamelibraryassignment.models.LibraryModel
import org.jetbrains.anko.startActivityForResult

class LibraryListActivity : AppCompatActivity() {

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_library_list)
        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = LibraryAdapter(app.libraries)

        toolbar.title = title
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> startActivityForResult<LibraryActivity>(0)
        }
        return super.onOptionsItemSelected(item)
    }
}

class LibraryAdapter constructor(private var libraries: List<LibraryModel>) :
        RecyclerView.Adapter<LibraryAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.card_library,
                        parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val library = libraries[holder.adapterPosition]
        holder.bind(library)
    }

    override fun getItemCount(): Int = libraries.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(library: LibraryModel) {
            itemView.libraryTitle.text = library.title
            itemView.description.text = library.description
        }
    }
}

package org.wit.gamelibraryassignment.activities

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_library_list.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivityForResult
import org.wit.gamelibraryassignment.R
import org.wit.gamelibraryassignment.main.MainApp
import org.wit.gamelibraryassignment.models.LibraryModel


class LibraryListActivity : AppCompatActivity(), LibraryListener {

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_library_list)
        app = application as MainApp

        //layout and populate for display
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager   //recyclerView is a widget in activity_placemark_list.xml
        loadLibrary()

        //enable action bar and set title
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

    override fun onLibraryClick(library: LibraryModel) {
        startActivityForResult(intentFor<LibraryActivity>().putExtra("library_edit", library), 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        loadLibrary()
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun loadLibrary() {
        showLibrarys(app.libraries.findAll())
    }
    fun showLibrarys (libraries: List<LibraryModel>) {
        recyclerView.adapter = LibraryAdapter(libraries  , this)
        recyclerView.adapter?.notifyDataSetChanged()
    }

}


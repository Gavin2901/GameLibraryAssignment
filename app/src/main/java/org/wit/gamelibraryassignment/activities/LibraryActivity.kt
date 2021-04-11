package org.wit.gamelibraryassignment.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_library.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.gamelibraryassignment.R
import org.wit.gamelibraryassignment.main.MainApp
import org.wit.gamelibraryassignment.models.LibraryModel

class LibraryActivity : AppCompatActivity(), AnkoLogger {

    var library = LibraryModel()
    lateinit var app: MainApp
    var edit = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_library)
        app = application as MainApp

        toolbarAdd.title = title
        setSupportActionBar(toolbarAdd)

        if (intent.hasExtra("library_edit")) {
            edit=true
            library= intent.extras?.getParcelable<LibraryModel>("library_edit")!!
            libraryTitle.setText(library.title)
            libraryDescription.setText(library.description)
            btnAdd.setText(R.string.save_library)
        }

        btnAdd.setOnClickListener() {
            library.title = libraryTitle.text.toString()
            library.description = libraryDescription.text.toString()
            if (library.title.isEmpty()) {
                toast(R.string.enter_library_title)
            } else {
                if (edit) {
                    app.libraries.update(library.copy())
                } else {
                    app.libraries.create(library.copy())
                }
            }
            info("add Button Pressed: ${libraryTitle}r")
            setResult(AppCompatActivity.RESULT_OK)
            finish()
        }


        toolbarAdd.title = title
        setSupportActionBar(toolbarAdd)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_library, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
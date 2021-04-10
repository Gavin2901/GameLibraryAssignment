package org.wit.gamelibraryassignment.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_library.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.gamelibraryassignment.R
import org.wit.gamelibraryassignment.main.MainApp
import org.wit.gamelibraryassignment.models.LibraryModel

class LibraryActivity : AppCompatActivity(), AnkoLogger {

    var library = LibraryModel()
    lateinit var app : MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_library)
        app = application as MainApp

        btnAdd.setOnClickListener() {
            library.title = libraryTitle.text.toString()
            library.description = libraryDescription.text.toString()
            if (library.title.isNotEmpty()) {
                app.libraries.add(library.copy())
                info("add Button Pressed: $libraryTitle")
                app.libraries.forEach { info("add Button Pressed: ${it.title}, ${it.description}")}
            }
            else {
                toast ("Please Enter a title")
            }
        }
    }
}
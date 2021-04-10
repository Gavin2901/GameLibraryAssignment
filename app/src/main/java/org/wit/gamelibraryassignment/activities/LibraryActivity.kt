package org.wit.gamelibraryassignment.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.gamelibraryassignment.R
import org.wit.gamelibraryassignment.models.LibraryModel

class LibraryActivity : AppCompatActivity(), AnkoLogger {

    var library = LibraryModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        info("Main Activity started..")


        btnAdd.setOnClickListener() {
            library.title = libraryTitle.text.toString()
            if (library.title.isNotEmpty()) {
                info("add Button Pressed: $library")
            }
            else {
                toast ("Please Enter a title")
            }
        }

    }
    }

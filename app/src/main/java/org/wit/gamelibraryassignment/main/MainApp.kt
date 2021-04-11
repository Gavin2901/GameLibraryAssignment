package org.wit.gamelibraryassignment.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.gamelibraryassignment.models.LibraryMemStore
import org.wit.gamelibraryassignment.models.LibraryModel

class MainApp : Application(), AnkoLogger {



    val libraries = LibraryMemStore()


    override fun onCreate() {
        super.onCreate()
        info("Placemark started")

    }

}
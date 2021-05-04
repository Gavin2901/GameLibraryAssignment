package org.wit.gamelibraryassignment.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.gamelibraryassignment.models.LibraryStore
import org.wit.gamelibraryassignment.models.LibraryGameStore
import org.wit.gamelibraryassignment.models.LibraryJSONStore

class MainApp : Application(), AnkoLogger {

    lateinit var libraries: LibraryStore

    override fun onCreate() {
        super.onCreate()
        libraries = LibraryJSONStore(applicationContext)
        info("Library started")
    }
}
package org.wit.gamelibraryassignment.models

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class LibraryMemStore : LibraryStore, AnkoLogger {

    val libraries = ArrayList<LibraryModel>()

    override fun findAll(): List<LibraryModel> {
        return libraries
    }

    override fun create(library: LibraryModel) {
        library.id = getId()
        libraries.add(library)
        logAll()
    }

    override fun update(placemark: LibraryModel) {
        var foundLibrary: LibraryModel? = libraries.find { p -> p.id == placemark.id }
        if (foundLibrary != null) {
            foundLibrary.title = placemark.title
            foundLibrary.description = placemark.description
            foundLibrary.image = placemark.image
            logAll()
        }
    }

    fun logAll() {
        libraries.forEach { info("${it}") }
    }
}


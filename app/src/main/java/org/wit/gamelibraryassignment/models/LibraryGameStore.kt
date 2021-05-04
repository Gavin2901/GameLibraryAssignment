package org.wit.gamelibraryassignment.models

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class LibraryGameStore : LibraryStore, AnkoLogger {

    val libraries = ArrayList<LibraryModel>()

    override fun findAll(): List<LibraryModel> {
        return libraries
    }

    override fun create(library: LibraryModel) {
        library.id = getId()
        libraries.add(library)
        logAll()
    }



    fun logAll() {
        libraries.forEach { info("${it}") }
    }

    override fun update(library: LibraryModel) {
        var foundLocation: LibraryModel? = libraries.find { p -> p.id == library.id }
        if (foundLocation != null) {
            foundLocation.title = library.title
            foundLocation.description = library.description
            foundLocation.image = library.image
            foundLocation.lat = library.lat
            foundLocation.lng = library.lng
            foundLocation.zoom = library.zoom
            logAll();
        }
    }

    override fun delete(library: LibraryModel) {
        libraries.remove(library)
    }
}


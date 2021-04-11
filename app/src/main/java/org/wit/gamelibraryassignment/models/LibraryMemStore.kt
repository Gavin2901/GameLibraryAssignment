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

    override fun update(library: LibraryModel) {
        var foundLibrary: LibraryModel? = libraries.find { p -> p.id == library.id }
        if (foundLibrary != null) {
            foundLibrary.title = library.title
            foundLibrary.description = library.description
            logAll()
        }
    }

    fun logAll() {
        libraries.forEach { info("${it}") }
    }
}


package org.wit.gamelibraryassignment.models


interface LibraryStore {
    fun findAll(): List<LibraryModel>
    fun create(library: LibraryModel)
    fun update(library: LibraryModel)
}
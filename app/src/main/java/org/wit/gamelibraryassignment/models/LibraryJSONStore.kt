package org.wit.gamelibraryassignment.models

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.AnkoLogger
import org.wit.gamelibraryassignment.helpers.*
import java.util.*

val JSON_FILE = "libraries.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<LibraryModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class LibraryJSONStore : LibraryStore, AnkoLogger {

    val context: Context
    var libraries = mutableListOf<LibraryModel>()

    constructor (context: Context) {
        this.context = context
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<LibraryModel> {
        return libraries
    }

    override fun create(library: LibraryModel) {
        library.id = generateRandomId()
        libraries.add(library)
        serialize()
    }


    override fun update(library: LibraryModel) {
        val librariesList = findAll() as ArrayList<LibraryModel>
        var foundLocation: LibraryModel? = librariesList.find { p -> p.id == library.id }
        if (foundLocation != null) {
            foundLocation.title = library.title
            foundLocation.description = library.description
            foundLocation.image = library.image
            foundLocation.lat = library.lat
            foundLocation.lng = library.lng
            foundLocation.zoom = library.zoom
        }
        serialize()
    }



    override fun delete(library: LibraryModel) {
        libraries.remove(library)
        serialize()
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(libraries, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        libraries = Gson().fromJson(jsonString, listType)
    }


}
package org.wit.gamelibraryassignment.models
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.Context
import android.content.ContentValues


class LibraryDBStore(context: Context, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int)
    : SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION), LibraryStore
{

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_LIBRARY_TABLE = ("CREATE TABLE " +
                TABLE_LIBRARY + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_TITLE + " TEXT," +
                COLUMN_DESCRIPTION + " TEXT," +
                COLUMN_IMAGE + " TEXT," +
                COLUMN_LAT + " DOUBLE," +
                COLUMN_LNG + " DOUBLE," +
                COLUMN_ZOOM + " FLOAT" +
                ")")
        db.execSQL(CREATE_LIBRARY_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_LIBRARY")
        onCreate(db)
    }


    companion object {

        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "libraryDB.db"
        val TABLE_LIBRARY = "libraries"

        val COLUMN_ID = "_id"
        val COLUMN_TITLE = "title"
        val COLUMN_DESCRIPTION = "genre"
        val COLUMN_IMAGE = "image"
        val COLUMN_LAT = "lat"
        val COLUMN_LNG = "lng"
        val COLUMN_ZOOM = "zoom"
    }

    override fun findAll(): List<LibraryModel> {
        val query = "SELECT * FROM $TABLE_LIBRARY"
        val db = this.writableDatabase
        val cursor = db.rawQuery(query, null)

        val libraries = ArrayList<LibraryModel>()

        if (cursor.moveToFirst()) {
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                val id = Integer.parseInt(cursor.getString(0)).toLong()
                val title = cursor.getString(1)
                val description = cursor.getString(2)
                val image = cursor.getString(3)
                val lat = cursor.getDouble(4)
                val lng = cursor.getDouble(5)
                val zoom = cursor.getFloat(6)
                libraries.add(LibraryModel(id, title = title, description = description, image = image, lat = lat, lng = lng, zoom = zoom))
                cursor.moveToNext()
            }
            cursor.close()
        }
        db.close()
        return libraries
    }


    override fun create(movie: LibraryModel) {
        val values = ContentValues()
        values.put(COLUMN_TITLE, movie.title)
        values.put(COLUMN_DESCRIPTION, movie.description)

        values.put(COLUMN_IMAGE, movie.image)
        values.put(COLUMN_LAT, movie.lat)
        values.put(COLUMN_LNG, movie.lng)
        values.put(COLUMN_ZOOM, movie.zoom)

        val db = this.writableDatabase

        db.insert(TABLE_LIBRARY, null, values)
        db.close()
    }


    override fun update(library: LibraryModel) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_ID, library.id)
        values.put(COLUMN_TITLE, library.title)
        values.put(COLUMN_DESCRIPTION, library.description)
        values.put(COLUMN_IMAGE, library.image)
        values.put(COLUMN_LAT, library.lat)
        values.put(COLUMN_LNG, library.lng)
        values.put(COLUMN_ZOOM, library.zoom)

        val updated = db.update(TABLE_LIBRARY, values,"_id="+ library.id,null)
        db.close()

    }


    override fun delete(library: LibraryModel) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_ID, library.id)

        val deleted = db.delete(TABLE_LIBRARY,"_id="+library.id,null)
        db.close()
    }

}
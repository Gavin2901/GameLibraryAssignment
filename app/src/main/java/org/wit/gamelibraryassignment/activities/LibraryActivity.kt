package org.wit.gamelibraryassignment.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_library.*
import kotlinx.android.synthetic.main.card_library.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast
import org.wit.gamelibraryassignment.R
import org.wit.gamelibraryassignment.helpers.readImage
import org.wit.gamelibraryassignment.helpers.readImageFromPath
import org.wit.gamelibraryassignment.helpers.showImagePicker
import org.wit.gamelibraryassignment.main.MainApp
import org.wit.gamelibraryassignment.models.LibraryModel
import org.wit.gamelibraryassignment.models.Location

class LibraryActivity : AppCompatActivity(), AnkoLogger {

    var library = LibraryModel()
    lateinit var app: MainApp
    var edit = false
    val IMAGE_REQUEST = 1
    val LOCATION_REQUEST = 2


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_library)
        app = application as MainApp

        if (intent.hasExtra("library_edit")) {
            edit = true
            library = intent.extras?.getParcelable<LibraryModel>("library_edit")!!
            libraryTitle.setText(library.title)
            libraryDescription.setText(library.description)
            gameImage.setImageBitmap(readImageFromPath(this, library.image))
            if (library.image != null) {
                chooseImage.setText(R.string.change_game_image)
            }
            btnAdd.setText(R.string.save_library)
        }

        developerLocation.setOnClickListener {
            val location = Location(53.0, -7.0, 5f)
            if (library.zoom != 0f) {
                location.lat =  library.lat
                location.lng = library.lng
                location.zoom = library.zoom
            }
            startActivityForResult(intentFor<MapsActivity>().putExtra("location", location), LOCATION_REQUEST)
        }

        btnAdd.setOnClickListener() {
            library.title = libraryTitle.text.toString()
            library.description = libraryDescription.text.toString()
            if (library.title.isEmpty()) {
                toast(R.string.enter_library_title)
            } else {
                if (edit) {
                    app.libraries.update(library.copy())
                } else {
                    app.libraries.create(library.copy())
                }
            }
            info("add Button Pressed: ${libraryTitle}r")
            setResult(AppCompatActivity.RESULT_OK)
            finish()
        }

        toolbarAdd.title = title
        setSupportActionBar(toolbarAdd)

        chooseImage.setOnClickListener {
            showImagePicker(this, IMAGE_REQUEST)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_library, menu)
        if (edit && menu != null) menu.getItem(0).setVisible(true)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.item_delete -> {
                app.libraries.delete(library)
                finish()
            }
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            IMAGE_REQUEST -> {
                if (data != null) {
                    library.image = data.getData().toString()
                    gameImage.setImageBitmap(readImage(this, resultCode, data))
                    chooseImage.setText(R.string.change_game_image)
                }
            }
            LOCATION_REQUEST -> {
                if (data != null) {
                    val location = data.extras?.getParcelable<Location>("location")!!
                    library.lat = location.lat
                    library.lng = location.lng
                    library.zoom = location.zoom
                }
            }
        }
    }

    }
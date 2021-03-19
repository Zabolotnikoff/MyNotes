package com.example.mynotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.room.RoomDatabase
import com.example.mynotes.data.datasource.RoomDatabaseClient
import com.example.mynotes.data.repository.RoomRepository
import com.example.mynotes.domain.entities.Note
import com.example.mynotes.utils.ACTIVITY
import com.example.mynotes.utils.DATABASE_REPOSITORY
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        applicationInitial()
        navController = Navigation.findNavController(this, R.id.navHostFragment)
        setSupportActionBar(toolbar)
    }

    private fun applicationInitial() {
        ACTIVITY = this
        DATABASE_REPOSITORY =
            RoomRepository(RoomDatabaseClient.getInstance(ACTIVITY).getAppRoomDao())
        addFirstNote()
    }

    private fun addFirstNote() {
        CoroutineScope(Dispatchers.IO).launch {
            if (DATABASE_REPOSITORY.getAllNotes().isEmpty())
                DATABASE_REPOSITORY.insert(
                    Note(id = 0, header = "Заголовок заметки", text = "Текст заметки")
                ) { }
        }
    }
}

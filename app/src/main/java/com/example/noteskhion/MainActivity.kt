package com.example.noteskhion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.noteskhion.ui.theme.AllApp.NoteApp
import com.example.noteskhion.ui.theme.NotesKhionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesKhionTheme {
                NoteApp()
            }
        }
    }
}
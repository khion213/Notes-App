package com.example.noteskhion.ui.theme.AllApp

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class NoteViewModel : ViewModel() {
    var notes = mutableStateListOf<Pair<String, String>>()
        private set

    fun addNote(title: String, content: String) {
        notes.add(Pair(title, content))
    }

    fun deleteNote(index: Int) {
        notes.removeAt(index)
    }

    fun editNote(index: Int, title: String, content: String) {
        notes[index] = Pair(title, content)
    }

    fun copyNoteContent(index: Int): String {
        return notes[index].second
    }
}
package com.example.noteskhion.ui.theme.AllApp

import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun NoteApp() {
    val noteViewModel: NoteViewModel = viewModel()
    var isAddingNote by remember { mutableStateOf(false) }

    if (isAddingNote) {
        AddNoteScreen(
            onSave = { title, content ->
                noteViewModel.addNote(title, content)
                isAddingNote = false
            },
            onCancel = { isAddingNote = false }
        )
    } else {
        NoteScreen(
            notes = noteViewModel.notes,
            onAddNoteClick = { isAddingNote = true },
            onDeleteNote = { noteViewModel.deleteNote(it) },
            onEditNote = { index, title, content -> noteViewModel.editNote(index, title, content) },
            onCopyNote = { noteViewModel.copyNoteContent(it) }
        )
    }
}
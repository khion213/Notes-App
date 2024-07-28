package com.example.noteskhion.ui.theme.AllApp


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.noteskhion.ui.theme.noteColors

@Composable
fun NoteScreen(
    notes: List<Pair<String, String>>,
    onAddNoteClick: () -> Unit,
    onDeleteNote: (Int) -> Unit,
    onEditNote: (Int, String, String) -> Unit,
    onCopyNote: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(
            onClick = onAddNoteClick,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(8.dp)
                .size(200.dp, 50.dp)
        ) {
            Text("Add Your Note")
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(notes) { index, note ->
                var showOptions by remember { mutableStateOf(false) }
                if (showOptions) {
                    NoteOptionsDialog(
                        onDelete = { onDeleteNote(index) },
                        onEdit = { onEditNote(index, note.first, note.second) },
                        onCopy = { onCopyNote(index) },
                        onDismiss = { showOptions = false }
                    )
                }
                NoteItem(
                    title = note.first,
                    content = note.second,
                    backgroundColor = noteColors[index % noteColors.size],
                    onLongPress = { showOptions = true }
                )
            }
        }
    }
}

@Composable
fun NoteItem(title: String, content: String, backgroundColor: Color, onLongPress: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor, shape = MaterialTheme.shapes.medium)
            .clickable { }
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = { onLongPress() }
                )
            }
            .padding(16.dp)
    ) {
        Text(text = title, style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = content, style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
fun NoteOptionsDialog(onDelete: () -> Unit, onEdit: () -> Unit, onCopy: () -> Unit, onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier
                .wrapContentSize()
                .padding(16.dp)
        ) {
            Column {
                TextButton(onClick = onDelete) { Text("Delete") }
              //  TextButton(onClick = onCopy) { Text("Copy") }
                // TextButton(onClick = onEdit) { Text("Edit") }
            }
        }
    }
}
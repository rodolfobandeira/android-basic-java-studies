package com.rodolfobandeira.ceep.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.rodolfobandeira.ceep.R;
import com.rodolfobandeira.ceep.dao.NoteDAO;
import com.rodolfobandeira.ceep.model.Note;
import com.rodolfobandeira.ceep.ui.recyclerview.adapter.NotesListAdapter;
import com.rodolfobandeira.ceep.ui.recyclerview.helper.callback.NoteItemTouchHelperCallback;

import java.util.List;

import static com.rodolfobandeira.ceep.ui.activity.NoteActivityConstants.APP_TITLE;
import static com.rodolfobandeira.ceep.ui.activity.NoteActivityConstants.INVALID_POSITION;
import static com.rodolfobandeira.ceep.ui.activity.NoteActivityConstants.NOTE_KEY;
import static com.rodolfobandeira.ceep.ui.activity.NoteActivityConstants.POSITION_KEY;
import static com.rodolfobandeira.ceep.ui.activity.NoteActivityConstants.REQUEST_CODE_INSERT_NOTE;
import static com.rodolfobandeira.ceep.ui.activity.NoteActivityConstants.REQUEST_CODE_UPDATE_NOTE;

public class MainActivity extends AppCompatActivity {
    private NotesListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(APP_TITLE);

        List<Note> allNotes = getAllNotes();
        setupRecyclerView(allNotes);
        setupInsertNoteButton();
    }

    private void setupInsertNoteButton() {
        TextView buttonInsertNote = findViewById(R.id.main_insert_note);
        buttonInsertNote.setOnClickListener(v -> goToInsertNoteForm());
    }

    private void goToInsertNoteForm() {
        Intent startFormActivity =
                new Intent(MainActivity.this, FormNoteActivity.class);
        startActivityForResult(startFormActivity, REQUEST_CODE_INSERT_NOTE);
    }

    private List<Note> getAllNotes() {
        NoteDAO dao = new NoteDAO();
        /*
        for (int i = 1; i <= 3; i++) {
            dao.insert(new Note("Note " + i, "Description " + i));
        }
        */
        return dao.all();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (isValidForNoteCreation(requestCode, resultCode, data)) {
            addNote(data);
        }

        if(isValidForNoteUpdate(requestCode, resultCode, data)) {
            Note receivedNote = (Note) data.getSerializableExtra(NOTE_KEY);
            int receivedPosition = data.getIntExtra(POSITION_KEY, INVALID_POSITION);
            if (isValidPosition(receivedPosition)) {
                updateNote(receivedNote, receivedPosition);
            } else {
                Toast.makeText(
                        this,
                        "Error trying to update this note",
                        Toast.LENGTH_SHORT
                ).show();
            }
        }
    }

    private void updateNote(Note note, int position) {
        new NoteDAO().update(position, note);
        adapter.update(position, note);
    }

    private boolean isValidPosition(int receivedPosition) {
        return receivedPosition > INVALID_POSITION;
    }

    private boolean isValidForNoteUpdate(int requestCode, int resultCode, Intent data) {
        return isUpdateNoteRequestCode(requestCode)
                && isCreatedNoteResultCode(resultCode)
                && hasNote(data);
    }

    private boolean isUpdateNoteRequestCode(int requestCode) {
        return requestCode == REQUEST_CODE_UPDATE_NOTE;
    }

    private void addNote(Intent data) {
        Note note = (Note) data.getSerializableExtra(NOTE_KEY);
        new NoteDAO().insert(note);
        adapter.add(note);
    }

    private boolean isValidForNoteCreation(int requestCode, int resultCode, Intent data) {
        return isRequestInsertNote(requestCode) && isCreatedNoteResultCode(resultCode) && hasNote(data);
    }

    private boolean hasNote(Intent data) {
        return data.hasExtra("note");
    }

    private boolean isCreatedNoteResultCode(int resultCode) {
        return resultCode == Activity.RESULT_OK;
    }

    private boolean isRequestInsertNote(int requestCode) {
        return requestCode == REQUEST_CODE_INSERT_NOTE;
    }

    private void setupRecyclerView(List<Note> allNotes) {
        RecyclerView notesList = findViewById(R.id.list_item_recycler_view);
        setupAdapter(allNotes, notesList);
        setupItemTouchHelper(notesList);
    }

    private void setupItemTouchHelper(RecyclerView notesList) {
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new NoteItemTouchHelperCallback(adapter));
        itemTouchHelper.attachToRecyclerView(notesList);
    }

    private void setupAdapter(List<Note> allNotes, RecyclerView notesList) {
        adapter = new NotesListAdapter(this, allNotes);
        notesList.setAdapter(adapter);
        adapter.setOnItemClickListener((note, position) -> {
            goToFormNoteActivityUpdate(note, position);
        });
    }

    private void goToFormNoteActivityUpdate(Note note, int position) {
        Intent openFormWithNote = new Intent(MainActivity.this, FormNoteActivity.class);
        openFormWithNote.putExtra(NOTE_KEY, note);
        openFormWithNote.putExtra(POSITION_KEY, position);
        startActivityForResult(openFormWithNote, REQUEST_CODE_UPDATE_NOTE);
    }
}
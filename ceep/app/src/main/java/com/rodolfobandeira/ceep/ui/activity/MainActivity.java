package com.rodolfobandeira.ceep.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.rodolfobandeira.ceep.R;
import com.rodolfobandeira.ceep.dao.NoteDAO;
import com.rodolfobandeira.ceep.model.Note;
import com.rodolfobandeira.ceep.ui.recyclerview.adapter.NotesListAdapter;

import java.util.List;

import static com.rodolfobandeira.ceep.ui.activity.NoteActivityConstants.APP_TITLE;
import static com.rodolfobandeira.ceep.ui.activity.NoteActivityConstants.REQUEST_CODE_INSERT_NOTE;
import static com.rodolfobandeira.ceep.ui.activity.NoteActivityConstants.RESULT_CODE_CREATED_NOTE;

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
        return dao.all();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (isRequestCodeInsertNote(requestCode, resultCode, data)) {
            addNote(data);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void addNote(Intent data) {
        Note note = (Note) data.getSerializableExtra("note");
        new NoteDAO().insert(note);
        adapter.add(note);
    }

    private boolean isRequestCodeInsertNote(int requestCode, int resultCode, Intent data) {
        return isRequestInsertNote(requestCode) && IsInsertNoteForm(resultCode) && hasNote(data);
    }

    private boolean hasNote(Intent data) {
        return data.hasExtra("note");
    }

    private boolean IsInsertNoteForm(int resultCode) {
        return resultCode == RESULT_CODE_CREATED_NOTE;
    }

    private boolean isRequestInsertNote(int requestCode) {
        return requestCode == REQUEST_CODE_INSERT_NOTE;
    }

    private void setupRecyclerView(List<Note> allNotes) {
        RecyclerView notesList = findViewById(R.id.list_item_recycler_view);
        setupAdapter(allNotes, notesList);
    }

    private void setupAdapter(List<Note> allNotes, RecyclerView notesList) {
        adapter = new NotesListAdapter(this, allNotes);
        notesList.setAdapter(adapter);
    }
}
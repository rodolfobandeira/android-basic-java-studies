package com.rodolfobandeira.ceep.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.rodolfobandeira.ceep.R;
import com.rodolfobandeira.ceep.model.Note;

import static com.rodolfobandeira.ceep.ui.activity.NoteActivityConstants.RESULT_CODE_CREATED_NOTE;

public class FormNoteActivity extends AppCompatActivity {

    public static final String NOTE_KEY = "note";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_note);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_form_save_note, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (isSaveNoteMenu(item)) {
            Note createdNote = createNote();
            returnNote(createdNote);
            finish(); // Finalize activity
        }
        return super.onOptionsItemSelected(item);
    }

    private void returnNote(Note createdNote) {
        Intent insertIntent = new Intent();
        insertIntent.putExtra(NOTE_KEY, createdNote);
        setResult(RESULT_CODE_CREATED_NOTE, insertIntent);
    }

    private Note createNote() {
        EditText title = findViewById(R.id.form_note_title);
        EditText description = findViewById(R.id.form_note_description);

        Note createdNote = new Note(
                title.getText().toString(),
                description.getText().toString()
        );
        return createdNote;
    }

    private boolean isSaveNoteMenu(MenuItem item) {
        return item.getItemId() == R.id.menu_form_ic_save_note;
    }
}
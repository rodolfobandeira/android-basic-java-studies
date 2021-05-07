package com.rodolfobandeira.ceep.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.rodolfobandeira.ceep.R;
import com.rodolfobandeira.ceep.model.Note;

import static com.rodolfobandeira.ceep.ui.activity.NoteActivityConstants.INVALID_POSITION;
import static com.rodolfobandeira.ceep.ui.activity.NoteActivityConstants.NOTE_KEY;
import static com.rodolfobandeira.ceep.ui.activity.NoteActivityConstants.POSITION_KEY;

public class FormNoteActivity extends AppCompatActivity {
    private int receivedPosition = INVALID_POSITION;
    private TextView title;
    private TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_note);
        setTitle("New Note");
        initializeFields();

        Intent receivedData = getIntent();
        if(receivedData != null && receivedData.hasExtra(NOTE_KEY)) {
            setTitle("Edit Note");
            Note receivedNote = (Note) receivedData.getSerializableExtra(NOTE_KEY);
            receivedPosition = receivedData.getIntExtra(POSITION_KEY, INVALID_POSITION);

            if(receivedNote != null) {
                populateFields(receivedNote);
            }
        }
    }

    private void populateFields(Note receivedNote) {
        title.setText(receivedNote.getTitle());
        description.setText(receivedNote.getDescription());
    }

    private void initializeFields() {
        title = findViewById(R.id.form_note_title);
        description = findViewById(R.id.form_note_description);
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

    private void returnNote(Note note) {
        Intent insertIntent = new Intent();
        insertIntent.putExtra(NOTE_KEY, note);
        insertIntent.putExtra(POSITION_KEY, receivedPosition);
        setResult(Activity.RESULT_OK, insertIntent);
    }

    private Note createNote() {
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
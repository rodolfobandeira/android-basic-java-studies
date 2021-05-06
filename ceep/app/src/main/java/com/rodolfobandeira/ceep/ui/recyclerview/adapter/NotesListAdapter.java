package com.rodolfobandeira.ceep.ui.recyclerview.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rodolfobandeira.ceep.R;
import com.rodolfobandeira.ceep.model.Note;

import java.util.List;

public class NotesListAdapter extends RecyclerView.Adapter<NotesListAdapter.NoteViewHolder> {
    private final List<Note> notes;
    private final Context context;
    private int totalViewHolder = 0;

    public NotesListAdapter(Context context, List<Note> notes) {
        this.notes = notes;
        this.context = context;
    }

    @Override
    public NotesListAdapter.NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        totalViewHolder++;
        View createdView = LayoutInflater.from(context)
                .inflate(R.layout.item, parent, false);
        Log.i("QTY_VIEW_HOLDER", "onCreateViewHolder: " + totalViewHolder);
        return new NoteViewHolder(createdView);
    }

    public void onBindViewHolder(NotesListAdapter.NoteViewHolder holder, int position) {
        Note note = notes.get(position);
        NoteViewHolder noteViewHolder = (NoteViewHolder) holder;
        noteViewHolder.link(note);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView description;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_title);
            description = itemView.findViewById(R.id.item_description);
        }

        public void link(Note note) {
            title.setText(note.getTitle());
            description.setText(note.getDescription());
        }
    }

    public void add(Note note) {
        notes.add(note);
        notifyDataSetChanged();
    }
}

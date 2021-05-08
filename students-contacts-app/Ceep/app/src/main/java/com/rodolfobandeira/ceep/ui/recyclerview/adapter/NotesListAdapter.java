package com.rodolfobandeira.ceep.ui.recyclerview.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rodolfobandeira.ceep.R;
import com.rodolfobandeira.ceep.model.Note;
import com.rodolfobandeira.ceep.ui.recyclerview.adapter.listener.OnItemClickListener;

import java.util.Collections;
import java.util.List;

public class NotesListAdapter extends RecyclerView.Adapter<NotesListAdapter.NoteViewHolder> {
    private final List<Note> notes;
    private final Context context;
    private OnItemClickListener onItemClickListener;

    public NotesListAdapter(Context context, List<Note> notes) {
        this.notes = notes;
        this.context = context;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public NotesListAdapter.NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View createdView = LayoutInflater.from(context)
                .inflate(R.layout.item, parent, false);
        return new NoteViewHolder(createdView);
    }

    public void onBindViewHolder(NotesListAdapter.NoteViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.link(note);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void add(Note note) {
        notes.add(note);
        notifyDataSetChanged();
    }

    public void update(int position, Note note) {
        notes.set(position, note);
        notifyItemChanged(position);
    }

    public void remove(int position) {
        notes.remove(position);
        notifyItemRemoved(position);
    }

    public void swapPosition(int startPosition, int finalPosition) {
        Collections.swap(notes, startPosition, finalPosition);
        notifyItemMoved(startPosition, finalPosition);
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView description;
        private Note note;

        public NoteViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_title);
            description = itemView.findViewById(R.id.item_description);

            itemView.setOnClickListener((View) -> {
                onItemClickListener.onItemClick(note, getAdapterPosition());
            });
        }

        public void link(Note note) {
            // Here is where we assign the note object that is used on NoteViewHolder lambda function
            this.note = note;
            title.setText(note.getTitle());
            description.setText(note.getDescription());
        }
    }
}

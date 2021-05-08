package com.rodolfobandeira.ceep.ui.recyclerview.helper.callback;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.rodolfobandeira.ceep.dao.NoteDAO;
import com.rodolfobandeira.ceep.ui.recyclerview.adapter.NotesListAdapter;

public class NoteItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private final NotesListAdapter adapter; // Final because it is only changed inside constructor

    public NoteItemTouchHelperCallback(NotesListAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int swipeFlags = ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT;
        int dragFlags = ItemTouchHelper.DOWN | ItemTouchHelper.UP | ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        int startPosition = viewHolder.getAdapterPosition();
        int finalPosition = target.getAdapterPosition();
        swapNotes(startPosition, finalPosition);
        return true; // Expected true
    }

    private void swapNotes(int startPosition, int finalPosition) {
        new NoteDAO().swapPosition(startPosition, finalPosition);
        adapter.swapPosition(startPosition, finalPosition);
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();
        removeNote(position);
    }

    private void removeNote(int adapterPosition) {
        new NoteDAO().remove(adapterPosition);
        adapter.remove(adapterPosition);
    }
}

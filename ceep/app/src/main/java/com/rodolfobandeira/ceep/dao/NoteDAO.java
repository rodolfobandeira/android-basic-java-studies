package com.rodolfobandeira.ceep.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.rodolfobandeira.ceep.model.Note;

public class NoteDAO {

    private final static ArrayList<Note> notes = new ArrayList<>();

    public List<Note> all() {
        return (List<Note>) notes.clone();
    }

    public void insert(Note... notes) {
        NoteDAO.notes.addAll(Arrays.asList(notes));
    }

    public void update(int position, Note note) {
        notes.set(position, note);
    }

    public void remove(int position) {
        notes.remove(position);
    }

    public void swap(int posicaoInicio, int posicaoFim) {
        Collections.swap(notes, posicaoInicio, posicaoFim);
    }

    public void removeAll() {
        notes.clear();
    }
}

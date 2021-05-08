package com.rodolfobandeira.scheduler.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.rodolfobandeira.scheduler.R;
import com.rodolfobandeira.scheduler.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentListAdapter extends BaseAdapter {
    private final List<Student> students = new ArrayList<>();
    private final Context context;

    public StudentListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Student getItem(int position) {
        return students.get(position);
    }

    @Override
    public long getItemId(int position) {
        return students.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View createdView = getInflate(viewGroup);
        Student student = students.get(position);

        linkViews(createdView, student);

        return createdView;
    }

    public void update(List<Student> students) {
        this.students.clear();
        this.students.addAll(students);
    }

    public void remove(Student student) {
        students.remove(student);
        notifyDataSetChanged();
    }

    private void linkViews(View createdView, Student student) {
        TextView fullName = createdView.findViewById(R.id.item_student_full_name);
        TextView phone = createdView.findViewById(R.id.item_student_phone);

        fullName.setText(student.getFullName());
        phone.setText(student.getPhone() + " " + student.formattedDate());
    }

    private View getInflate(ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(
                R.layout.item_student,
                viewGroup,
                false
        );
    }
}

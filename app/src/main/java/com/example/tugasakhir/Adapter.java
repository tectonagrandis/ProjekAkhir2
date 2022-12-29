package com.example.tugasakhir;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private ArrayList<CourseModel> courseRVCourseModelArrayList;
    private Context context;
    private CourseClickInterface courseClickInterface;
    int lastPos = -1;

    public Adapter(ArrayList<CourseModel> courseRVCourseModelArrayList, Context context, CourseClickInterface courseClickInterface) {
        this.courseRVCourseModelArrayList = courseRVCourseModelArrayList;
        this.context = context;
        this.courseClickInterface = courseClickInterface;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.course_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        CourseModel courseModel = courseRVCourseModelArrayList.get(position);
        holder.courseTV.setText(courseModel.getCourseName());
        holder.coursePriceTV.setText("Rp. " + courseModel.getCoursePrice());
        Picasso.get().load(courseModel.getCourseImg()).into(holder.courseIV);
        setAnimation(holder.itemView, position);
        holder.courseIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                courseClickInterface.onCourseClick(position);
            }
        });
    }

    private void setAnimation(View itemView, int position) {
        if (position > lastPos) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            itemView.setAnimation(animation);
            lastPos = position;
        }
    }

    @Override
    public int getItemCount() {
        return courseRVCourseModelArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView courseIV;
        private TextView courseTV, coursePriceTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            courseIV = itemView.findViewById(R.id.idIVCourse);
            courseTV = itemView.findViewById(R.id.idTVCOurseName);
            coursePriceTV = itemView.findViewById(R.id.idTVCousePrice);
        }
    }

    public interface CourseClickInterface {
        void onCourseClick(int position);
    }
}
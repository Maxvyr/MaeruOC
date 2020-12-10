package com.pandamy.maeruoc.ui;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pandamy.maeruoc.R;
import com.pandamy.maeruoc.models.Meeting;

import java.util.List;

public class ListMeetingRecyclerViewAdapter extends RecyclerView.Adapter<ListMeetingRecyclerViewAdapter.ViewHolder> {

    private List<Meeting> meetings;


    //Constructor
    public ListMeetingRecyclerViewAdapter(List<Meeting> meetings){
        this.meetings = meetings;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_list_meeting, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Meeting meeting = meetings.get(position);
    holder.colorMeeting.setBackgroundColor(meeting.getRoom().getColor().getIntColor());
    holder.roomName.setText(meeting.getRoom().getName());
    holder.email.setText(meeting.getMember().getEmail());


    holder.imageButton.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //todo delete meeting
            }
    });
    }

    @Override
    public int getItemCount() {
        return meetings.size();
    }

    public static class ViewHolder extends  RecyclerView.ViewHolder{
        View colorMeeting = itemView.findViewById(R.id.color_meeting);
        TextView roomName = itemView.findViewById(R.id.room_name);
        TextView email = itemView.findViewById(R.id.email);
        ImageButton imageButton = itemView.findViewById(R.id.item_list_delete_button);

        public  ViewHolder(View view){
            super(view);
        }
    }
}

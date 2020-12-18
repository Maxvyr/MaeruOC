package com.pandamy.maeruoc.ui;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pandamy.maeruoc.R;
import com.pandamy.maeruoc.controller.CallbackMeeting;
import com.pandamy.maeruoc.models.Meeting;

import java.util.List;

public class ListMeetingRecyclerViewAdapter extends RecyclerView.Adapter<ListMeetingRecyclerViewAdapter.ViewHolder> {

    private List<Meeting> meetings;
    private Activity activity;
    private CallbackMeeting callBack;
    public static final String TAG = "ListMeetingRecyclerView";


    //Constructor
    public ListMeetingRecyclerViewAdapter(List<Meeting> meetings, CallbackMeeting callBack){
        this.meetings = meetings;
        this.callBack = callBack;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_meeting, parent, false);
//        itemMeetingBinding = DataBindingUtil.setContentView(activity,R.layout.item_meeting);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Meeting meeting = meetings.get(position);
    holder.colorMeeting.setBackgroundColor(meeting.getRoom().getColor().getIntColor());
    holder.roomName.setText(meeting.getRoom().getName());
    holder.email.setText(meeting.getMember().getEmail());


    holder.imageButton.setOnClickListener(v -> {
        //call callback for delete item
        callBack.deleteMeeting(meeting);
    });
    }

    @Override
    public int getItemCount() {
        return meetings.size();
    }

    public static class ViewHolder extends  RecyclerView.ViewHolder{
        View colorMeeting;
        TextView roomName;
        TextView email;
        ImageButton imageButton;

        public  ViewHolder(View itemView){
            super(itemView);
            colorMeeting = itemView.findViewById(R.id.color_meeting);
            roomName = itemView.findViewById(R.id.room_name);
            email = itemView.findViewById(R.id.email);
            imageButton = itemView.findViewById(R.id.item_list_delete_button);
        }
    }
}

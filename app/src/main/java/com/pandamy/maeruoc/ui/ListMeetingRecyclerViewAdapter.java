package com.pandamy.maeruoc.ui;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pandamy.maeruoc.R;
import com.pandamy.maeruoc.controller.CallbackMeeting;
import com.pandamy.maeruoc.di.DI;
import com.pandamy.maeruoc.models.Meeting;
import com.pandamy.maeruoc.service.ApiService;

import java.util.ArrayList;
import java.util.List;

public class ListMeetingRecyclerViewAdapter extends RecyclerView.Adapter<ListMeetingRecyclerViewAdapter.ViewHolder> implements Filterable {

    private List<Meeting> meetings;
    private List<Meeting> meetingsFullList;
    private CallbackMeeting callBack;
    private ApiService apiService = DI.getApiService();
    public static final String TAG = "ListMeetingRecyclerView";


    //Constructor
    public ListMeetingRecyclerViewAdapter(List<Meeting> meetings, CallbackMeeting callBack){
        this.meetings = meetings;
        this.callBack = callBack;
        //permet d'avoir la liste fix
        meetingsFullList = new ArrayList<>(meetings);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_meeting, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Meeting meeting = meetings.get(position);
        String txtMeeting = meeting.getTitle() + " - " + meeting.getDate() + " - " + meeting.getRoom().getName();
        holder.colorMeeting.getBackground().setTint(meeting.getRoom().getColor().getIntColor());
        holder.roomName.setText(txtMeeting);
        holder.email.setText(meeting.getMember());

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



    @Override
    public Filter getFilter() {
        return meetingsFilter;
    }

    private Filter meetingsFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Meeting> filteredList = new ArrayList<>();

            if(constraint == null || constraint.length() == 0) {
                filteredList.addAll(meetingsFullList);
            } else{
                //en minuscule et supprimer les espaces
                String filterPattern = constraint.toString().toLowerCase().trim();

                for(Meeting meeting : meetingsFullList){
                    //avec le titre
                    apiService.filterMeetingByName(meeting,filteredList,filterPattern);
                    //avec le nom de la room
                    apiService.filterMeetingByRoom(meeting,filteredList,filterPattern);
                    //avec la date
                    apiService.filterMeetingByDate(meeting,filteredList,filterPattern);
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            meetings.clear();
            meetings.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}

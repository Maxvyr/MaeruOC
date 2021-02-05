package com.pandamy.maeruoc.ui;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pandamy.maeruoc.R;
import com.pandamy.maeruoc.controller.CallbackMeeting;
import com.pandamy.maeruoc.controller.CallbackMember;
import com.pandamy.maeruoc.models.Meeting;
import com.pandamy.maeruoc.models.Member;

import java.util.List;

public class ListMemberRecyclerViewAdapter extends RecyclerView.Adapter<ListMemberRecyclerViewAdapter.ViewHolder> {

    private List<Member> members;
    private CallbackMember callBack;
    public static final String TAG = "ListMeetingRecyclerView";


    //Constructor
    public ListMemberRecyclerViewAdapter(List<Member> members, CallbackMember callBack){
        this.members = members;
        this.callBack = callBack;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_member, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Member member = members.get(position);
        String memberFLName = member.getFirstName() + " " + member.getLastName().toUpperCase();
        holder.memberName.setText(memberFLName);
        holder.email.setText(member.getEmail());

        holder.checkBox.setOnClickListener(v -> {
            //call callback checkbox
            callBack.onClickCheckBox(position);
        });
    }

    @Override
    public int getItemCount() {
        return members.size();
    }

    public static class ViewHolder extends  RecyclerView.ViewHolder{
        TextView memberName;
        TextView email;
        CheckBox checkBox;

        public  ViewHolder(View itemView){
            super(itemView);
            memberName = itemView.findViewById(R.id.item_member_name);
            email = itemView.findViewById(R.id.item_member_email);
            checkBox = itemView.findViewById(R.id.item_member_check);
        }
    }
}

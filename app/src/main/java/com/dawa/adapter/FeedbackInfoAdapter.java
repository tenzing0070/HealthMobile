package com.dawa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dawa.mobilehealth.R;

import com.dawa.model.doctors;
import com.dawa.model.feedbacks;


import java.util.ArrayList;
import java.util.List;



public class FeedbackInfoAdapter extends RecyclerView.Adapter<FeedbackInfoAdapter.FeedbacksViewHolder> {

    Context mContext;
    List<feedbacks> feedbacksList;
    private List<feedbacks> filterfeedbackList;


    public FeedbackInfoAdapter(Context mContext, List<feedbacks> feedbacksList)

    {
        this.mContext = mContext;
        this.feedbacksList = feedbacksList;
        filterfeedbackList = new ArrayList<>(feedbacksList);
    }


    @NonNull
    @Override
    public FeedbacksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_admin_feedbackinfo_details,parent,false);
        return new FeedbacksViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull FeedbackInfoAdapter.FeedbacksViewHolder holder, int i) {
        final feedbacks feedbacks = feedbacksList.get(i);

        holder.useremail.setText(feedbacks.getEmail());
        holder.usermessage.setText(feedbacks.getMessage());

        boolean isExpandble = feedbacksList.get(i).isExpandable();
        holder.expandableLayout.setVisibility(isExpandble ? View.VISIBLE : View.GONE);



    }

    @Override
    public int getItemCount() {

        return feedbacksList.size();
    }

    public Filter getFilter()

    {
        return appointmentsfilter;
    }

    private Filter appointmentsfilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<feedbacks> filteredList= new ArrayList<>();
            if (constraint == null || constraint.length()==0){
                filteredList.addAll(filterfeedbackList);
            }else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (feedbacks feedbacks : filterfeedbackList){
                    if(feedbacks.getEmail().toLowerCase().contains(filterPattern)){
                        filteredList.add(feedbacks);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            feedbacksList.clear();
            feedbacksList.addAll((List)results.values);
            notifyDataSetChanged();

        }
    };

    public class FeedbacksViewHolder extends RecyclerView.ViewHolder{


        TextView useremail, usermessage;
        LinearLayout linearLayout;
        RelativeLayout expandableLayout;



        public FeedbacksViewHolder(@NonNull View itemView) {
            super(itemView);


            useremail= itemView.findViewById(R.id.user_feedback_email);
            usermessage = itemView.findViewById(R.id.user_feedback_message);

            linearLayout = itemView.findViewById(R.id.linear_layout_feedback);
            expandableLayout = itemView.findViewById(R.id.expandable_layout_feedback);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    feedbacks feedbacks = feedbacksList.get(getAdapterPosition());
                    feedbacks.setExpandable(!feedbacks.isExpandable());
                    notifyItemChanged(getAdapterPosition());
                }
            });


        }
    }
}

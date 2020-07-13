package com.dawa.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dawa.api.admin_api;
import com.dawa.mobilehealth.R;
import com.dawa.mobilehealth.admin.AdmindashActivity;
import com.dawa.model.Faqs;
import com.dawa.model.doctors;
import com.dawa.url.url;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FaqInfoAdapter extends RecyclerView.Adapter<FaqInfoAdapter.FaqInfoViewHolder> {

    Context mContext;
    List<Faqs> faqsList;
    private List<Faqs> filterfaqList;
    String id;
    ImageView ivDeleteFaq;

    public FaqInfoAdapter(Context mContext, List<Faqs> faqsList) {

        this.mContext = mContext;
        this.faqsList = faqsList;
        filterfaqList = new ArrayList<>(faqsList);

    }

    @NonNull
    @Override
    public FaqInfoAdapter.FaqInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_admin_faqinfo_details,parent,false);

        ivDeleteFaq =v.findViewById(R.id.imgFaqInfoDel);

        ivDeleteFaq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteFaqInfo();
            }

            private void deleteFaqInfo() {

                admin_api faqinfoApi = url.getInstance().create(admin_api.class);
                Call<Faqs> voidCall = faqinfoApi.deleteFaqPost(url.token, id);
                voidCall.enqueue(new Callback<Faqs>() {
                    @Override
                    public void onResponse(Call<Faqs> call, Response<Faqs> response) {
                        if (!response.isSuccessful()) {

                            Toast.makeText(mContext, "Code : " + response.code() + ", Message : " + response.message(), Toast.LENGTH_SHORT).show();

                        }
                        Toast.makeText(mContext, "Deleted !!!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Faqs> call, Throwable t) {
                        Toast.makeText(mContext, "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
        return new FaqInfoViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull FaqInfoAdapter.FaqInfoViewHolder holder, int i) {

        final Faqs Faqs = faqsList.get(i);

        holder.question.setText(Faqs.getQuestion());
        holder.answer.setText(Faqs.getAnswer());


        boolean isExpandble = faqsList.get(i).isExpandable();
        holder.expandableLayout.setVisibility(isExpandble ? View.VISIBLE : View.GONE);

    }

    @Override
    public int getItemCount() {
        return faqsList.size();
    }

    public Filter getFilter()

    {
        return userssfilter;
    }

    private Filter userssfilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Faqs> filteredList= new ArrayList<>();
            if (constraint == null || constraint.length()==0){
                filteredList.addAll(filterfaqList);
            }else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Faqs Faqs : filterfaqList){
                    if(Faqs.getQuestion().toLowerCase().contains(filterPattern)){
                        filteredList.add(Faqs);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            faqsList.clear();
            faqsList.addAll((List)results.values);
            notifyDataSetChanged();

        }
    };


    public class FaqInfoViewHolder extends RecyclerView.ViewHolder {

        TextView question, answer;
        LinearLayout linearLayout;
        RelativeLayout expandableLayout;

        public FaqInfoViewHolder(@NonNull View itemView) {
            super(itemView);

            question = itemView.findViewById(R.id.admin_question);
            answer = itemView.findViewById(R.id.admin_answer);

            linearLayout = itemView.findViewById(R.id.linear_layout_adminfaqinfo);
            expandableLayout = itemView.findViewById(R.id.expandable_layout_adminfaqdetails);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Faqs Faqs = faqsList.get(getAdapterPosition());
                    Faqs.setExpandable(!Faqs.isExpandable());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}

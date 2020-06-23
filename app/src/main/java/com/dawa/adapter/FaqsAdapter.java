package com.dawa.adapter;

import android.content.Context;
import android.os.StrictMode;
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
import com.dawa.model.Faqs;
import com.dawa.model.Instructions;

import java.util.ArrayList;
import java.util.List;

public class FaqsAdapter extends RecyclerView.Adapter<FaqsAdapter.FaqVH> {

    Context mContext;
    List<Faqs> faqsList;
    private List<Faqs> filterfaqList;

    public FaqsAdapter( Context mContext, List<Faqs> faqsList)

    {
        this.mContext = mContext;
        this.faqsList = faqsList;
        filterfaqList = new ArrayList<>(faqsList);

    }

    @NonNull
    @Override
    public FaqVH onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_faq_list,parent,false);
        return new FaqVH(view);
    }

    private void StrictMode() {
        StrictMode.ThreadPolicy policy =
                new StrictMode.ThreadPolicy.Builder()
                        .permitAll().build();

        StrictMode.setThreadPolicy(policy);
    }

    @Override
    public void onBindViewHolder(@NonNull FaqVH holder, int i) {

       final Faqs faqs= faqsList.get(i);

        holder.questionNameTxt.setText(faqs.getQuestion());
        holder.answerTxt.setText(faqs.getAnswer());

        boolean isExpandable = faqsList.get(i).isExpandable();
        holder.expandableLayout.setVisibility(isExpandable ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return faqsList.size();
    }

    public Filter getFilter()

    {
        return faqsfilter;
    }

    private Filter faqsfilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Faqs> filteredList= new ArrayList<>();
            if (constraint == null || constraint.length()==0){
                filteredList.addAll(filterfaqList);
            }else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Faqs faqs : filterfaqList){
                    if(faqs.getQuestion().toLowerCase().contains(filterPattern)){
                        filteredList.add(faqs);
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


    public class FaqVH extends RecyclerView.ViewHolder {

        TextView questionNameTxt, answerTxt;

        LinearLayout linearLayout;
        RelativeLayout expandableLayout;

        public FaqVH(@NonNull View itemView) {
            super(itemView);

            questionNameTxt = itemView.findViewById(R.id.question);
            answerTxt = itemView.findViewById(R.id.answer);

            linearLayout = itemView.findViewById(R.id.rowlinear_layout);
            expandableLayout = itemView.findViewById(R.id.expandable_layout);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   Faqs faqs = faqsList.get(getAdapterPosition());
                   faqs.setExpandable(!faqs.isExpandable());
                   notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}

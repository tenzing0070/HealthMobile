package com.dawa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dawa.mobilehealth.R;
import com.dawa.model.Faqs;

import java.util.ArrayList;
import java.util.List;

public class FaqsAdapter extends RecyclerView.Adapter<FaqsAdapter.FaqVH> {

    List<Faqs> faqsList;

    public FaqsAdapter(List<Faqs> faqsList)

    {
        this.faqsList = faqsList;
    }

    @NonNull
    @Override
    public FaqVH onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_faq_list,parent,false);
        return new FaqVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FaqVH holder, int position) {

        Faqs faqs= faqsList.get(position);
        holder.questionNameTxt.setText(faqs.getQuestionName());
        holder.answerTxt.setText(faqs.getAnswer());

        boolean isExpandable = faqsList.get(position).isExpandable();
        holder.expandableLayout.setVisibility(isExpandable ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return faqsList.size();
    }

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

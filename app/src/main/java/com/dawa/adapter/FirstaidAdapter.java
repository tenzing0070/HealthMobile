package com.dawa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dawa.mobilehealth.R;
import com.dawa.model.Instructions;

import java.util.List;

public class FirstaidAdapter extends RecyclerView.Adapter<FirstaidAdapter.FirstaidVH> {


    List<Instructions> instructionsList;

    public FirstaidAdapter( List<Instructions> instructionsList)
    {

        this.instructionsList = instructionsList;
    }


    @NonNull
    @Override
    public FirstaidVH onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_firstaid,parent,false);
        return new FirstaidVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FirstaidAdapter.FirstaidVH holder, int position) {

        Instructions instructions = instructionsList.get(position);
        holder.codeNameTxt.setText(instructions.getCodeName());
        holder.instructionTxt.setText(instructions.getInstruction());
        holder.descriptionTxt.setText(instructions.getDescription());

        boolean isExpandable = instructionsList.get(position).isExpandable();
        holder.expandablelayout.setVisibility(isExpandable ? View.VISIBLE : View.GONE);

    }

    @Override
    public int getItemCount() {
        return instructionsList.size();
    }

    public class FirstaidVH extends RecyclerView.ViewHolder{

        TextView codeNameTxt, instructionTxt, descriptionTxt;

        LinearLayout linearLayout;
        RelativeLayout expandablelayout;

        public FirstaidVH(@NonNull View itemView) {
            super(itemView);

            codeNameTxt = itemView.findViewById(R.id.code_name);
            instructionTxt = itemView.findViewById(R.id.instruction);
            descriptionTxt = itemView.findViewById(R.id.description_firstaid);

            linearLayout = itemView.findViewById(R.id.linear_layout_firstaid);
            expandablelayout = itemView.findViewById(R.id.expandable_layout_firstaid);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Instructions instructions = instructionsList.get(getAdapterPosition());
                    instructions.setExpandable(!instructions.isExpandable());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}

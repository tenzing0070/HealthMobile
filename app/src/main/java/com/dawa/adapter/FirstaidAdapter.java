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
import com.dawa.model.Instructions;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class FirstaidAdapter extends RecyclerView.Adapter<FirstaidAdapter.FirstaidVH> {


     private List<Instructions> filterinstructionList;
    List<Instructions> instructionsList;

    public FirstaidAdapter( List<Instructions> instructionsList)
    {

        this.instructionsList = instructionsList;
        filterinstructionList = new ArrayList<>(instructionsList);
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
        holder.photoimg.setImageResource(instructions.getImage());

        boolean isExpandable = instructionsList.get(position).isExpandable();
        holder.expandablelayout.setVisibility(isExpandable ? View.VISIBLE : View.GONE);

    }

    @Override
    public int getItemCount() {
        return instructionsList.size();
    }


    public  Filter getFilter() { return injuryfilter;}

    private Filter injuryfilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Instructions> filteredList= new ArrayList<>();
            if (constraint == null || constraint.length()==0){
                filteredList.addAll(instructionsList);
            }else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Instructions instructions : instructionsList){
                    if(instructions.getCodeName().toLowerCase().contains(filterPattern)){
                        filteredList.add(instructions);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            instructionsList.clear();
            instructionsList.addAll((List)results.values);
            notifyDataSetChanged();

        }
    };

    public class FirstaidVH extends RecyclerView.ViewHolder{

        TextView codeNameTxt, instructionTxt, descriptionTxt;
        CircleImageView photoimg;

        LinearLayout linearLayout;
        RelativeLayout expandablelayout;

        public FirstaidVH(@NonNull View itemView) {
            super(itemView);

            codeNameTxt = itemView.findViewById(R.id.code_name);
            instructionTxt = itemView.findViewById(R.id.instruction);
            descriptionTxt = itemView.findViewById(R.id.description_firstaid);
            photoimg = (CircleImageView) itemView.findViewById(R.id.imgdisease);

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

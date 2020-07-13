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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dawa.mobilehealth.R;
import com.dawa.mobilehealth.admin.Admin_doc_info_details_crud;
import com.dawa.mobilehealth.admin.Admin_firstaid_info_details_crud;
import com.dawa.model.Instructions;

import com.dawa.url.url;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FirstaidInfoAdapter extends RecyclerView.Adapter<FirstaidInfoAdapter.FirstaidInfoViewHolder> {

    Context mContext;
    List<Instructions> instructionsList;
    private List<Instructions> filterinstructionList;

    public FirstaidInfoAdapter(Context mContext, List<Instructions> instructionsList) {

        this.mContext = mContext;
        this.instructionsList = instructionsList;
        filterinstructionList = new ArrayList<>(instructionsList);

    }

    @NonNull
    @Override
    public FirstaidInfoAdapter.FirstaidInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_admin_firstaidinfo_details,parent,false);
        return new FirstaidInfoViewHolder(v);

    }


    @Override
    public void onBindViewHolder(@NonNull FirstaidInfoAdapter.FirstaidInfoViewHolder holder, int i) {

        final Instructions Instructions = instructionsList.get(i);
        String imgPath = url.imagePath+Instructions.getImage();
        holder.codename.setText(Instructions.getCodename());
        holder.instruction.setText(Instructions.getInstruction());
        holder.description.setText(Instructions.getDescription());
        Picasso.get().load(imgPath).into(holder.imgProfile);

        boolean isExpandble = instructionsList.get(i).isExpandable();
        holder.expandableLayout.setVisibility(isExpandble ? View.VISIBLE : View.GONE);

        holder.imgviewmorefirstaid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent notify = new Intent(mContext, Admin_firstaid_info_details_crud.class);
                notify.putExtra("id", Instructions.get_id());
                notify.putExtra("codename",Instructions.getCodename());
                notify.putExtra("instruction",Instructions.getInstruction());
                notify.putExtra("description",Instructions.getDescription());
                notify.putExtra("image", Instructions.getImage());
                mContext.startActivity(notify);

            }

        });



    }

    @Override
    public int getItemCount() {
        return instructionsList.size();
    }

    public Filter getFilter()

    {
        return userssfilter;
    }

    private Filter userssfilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Instructions> filteredList= new ArrayList<>();
            if (constraint == null || constraint.length()==0){
                filteredList.addAll(filterinstructionList);
            }else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Instructions Instructions : filterinstructionList){
                    if(Instructions.getCodename().toLowerCase().contains(filterPattern)){
                        filteredList.add(Instructions);
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


    public class FirstaidInfoViewHolder extends RecyclerView.ViewHolder {

        CircleImageView imgProfile;
        TextView codename, instruction,description;
        LinearLayout linearLayout;
        RelativeLayout expandableLayout;
        ImageView imgviewmorefirstaid;

        public FirstaidInfoViewHolder(@NonNull View itemView) {
            super(itemView);

            imgProfile = itemView.findViewById(R.id.imgAdminFirstaid);
            codename = itemView.findViewById(R.id.firstaid_codename);
            instruction = itemView.findViewById(R.id.firstaid_instruction);
            description = itemView.findViewById(R.id.firstaid_description);

            imgviewmorefirstaid = itemView.findViewById(R.id.imgviewmorefirstaid);


            linearLayout = itemView.findViewById(R.id.linear_layout_adminfirstaidinfo);
            expandableLayout = itemView.findViewById(R.id.expandable_layout_adminfirstaiddetails);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Instructions Instructions = instructionsList.get(getAdapterPosition());
                    Instructions.setExpandable(!Instructions.isExpandable());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}

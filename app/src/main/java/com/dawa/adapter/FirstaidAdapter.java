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
import com.dawa.model.Instructions;
import com.dawa.url.url;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class FirstaidAdapter extends RecyclerView.Adapter<FirstaidAdapter.FirstaidsViewHolder> {

    Context mContext;
    List<Instructions> instructionsList;
    private List<Instructions> filterfirstaidList;


    public FirstaidAdapter( Context mContext, List<Instructions> instructionsList)
    {
        this.mContext = mContext;
        this.instructionsList = instructionsList;
        filterfirstaidList = new ArrayList<>(instructionsList);
    }


    @NonNull
    @Override
    public FirstaidsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_firstaid,parent,false);
        return new FirstaidsViewHolder(v);
    }

    private void StrictMode() {
        StrictMode.ThreadPolicy policy =
                new StrictMode.ThreadPolicy.Builder()
                        .permitAll().build();

        StrictMode.setThreadPolicy(policy);
    }

    @Override
    public void onBindViewHolder(@NonNull FirstaidAdapter.FirstaidsViewHolder holder, int i) {
        final Instructions instructions = instructionsList.get(i);
        String imgPath = url.imagePath+instructions.getImage();

        Picasso.get().load(imgPath).into(holder.imgProblem);
        holder.firstaidCodeName.setText(instructions.getCodename());
        holder.firstaidInstruction.setText(instructions.getInstruction());
        holder.firstaidDescription.setText(instructions.getDescription());


    }

    @Override
    public int getItemCount() {
        return instructionsList.size();
    }


    public  Filter getFilter() { return instructionsfilter;}

    private Filter instructionsfilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Instructions> filteredList= new ArrayList<>();
            if (constraint == null || constraint.length()==0){
                filteredList.addAll(filterfirstaidList);
            }else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Instructions instructions : filterfirstaidList){
                    if(instructions.getCodename().toLowerCase().contains(filterPattern)){
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

    public class FirstaidsViewHolder extends RecyclerView.ViewHolder{

        CircleImageView imgProblem;
        TextView firstaidCodeName, firstaidInstruction, firstaidDescription;

        public FirstaidsViewHolder(@NonNull View itemView) {
            super(itemView);

            imgProblem =  itemView.findViewById(R.id.imgProblem);
             firstaidCodeName= itemView.findViewById(R.id.code_name);
            firstaidInstruction = itemView.findViewById(R.id.instruction);
            firstaidDescription = itemView.findViewById(R.id.description_firstaid);



        }
    }


}

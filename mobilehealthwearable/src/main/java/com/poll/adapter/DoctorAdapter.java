package com.poll.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.poll.Url.url;
import com.poll.mobilehealthwearable.DoctorDetailActivity;
import com.poll.mobilehealthwearable.R;
import com.poll.model.doctors;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.DoctorsViewHolder> {
    Context mContext;
    List<doctors> doctorsList;
    private List<doctors> filterdoctorList;

    public DoctorAdapter(Context mContext, List<doctors> doctorsList) {
        this.mContext = mContext;
        this.doctorsList=doctorsList;
        filterdoctorList = new ArrayList<>(doctorsList);
    }

    @NonNull
    @Override
    public DoctorsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_doctor_img_display, parent, false);
            return new DoctorsViewHolder(v);

    }

    private void StrictMode() {
        StrictMode.ThreadPolicy policy =
                new StrictMode.ThreadPolicy.Builder()
                        .permitAll().build();

        StrictMode.setThreadPolicy(policy);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorsViewHolder holder, int i) {
        final doctors doctors = doctorsList.get(i);
        String imgPath = url.imagePath+doctors.getImage();

        Picasso.get().load(imgPath).into(holder.imgProfileDoc);
        holder.doctorFirstName.setText(doctors.getFirstname());
        holder.doctorLastName.setText(doctors.getLastname());
        holder.doctorSpecialist.setText(doctors.getSpecialist());
       holder.doctorGender.setText(doctors.getGender());
       holder.doctorPrice.setText(doctors.getPrice());

        holder.imgProfileDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewDetails = new Intent(mContext, DoctorDetailActivity.class);
                viewDetails.putExtra("id",doctors.get_id());
                viewDetails.putExtra("firstname", doctors.getFirstname());
                viewDetails.putExtra("lastname", doctors.getLastname());
                viewDetails.putExtra("Specialist", doctors.getSpecialist());
                viewDetails.putExtra("gender", doctors.getGender());
               viewDetails.putExtra("price", doctors.getPrice());
               viewDetails.putExtra("image", doctors.getImage());
                mContext.startActivity(viewDetails);

            }
        });

    }

    @Override
    public int getItemCount() { return  doctorsList.size(); }


//    @Override
    public Filter getFilter() {
        return doctorsfilter;
    }



    private Filter doctorsfilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<doctors> filteredList= new ArrayList<>();
            if (constraint == null || constraint.length()==0){
                filteredList.addAll(filterdoctorList);
            }else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (doctors doctors : filterdoctorList){
                    if(doctors.getSpecialist().toLowerCase().contains(filterPattern)){
                        filteredList.add(doctors);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            doctorsList.clear();
            doctorsList.addAll((List)results.values);
            notifyDataSetChanged();

        }
    };


    public class DoctorsViewHolder extends RecyclerView.ViewHolder {

        CircleImageView imgProfileDoc;
        TextView doctorFirstName, doctorLastName, doctorSpecialist, doctorGender, doctorPrice;

        public DoctorsViewHolder (@NonNull View itemView ) {
            super(itemView);
            imgProfileDoc = itemView.findViewById(R.id.imgProfileDoc);
            doctorFirstName = itemView.findViewById(R.id.doctorFirstName);
            doctorLastName = itemView.findViewById(R.id.doctorLastName);
            doctorSpecialist = itemView.findViewById(R.id.doctorSpecialist);
           doctorGender = itemView.findViewById(R.id.doctortextGender);
            doctorPrice = itemView.findViewById(R.id.viewholder_doctor_price);
        }
    }


}

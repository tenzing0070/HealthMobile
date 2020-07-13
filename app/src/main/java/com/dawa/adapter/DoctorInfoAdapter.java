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


import com.dawa.mobilehealth.admin.Admin_doc_info_details_crud;
import com.dawa.mobilehealth.R;



import com.dawa.model.doctors;
import com.dawa.url.url;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class DoctorInfoAdapter extends RecyclerView.Adapter<DoctorInfoAdapter.DoctorInfoViewHolder> {

    String id;
    Context mContext;
    List<doctors> doctorsList;
    private List<doctors> filterdoctorList;

    public DoctorInfoAdapter(Context mContext, List<doctors> doctorsList) {

        this.mContext = mContext;
        this.doctorsList = doctorsList;
        filterdoctorList = new ArrayList<>(doctorsList);

    }

    @NonNull
    @Override
    public DoctorInfoAdapter.DoctorInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_admin_doctorinfo_details, parent, false);
        return new DoctorInfoViewHolder(v);

    }







    @Override
    public void onBindViewHolder(@NonNull DoctorInfoAdapter.DoctorInfoViewHolder holder, int i) {

        final doctors doctors = doctorsList.get(i);
        String imgPath = url.imagePath + doctors.getImage();

        holder.firstname.setText(doctors.getFirstname());
        holder.lastname.setText(doctors.getLastname());
        holder.gender.setText(doctors.getGender());
        holder.specialist.setText(doctors.getSpecialist());
        holder.price.setText(doctors.getPrice());
        Picasso.get().load(imgPath).into(holder.imgProfile);

        boolean isExpandble = doctorsList.get(i).isExpandable();
        holder.expandableLayout.setVisibility(isExpandble ? View.VISIBLE : View.GONE);

        holder.imgviewmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent notify = new Intent(mContext, Admin_doc_info_details_crud.class);
                notify.putExtra("id", doctors.get_id());
                notify.putExtra("firstname",doctors.getFirstname());
                notify.putExtra("lastname",doctors.getLastname());
                notify.putExtra("gender",doctors.getGender());
                notify.putExtra("specialist",doctors.getSpecialist());
                notify.putExtra("price",doctors.getPrice());
                notify.putExtra("image", doctors.getImage());
                mContext.startActivity(notify);

            }

        });

    }

    @Override
    public int getItemCount() {
        return doctorsList.size();
    }

    public Filter getFilter() {
        return doctorsfilter;
    }

    private Filter doctorsfilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<doctors> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(filterdoctorList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (doctors doctors : filterdoctorList) {
                    if (doctors.getSpecialist().toLowerCase().contains(filterPattern)) {
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
            doctorsList.addAll((List) results.values);
            notifyDataSetChanged();

        }
    };


    public class DoctorInfoViewHolder extends RecyclerView.ViewHolder {

        CircleImageView imgProfile;
        ImageView imgviewmore;
        TextView firstname, lastname, gender, specialist, price, did;
        LinearLayout linearLayout;
        RelativeLayout expandableLayout;


        public DoctorInfoViewHolder(@NonNull final View itemView) {
            super(itemView);

            imgProfile = itemView.findViewById(R.id.imgdoctorimage);
            firstname = itemView.findViewById(R.id.doctor_firstname);
            lastname = itemView.findViewById(R.id.doctor_lastname);
            gender = itemView.findViewById(R.id.doctor_gender);
            specialist = itemView.findViewById(R.id.doctor_specialist);
            price = itemView.findViewById(R.id.doctor_price);

            imgviewmore = itemView.findViewById(R.id.imgviewmore);



            linearLayout = itemView.findViewById(R.id.linear_layout_admindoctorinfo);
            expandableLayout = itemView.findViewById(R.id.expandable_layout_admindoctordetails);


            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    doctors doctors = doctorsList.get(getAdapterPosition());
                    doctors.setExpandable(!doctors.isExpandable());
                    notifyItemChanged(getAdapterPosition());

                }

            });



        }








    }
}

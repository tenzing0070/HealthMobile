package com.dawa.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dawa.mobilehealth.R;
import com.dawa.mobilehealth.admin.Admin_booking_info_details_crud;
import com.dawa.mobilehealth.admin.Admin_doc_info_details_crud;
import com.dawa.model.Booking;
import com.dawa.model.users;
import com.dawa.url.url;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AppointmentInfoAdapter extends RecyclerView.Adapter<AppointmentInfoAdapter.AppointmentsViewHolder> {

    Context mContext;
    List<Booking> appointmentsList;
    private List<Booking> filterappointmentList;


    public AppointmentInfoAdapter(Context mContext, List<Booking> appointmentsList)

    {
        this.mContext = mContext;
        this.appointmentsList = appointmentsList;
        filterappointmentList = new ArrayList<>(appointmentsList);
    }


    @NonNull
    @Override
    public AppointmentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_admin_appointmentinfo_details,parent,false);
        return new AppointmentsViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull AppointmentInfoAdapter.AppointmentsViewHolder holder, int i) {
        final Booking booking = appointmentsList.get(i);
        String imgPath = url.imagePath+booking.getDoctors().getImage();

        holder.docfirstname.setText(booking.getDoctors().getFirstname());
        holder.doclastname.setText(booking.getDoctors().getLastname());
        holder.docspecialist.setText(booking.getDoctors().getSpecialist());
        holder.docgender.setText(booking.getDoctors().getGender());
        holder.docprice.setText(booking.getDoctors().getPrice());
        holder.username.setText(booking.getUser().getUsername());
        Picasso.get().load(imgPath).into(holder.imgProfile);
        holder.purpose.setText(booking.getPurpose());
        holder.date.setText(booking.getDate());
        holder.time.setText(booking.getTime());

        holder.imgviewmoreBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent notify = new Intent(mContext, Admin_booking_info_details_crud.class);
              // notify.putExtra("id", Booking.get_id());
                notify.putExtra("username", booking.getUser().getUsername());
                notify.putExtra("firstname", booking.getDoctors().getFirstname());
                notify.putExtra("lastname", booking.getDoctors().getLastname());
                notify.putExtra("gender", booking.getDoctors().getGender());
                notify.putExtra("price", booking.getDoctors().getPrice());
                notify.putExtra("specialist", booking.getDoctors().getSpecialist());
                notify.putExtra("lastname",booking.getPurpose());
                notify.putExtra("date",booking.getDate());
                notify.putExtra("time",booking.getTime());
                notify.putExtra("purpose",booking.getPurpose());
                notify.putExtra("image", booking.getDoctors().getImage());
                mContext.startActivity(notify);

            }

        });

    }

    @Override
    public int getItemCount() {

        return appointmentsList.size();
    }

    public Filter getFilter()

    {
        return appointmentsfilter;
    }

    private Filter appointmentsfilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Booking> filteredList= new ArrayList<>();
            if (constraint == null || constraint.length()==0){
                filteredList.addAll(filterappointmentList);
            }else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Booking booking : filterappointmentList){
                    if(booking.getDate().toLowerCase().contains(filterPattern)){
                        filteredList.add(booking);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            appointmentsList.clear();
            appointmentsList.addAll((List)results.values);
            notifyDataSetChanged();

        }
    };

    public class AppointmentsViewHolder extends RecyclerView.ViewHolder{

        CircleImageView imgProfile;
        TextView docfirstname, doclastname, docspecialist, docgender, docprice, username, date, time, purpose;
        ImageView imgviewmoreBooking;



        public AppointmentsViewHolder(@NonNull View itemView) {
            super(itemView);

            imgProfile =  itemView.findViewById(R.id.imgProfileDocAppoint);
            docfirstname= itemView.findViewById(R.id.doc_appoint_Fname);
            doclastname = itemView.findViewById(R.id.doc_appoint_Lname);
            docspecialist = itemView.findViewById(R.id.doc_appoint_Specialist);
            docgender= itemView.findViewById(R.id.doc_appoint_Gender);
            docprice = itemView.findViewById(R.id.doc_appoint_Price);
            username = itemView.findViewById(R.id.user_appoint_username);
            date = itemView.findViewById(R.id.appoint_Date);
            time = itemView.findViewById(R.id.appoint_Time);
            purpose = itemView.findViewById(R.id.appoint_Purpose);

            imgviewmoreBooking = itemView.findViewById(R.id.imgviewmorebooking);


        }
    }
}

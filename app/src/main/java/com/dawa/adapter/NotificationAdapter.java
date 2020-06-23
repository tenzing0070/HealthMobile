package com.dawa.adapter;

import android.content.Context;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.dawa.mobilehealth.R;
import com.dawa.model.Booking;
import com.dawa.url.url;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationsViewHolder> {

    Context mContext;
    List<Booking> bookingList;
    private List<Booking> filterbookingList;


    public NotificationAdapter( Context mContext, List<Booking> bookingList)

    {
        this.mContext = mContext;
        this.bookingList = bookingList;
        filterbookingList = new ArrayList<>(bookingList);
    }


    @NonNull
    @Override
    public NotificationsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_notification,parent,false);
        return new NotificationsViewHolder(v);
    }

    private void StrictMode() {
        StrictMode.ThreadPolicy policy =
                new StrictMode.ThreadPolicy.Builder()
                        .permitAll().build();

        StrictMode.setThreadPolicy(policy);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.NotificationsViewHolder holder, int i) {
        final Booking booking = bookingList.get(i);
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

//       boolean isExpandble = bookingList.get(i).isExpandable();
//       holder.expandableLayout.setVisibility(isExpandble ? View.VISIBLE : View.GONE);

    }

    @Override
    public int getItemCount() {

        return bookingList.size();
    }

    public Filter getFilter()

    {
        return bookingsfilter;
    }

    private Filter bookingsfilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Booking> filteredList= new ArrayList<>();
            if (constraint == null || constraint.length()==0){
                filteredList.addAll(filterbookingList);
            }else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Booking booking : filterbookingList){
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
            bookingList.clear();
            bookingList.addAll((List)results.values);
            notifyDataSetChanged();

        }
    };

    public class NotificationsViewHolder extends RecyclerView.ViewHolder{

        CircleImageView imgProfile;
        TextView docfirstname, doclastname, docspecialist, docgender, docprice, username, date, time, purpose;
//        LinearLayout linearLayout;
//        RelativeLayout expandableLayout;


        public NotificationsViewHolder(@NonNull View itemView) {
            super(itemView);

            imgProfile =  itemView.findViewById(R.id.imgProfileDoc);
            docfirstname= itemView.findViewById(R.id.doc_noti_Fname);
            doclastname = itemView.findViewById(R.id.doc_noti_Lname);
            docspecialist = itemView.findViewById(R.id.doc_noti_Specialist);
            docgender= itemView.findViewById(R.id.doc_noti_Gender);
            docprice = itemView.findViewById(R.id.doc_noti_Price);
            username = itemView.findViewById(R.id.user_noti_username);
            date = itemView.findViewById(R.id.noti_Date);
            time = itemView.findViewById(R.id.noti_Time);
            purpose = itemView.findViewById(R.id.noti_Purpose);

//            linearLayout = itemView.findViewById(R.id.linear_layout_notification);
//            expandableLayout = itemView.findViewById(R.id.expandable_layout_notification);

//            linearLayout.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Booking bookings = bookingList.get(getAdapterPosition());
//                    bookings.setExpandable(!bookings.isExpandable());
//                    notifyItemChanged(getAdapterPosition());
//                }
//            });

        }
    }
}

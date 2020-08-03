package com.poll.adapterwearable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.poll.Url.url;
import com.poll.mobilehealthwearable.R;
import com.poll.model.staffs;
import com.squareup.picasso.Picasso;


import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class StaffAdapter extends RecyclerView.Adapter<StaffAdapter.StaffsViewHolder> {
    Context mContext;
    List<staffs> staffsList;

    public StaffAdapter(Context mContext, List<staffs> staffsList) {
        this.mContext = mContext;
        this.staffsList=staffsList;
    }


    @NonNull
    @Override
    public StaffsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_dashboard, parent, false);
            return new StaffsViewHolder(v);

    }


    @Override
    public void onBindViewHolder(@NonNull StaffsViewHolder holder, int i) {
        final staffs staffs = staffsList.get(i);
        String imgPath = url.imagePath+staffs.getImage();

        Picasso.get().load(imgPath).into(holder.imgProfile);

        holder.staffFirstName.setText(staffs.getFirstname());
        holder.staffLastName.setText(staffs.getLastname());
        holder.staffAge.setText(staffs.getAge());
       holder.staffGender.setText(staffs.getGender());
       holder.staffPrice.setText(staffs.getPrice());

    }

    @Override
    public int getItemCount() { return  staffsList.size(); }

    public class StaffsViewHolder extends RecyclerView.ViewHolder {

        CircleImageView imgProfile;
        TextView staffFirstName, staffLastName, staffAge, staffGender, staffPrice;

        public StaffsViewHolder (@NonNull View itemView ) {
            super(itemView);
//            imgProfile = itemView.findViewById(R.id.imgProfile);
//            staffFirstName = itemView.findViewById(R.id.staffFirstName);
//            staffLastName = itemView.findViewById(R.id.staffLastName);
//            staffAge = itemView.findViewById(R.id.staffAge);
//           staffGender = itemView.findViewById(R.id.stafftextGender);
//            staffPrice = itemView.findViewById(R.id.viewholder_staff_price);
        }
    }
}

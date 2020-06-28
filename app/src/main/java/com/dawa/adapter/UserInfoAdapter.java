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

import com.dawa.model.users;
import com.dawa.url.url;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserInfoAdapter extends RecyclerView.Adapter<UserInfoAdapter.UserInfoViewHolder> {

    Context mContext;
    List<users> usersList;
    private List<users> filteruserList;

    public UserInfoAdapter (Context mContext, List<users> usersList) {

        this.mContext = mContext;
        this.usersList = usersList;
        filteruserList = new ArrayList<>(usersList);

    }

    @NonNull
    @Override
    public UserInfoAdapter.UserInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_admin_userinfo_details,parent,false);
        return new UserInfoViewHolder(v);

    }

    private void StrictMode() {
        StrictMode.ThreadPolicy policy =
                new StrictMode.ThreadPolicy.Builder()
                        .permitAll().build();

        StrictMode.setThreadPolicy(policy);
    }

    @Override
    public void onBindViewHolder(@NonNull UserInfoAdapter.UserInfoViewHolder holder, int i) {

        final users users = usersList.get(i);
        String imgPath = url.imagePath+users.getImage();
        holder.firstname.setText(users.getFirstname());
        holder.lastname.setText(users.getLastname());
        holder.age.setText(users.getAge());
        holder.address.setText(users.getAddress());
        holder.gender.setText(users.getGender());
        holder.phone.setText(users.getPhone());
        holder.email.setText(users.getEmail());
        holder.username.setText(users.getUsername());
        Picasso.get().load(imgPath).into(holder.imgProfile);

        boolean isExpandble = usersList.get(i).isExpandable();
        holder.expandableLayout.setVisibility(isExpandble ? View.VISIBLE : View.GONE);

    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public Filter getFilter()

    {
        return userssfilter;
    }

    private Filter userssfilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<users> filteredList= new ArrayList<>();
            if (constraint == null || constraint.length()==0){
                filteredList.addAll(filteruserList);
            }else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (users users : filteruserList){
                    if(users.getUsername().toLowerCase().contains(filterPattern)){
                        filteredList.add(users);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            usersList.clear();
            usersList.addAll((List)results.values);
            notifyDataSetChanged();

        }
    };


    public class UserInfoViewHolder extends RecyclerView.ViewHolder {

        CircleImageView imgProfile;
        TextView firstname, lastname, address, age, gender, email, phone, username;
        LinearLayout linearLayout;
        RelativeLayout expandableLayout;

        public UserInfoViewHolder(@NonNull View itemView) {
            super(itemView);

            imgProfile = itemView.findViewById(R.id.imguserimage);
            firstname = itemView.findViewById(R.id.user_firstname);
            lastname = itemView.findViewById(R.id.user_lastname);
            age = itemView.findViewById(R.id.user_age);
            address = itemView.findViewById(R.id.user_address);
            gender = itemView.findViewById(R.id.user_gender);
            email = itemView.findViewById(R.id.user_email);
            phone = itemView.findViewById(R.id.user_phone);
            username = itemView.findViewById(R.id.user_username);

            linearLayout = itemView.findViewById(R.id.linear_layout_adminuserinfo);
            expandableLayout = itemView.findViewById(R.id.expandable_layout_adminuserdetails);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    users users = usersList.get(getAdapterPosition());
                    users.setExpandable(!users.isExpandable());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}

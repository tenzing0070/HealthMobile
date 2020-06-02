package com.dawa.mobilehealth;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;


import com.dawa.api.health_api;
import com.dawa.model.feedbacks;
import com.dawa.url.url;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedbackActivity extends Fragment {

    EditText email, message;
    Button btnFeedback;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_feedback, container, false);

        email = view.findViewById(R.id.txtFeedbackEmail);
        message = view.findViewById(R.id.txtFeedbackMessage);

        btnFeedback = view.findViewById(R.id.btnSendFeedback);

        btnFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateFeedback();
            }

        });
//                feedbacks userFeedback = new feedbacks(email.getText().toString(),
//                        message.getText().toString());
        return view;

    }
    private void updateFeedback () {
        feedbacks feedbacks = new feedbacks(
                email.getText().toString(),
                message.getText().toString()
        );
                health_api feedbackApi = url.getInstance().create(health_api.class);
                Call<Void> feedbackCall = feedbackApi. feed(feedbacks);
                feedbackCall.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(!response.isSuccessful()) {
                            Toast.makeText(getActivity(), "The email address has been already used by someone. Please use your own email address:", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Toast.makeText(getActivity(), "Submitted", Toast.LENGTH_SHORT).show();
                        email.getText().clear();
                        message.getText().clear();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(getActivity(), "Error:" + t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();

                    }
                });
            }
}

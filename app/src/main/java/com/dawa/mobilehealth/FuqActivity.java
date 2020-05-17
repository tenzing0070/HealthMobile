package com.dawa.mobilehealth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dawa.adapter.FaqsAdapter;
import com.dawa.fragment.ProfileFragment;
import com.dawa.mobilehealth.login.LoginActivity;
import com.dawa.model.Faqs;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class FuqActivity extends AppCompatActivity {

   RecyclerView recyclerView;
    List<Faqs> faqsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        recyclerView = findViewById(R.id.faq_recycler_view);

        initData();
        setRecyclerView();
    }

    private void setRecyclerView() {
        FaqsAdapter faqsAdapter = new FaqsAdapter(faqsList);
        recyclerView.setAdapter(faqsAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(FuqActivity.this));
    }

    private void initData() {

       faqsList = new ArrayList<>();
        faqsList.add(new Faqs("1. What is Mobile Health (mHealth)?",
                "mHealth (mobile health) is a general term for the use of mobile phones and " +
                        "other wireless technology in medical care. The most common application of mHealth " +
                        "is the use of mobile devices to educate consumers about preventive healthcare services." +
                        " However, mHealth is also used for disease surveillance, treatment support, epidemic outbreak" +
                        " tracking and chronic disease management."));
        faqsList.add(new Faqs("2. How does Mobile Health benefits?","For consumers," +
                " a major benefit of mHealth is its convenience. Wearable devices and other mobile technology " +
                "allow users to continuously track and manage certain health data without having to see their healthcare" +
                " provider. There are also a plethora of apps to choose from: As of 2017, there were 325,000 mHealth apps" +
                " available for download from app stores, according to digital health consulting firm research2guidance."));
        faqsList.add(new Faqs("3. How many Mobie Health app are there?","There are now 318,000 mHealth apps" +
                " available in major app stores. That number has nearly doubled since 2015 driven by increased smartphone " +
                "adoption and ongoing heavy investment in the digital health market."));
        faqsList.add(new Faqs("4. What are Mobile Health Applications?","Mobile health apps display more accurate health " +
                "information and transparent medical charge information. They also provide patients with more opportunities to communicate with" +
                " physicians and may improve the relationship between physicians and patients."));
        faqsList.add(new Faqs("5. How Mobile Health Application works?","mHealth apps serve as virtual notebooks with which users can track" +
                " everything from steps to individual meals, their exercise regimens, even sleep patterns. Organized, user-friendly data tracking makes " +
                "it easier for mHealth users to set health-oriented goals and track their data as they attempt to achieve them."));
        faqsList.add(new Faqs("6. Are health apps effective?","The study defines prescribable health apps as those that are currently" +
                " accessible, have been proven effective and are preferably stand-alone. Eleven out of the 23 apps, less than half, showed positive health" +
                " outcomes for users attributable to the apps."));
        faqsList.add(new Faqs("7. Do health apps improve patient care?","The use of mobile health apps could improve patient experience," +
                " especially with regard to accessing health information, making physician-patient communication more convenient, ensuring transparency in " +
                "medical charge, and ameliorating short-term outcomes. All of these may contribute to positive health outcomes."));
        faqsList.add(new Faqs("8. How does Mobile Health save me money?","Mobile Health is personalized based on your health, wellness programs, and benefit plan. The platform uses" +
                "your health profile, which includes information such as your age, gender, geographic location, health risks," +
                "health plan selections, and even life events such as a new baby. It provides guidance on how best to utilize" +
                "your chosen health plan based on your health profile and health resources. For example, Mobile Health can" +
                "help you find healthcare providers in your insurance network or compare costs for health services. The" +
                "platform helps you understand your co-pays or services that your medical plan covers. You may also be able" +
                "to submit reimbursement requests for items such as gym memberships."));
        faqsList.add(new Faqs("9. Is there a charge to download Mobile Health Application? ","No, the Mobile Health app is free to download " +
                "from the iTunes or Google Play store."));
        faqsList.add(new Faqs("10. What if I do not have a smart phone? ","The Mobile Health platform is accessible via smartphone, tablet, smart watch, or the web. The platform" +
                "has the same “look and feel” regardless of the device you choose to use. "));
        faqsList.add(new Faqs("11. What if I have questions about my benefits or health programs? ","Select “Help Center” on the Navigation Menu to access HR and Benefits contact information. You can also" +
                "find answers to frequently asked questions."));
    }

}

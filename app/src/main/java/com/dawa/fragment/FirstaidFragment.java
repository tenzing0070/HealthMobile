package com.dawa.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dawa.adapter.FirstaidAdapter;
import com.dawa.mobilehealth.R;
import com.dawa.model.Instructions;

import java.util.ArrayList;
import java.util.List;

public class FirstaidFragment extends Fragment {

    private SearchView searchinjury;
    private Button btnSearch;
    FirstaidAdapter firstaidAdapter;

    RecyclerView recyclerView;
    List<Instructions> instructionsList;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_firstaid, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewFirstAid);
        searchinjury = view.findViewById(R.id.search_view);

        searchinjury.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(firstaidAdapter !=null){
               firstaidAdapter.getFilter().filter(newText);}
                return false;
            }
        });

        initData();
        setRecyclerView();
        return view;
    }

    private void setRecyclerView() {
        FirstaidAdapter firstaidAdapter = new FirstaidAdapter(instructionsList);
        recyclerView.setAdapter(firstaidAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void initData() {

        instructionsList = new ArrayList<>();

        instructionsList.add(new Instructions("Abdominal Pain", "Treatment For Symptoms:", ".GERD - Heartburn" +
                "from gastroesophagel reflux disease. Take an over the counter antacid.\n .Constipation - Take a laxative or mind stool softener.\n" +
                ".Pain - Take acetaminophen(Aspirin Free Anacin, Liquiprin,Panadol,Tylenol) and Avoid aspirin and ibuprofen(Advil,Midol,Motrin)",R.drawable.abdominalpain));
        instructionsList.add(new Instructions("Animal Bites", "Treatment for Symptopms", ".Clean wound with soap and warm water." +
                "Rinse for serveral minute after cleaning.\n.Reduce infection for wound to apply antibiotic cream.\n.Cover the wound with a sterile bandage.\n.If bleeding" +
                "continues, apply direct pressure until bleeding stops.", R.drawable.animalbites));
        instructionsList.add(new Instructions("Blood in Urine", "Treatment for Symptopms", ".Note the color of blood in urine.\n.Blood clots in urine. If so" +
                "what shape? \n.Person had any pain when urinate?\n.Blood with urine at beginning or end.", R.drawable.bloodinurine));
        instructionsList.add(new Instructions("Broken Toe","Treatment For Symptoms",".Pain - Take" +
                "ibuprofen or acetaminophen to reduce pain.\n.Stay off the foot and avoid pressure.\n.Control pain and serlling-keep the foot elevated as oftern possible.\n" +
                ".Wrap with clothe or towel and apply ice.\n.Stay off the foot and avoid pressure.", R.drawable.brokentoe));
        instructionsList.add(new Instructions("Burns","Treatment For Symptoms",".To stop fire on person using hot liquid, steam gases or use fire" +
                "stooping materials\n.Roll the person to smother flames.\n.Remove burned clothing from person.\n.Avoid swelling to " +
                "remove jewelry, belts,tight clothing", R.drawable.burns));
        instructionsList.add(new Instructions("Chocking","Treatment For Symptoms",".Give up to 5 blows between the shoulder " +
                "blades with your hand.\n.If the person is not pregnant or obese, do abdonimal thrusts.\n.Stand behind the person" +
                "and wrap your arms around the waist.", R.drawable.chocking));
        instructionsList.add(new Instructions("Cuts and Wounds","Treatment For Symptoms","Apply" +
                "direct pressure on the area.\n.Clean with warm water.\n.Apply an antibiotic ointment.\n.Put sterile bandage on the area.", R.drawable.cutsnwounds));
        instructionsList.add(new Instructions("Diarrhoea","Treatment For Symptoms",".Give a child" +
                "or adult plenty of clear fluids like water or Indalyte, clear broth.\n.Avoid milk or milk-based product, alcohol," +
                "apple juice,caffeine while you have diarrhoea.\n.Make sure person drinks more fluids than they are losing through " +
                "diarrhoea.", R.drawable.diarrhoea));
        instructionsList.add(new Instructions("Food Poisoning", "Treatment For Symptoms",".Avoid solid" +
                "foods until vomitting ends.Then ear light balanced food such as crackers or bread\n.Sipping soda may help prevent" +
                "vomiting.\n.Dont eat fried, greasy or sweet foods.\n.Prevent dehydration.", R.drawable.foodpoisioin));
        instructionsList.add(new Instructions("Heatstroke","Treatment For Symptoms",".Heatstroke is " +
                "a medical emergency.\n.Get the person out of the sun.\n.Immerse the person in a cool bath, spray the person with water" +
                "or apply cold wet cloths to the armoits, neck and groin.\n.Continue cooling efforts until the persons body temperature" +
                "drop to 101 to 102 F.", R.drawable.heatstroke));
        instructionsList.add(new Instructions("Nosebleeds","Treatment For Symptoms",".Have the person" +
                "sit up straight and lean forward slightly. Dont have the person liw down or tilt the head backwards.\n.With thumb and index" +
                "finger, firmly pinch the nose just below the bone up against the face.\n.Apply pressure for 5 minutes. Time yourself with clock\n." +
                "If bleeding continues after 5 minutes, repeat the process", R.drawable.nosebleed));
        instructionsList.add(new Instructions("Spider Bites", "Treatment For Symptoms","Scrape the area" +
                "with a fingernail or use tweezers to remove.\n.Dont pich the stinger--that can inject more venom\n.Ice the area.\n." +
                "If you are stung on your arm or leg, elevate it", R.drawable.spiderbites));
        instructionsList.add(new Instructions("Sunburn","Treatment For Symptoms",".The burn has blister or the skin" +
                "is white appearingm symptoms of more serious sunburn.\n.Replace body fluids with water,juice,or sports drinks.\n." +
                "Apply aloe or over the counter moisturizing lotion to skin directed.", R.drawable.sunburn));
        instructionsList.add(new Instructions("Vertigo","Treatment For Symptoms",".Have the person" +
                "lie down and rest.\n.The person should avoid sudden changes in body position.\n.Help the person avoid abrupt" +
                "head movements, especially looking up.\n.Help the person avoid falls.\n.The person should not drive or operate" +
                "dangerous machine.", R.drawable.vertigo));


    }
}

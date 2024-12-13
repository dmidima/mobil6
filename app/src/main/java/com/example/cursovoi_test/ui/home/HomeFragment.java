package com.example.cursovoi_test.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cursovoi_test.PersonalityType;
import com.example.cursovoi_test.R;
import com.example.cursovoi_test.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private SharedPreferences sharedPreferences;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView recyclerView = root.findViewById(R.id.recyclerView); // Find RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<PersonalityType> personalityTypes = new ArrayList<>();
        personalityTypes.add(new PersonalityType(1, "INTJ", "Стратег. Стратеги – это творческие и стратегические мыслители, у которых на все есть план."));
        personalityTypes.add(new PersonalityType(2, "INTP", "Ученый. Ученые – это изобретатели-новаторы с неутолимой жаждой знаний."));
        personalityTypes.add(new PersonalityType(3, "ISTJ", "Администратор. Администраторы – это практичные и опирающиеся на факты личности, в надежности которых сложно усомниться."));
        personalityTypes.add(new PersonalityType(4, "ISFJ", "Защитник. Защитники – это очень преданные и заботливые защитники, всегда готовые оберегать своих близких."));
        personalityTypes.add(new PersonalityType(5, "INFJ", "Активист. Активисты – это тихие мечтатели, часто являющиеся вдохновляющими и неутомимыми идеалистами."));
        personalityTypes.add(new PersonalityType(6, "ISTP", "Виртуоз. Виртуозы – это новаторы и практичные экспериментаторы, мастера на все руки."));
        personalityTypes.add(new PersonalityType(7, "ISFP", "Артист. Артисты – это гибкие и обаятельные люди, всегда готовые исследовать и испытать что-то новое."));
        personalityTypes.add(new PersonalityType(8, "INFP", "Посредник. Посредники – это поэтичные, добрые и альтруистичные люди, всегда готовые помочь в хорошем деле."));
        personalityTypes.add(new PersonalityType(9, "ESTP", "Делец. Дельцы – это сообразительные, энергичные и очень проницательные люди, которым действительно нравится жить на грани."));
        personalityTypes.add(new PersonalityType(10, "ESFP", "Развлекатель. Развлекатели – это спонтанные, энергичные энтузиасты, рядом с которыми жизнь никогда не бывает скучной."));
        personalityTypes.add(new PersonalityType(11, "ENFP", "Борец. Борцы – это энтузиасты, креативные и социальные люди, которые всегда найдут причину, чтобы улыбнуться."));
        personalityTypes.add(new PersonalityType(12, "ENTP", "Полемист. Полемисты – это любопытные и гибкие мыслители, которые не могут противостоять интеллектуальным вызовам."));
        personalityTypes.add(new PersonalityType(13, "ESTJ", "Менеджер. Менеджеры – это отличные организаторы, знающие толк в управлении вещами или людьми."));
        personalityTypes.add(new PersonalityType(14, "ESFJ", "Консул. Консулы – это очень заботливые, социальные, думающие об обществе люди, которые всегда готовы помочь."));
        personalityTypes.add(new PersonalityType(15, "ENFJ", "Тренер. Тренеры – это вдохновляющие оптимисты, готовые сразу действовать, чтобы сделать то, что считают правильным."));
        personalityTypes.add(new PersonalityType(16, "ENTJ", "Командир. Командиры – это смелые люди с хорошим воображением и силой воли, они всегда найдут путь или проложат его сами."));


        com.example.cursovoi_test.ui.home.PersonalityTypeAdapter adapter = new com.example.cursovoi_test.ui.home.PersonalityTypeAdapter(personalityTypes);
        recyclerView.setAdapter(adapter);

        return root;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

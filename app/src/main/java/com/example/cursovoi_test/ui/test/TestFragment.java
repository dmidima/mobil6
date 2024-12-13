package com.example.cursovoi_test.ui.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cursovoi_test.R;
import com.example.cursovoi_test.TestActivity;

import com.example.cursovoi_test.databinding.FragmentTestBinding;
import com.example.cursovoi_test.MainActivity;
import com.example.cursovoi_test.databinding.FragmentTestBinding;
public class TestFragment extends Fragment {

    private FragmentTestBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        TestViewModel dashboardViewModel =
                new ViewModelProvider(this).get(TestViewModel.class);

        binding = FragmentTestBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button showDocsButton = root.findViewById(R.id.showDocsButton);
        showDocsButton.setOnClickListener(v -> {
            MainActivity mainActivity = (MainActivity) requireActivity();
            mainActivity.showDocumentation();
        });

        Button startTestButton = binding.startTestButton;
        startTestButton.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), TestActivity.class);
            startActivity(intent);
        });


        final TextView textView = binding.textTest;
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
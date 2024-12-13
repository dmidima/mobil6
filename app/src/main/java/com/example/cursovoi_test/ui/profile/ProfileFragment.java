package com.example.cursovoi_test.ui.profile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cursovoi_test.DBHelper;
import com.example.cursovoi_test.DividerItemDecoration;
import com.example.cursovoi_test.LoginActivity;

import com.example.cursovoi_test.R;
import com.example.cursovoi_test.TestResult;
import com.example.cursovoi_test.databinding.FragmentProfileBinding;

import java.util.List;







public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private SharedPreferences sharedPreferences;
    private RecyclerView recyclerView;
    private TestResultAdapter adapter;
    private DBHelper dbHelper;
    private ProfileViewModel viewModel;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        dbHelper = new DBHelper(context);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new TestResultAdapter(dbHelper.getTestResultsForUser(sharedPreferences.getString("username", "")));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));


        Button logoutButton = binding.logoutButton;
        logoutButton.setOnClickListener(v -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove("username");
            editor.apply();
            Intent intent = new Intent(getContext(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });

        TextView userNameTextView = binding.userNameTextView;
        String username = sharedPreferences.getString("username", "");
        userNameTextView.setText("Пользователь: " + username);

        TextView textView = binding.textProfile;
        viewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        TextView quoteTextView = binding.quoteTextView;
        LiveData<String> quoteLiveData = viewModel.getQuoteText();
        quoteLiveData.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String quote) {
                quoteTextView.setText(quote);
            }
        });

        return root;
    }








    private class TestResultAdapter extends RecyclerView.Adapter<TestResultAdapter.ViewHolder>{
        private List<TestResult> testResults;

        public TestResultAdapter(List<TestResult> testResults){
            this.testResults = testResults;
        }

        @NonNull
        @Override
        public TestResultAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_result_item, parent, false); //Создайте test_result_item.xml
            return new ViewHolder(view);
        }

//        @Override
//        public void onBindViewHolder(@NonNull TestResultAdapter.ViewHolder holder, int position) {
//            TestResult testResult = testResults.get(position);
//            holder.dateTextView.setText(testResult.resultDate);
//            holder.resultTextView.setText(testResult.personalityType);
//        }


        @Override
        public void onBindViewHolder(@NonNull TestResultAdapter.ViewHolder holder, int position) {
            TestResult testResult = testResults.get(position);
            holder.dateTextView.setText(testResult.resultDate);
            holder.resultTextView.setText(testResult.personalityType);
            holder.descriptionTextView.setText(testResult.personalityDescription); // Отображение описания


            //Optional Styling (requires colors in colors.xml)
            holder.dateTextView.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.black));
            holder.resultTextView.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.colorAccent));
            holder.descriptionTextView.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.teal_200));


        }

        @Override
        public int getItemCount() {
            return testResults.size();
        }

//        public class ViewHolder extends RecyclerView.ViewHolder{
//            TextView dateTextView;
//            TextView resultTextView;
//            public ViewHolder(@NonNull View itemView){
//                super(itemView);
//                dateTextView = itemView.findViewById(R.id.dateTextView);
//                resultTextView = itemView.findViewById(R.id.resultTextView);
//            }
//        }


        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView dateTextView;
            TextView resultTextView;
            TextView descriptionTextView; // Добавлено

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                dateTextView = itemView.findViewById(R.id.dateTextView);
                resultTextView = itemView.findViewById(R.id.resultTextView);
                descriptionTextView = itemView.findViewById(R.id.descriptionTextView); // Инициализация
            }
        }
    }






    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
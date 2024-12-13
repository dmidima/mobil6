package com.example.cursovoi_test.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cursovoi_test.PersonalityType;
import com.example.cursovoi_test.R;

import java.util.List;

public class PersonalityTypeAdapter extends RecyclerView.Adapter<PersonalityTypeAdapter.ViewHolder> {

    private List<PersonalityType> personalityTypes;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView descriptionTextView;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }

    public PersonalityTypeAdapter(List<PersonalityType> personalityTypes) {
        this.personalityTypes = personalityTypes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.personality_type_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PersonalityType personalityType = personalityTypes.get(position);
        holder.nameTextView.setText(personalityType.name);
        holder.descriptionTextView.setText(personalityType.description);

        //Optional Styling (requires colors in colors.xml)
        holder.cardView.setCardBackgroundColor(holder.itemView.getContext().getResources().getColor(R.color.background_color1));
        holder.nameTextView.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.colorAccent));
        holder.descriptionTextView.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.teal_200));
    }

    @Override
    public int getItemCount() {
        return personalityTypes.size();
    }
}

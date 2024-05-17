package com.example.nutrition_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<FoodList> meal;

    public void setMeals(ArrayList<FoodList> meal1) {
        this.meal = meal1;
    }

    public ListAdapter(Context context) {
        this.context = context;
        meal = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return meal.size();
    }

    @Override
    public Object getItem(int i) {
        return meal.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_hero, viewGroup, false);
        };

        ViewHolder viewHolder = new ViewHolder(view);
        FoodList foodList = (FoodList) getItem(i);
        viewHolder.bind(foodList);
        return view;
    }

    private class ViewHolder {
        private TextView txtName;
        private TextView txtDescription;
        private ImageView imgPhoto;

        ViewHolder(View view) {
            txtName = view.findViewById(R.id.txt_name);
            txtDescription = view.findViewById(R.id.txt_description);
            imgPhoto = view.findViewById(R.id.img_photo);
        }

        void bind(FoodList hero) {
            txtName.setText(hero.getName());
            txtDescription.setText(hero.getDescription());
            imgPhoto.setImageResource(hero.getPhoto());
        }
    }
}

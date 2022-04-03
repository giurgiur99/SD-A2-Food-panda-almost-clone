package com.example.a2.DTO;

import com.example.a2.model.Categories;
import com.example.a2.model.Food;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class MenuDTO {

    private Categories categories;
    private List<Food> foods = new ArrayList<Food>();

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }
}

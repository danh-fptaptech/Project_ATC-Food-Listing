package vn.hdweb.team9.repository.foodDAO;

import vn.hdweb.team9.domain.entity.Food;
import vn.hdweb.team9.domain.entity.RatingFood;

import java.util.List;

public interface FoodDAO {
    // save food and return saved Id
    void save(Food food);
    
    // find all food
    List<Food> findAll();
    
    // find food by id
    Food finById(Long foodId);
    
    // find food by name
    List<Food> findByName(String foodName);
    
    // find food by slug
    List<Food> findBySlug(String foodSlug);
    
    // find ratings by food id
    List<RatingFood> findRatingsByFoodId(Long foodId);
    
    // get ratings and foods at times
    Food findFoodByIdJoinFetch(Long foodId);
    
    // delete food by id
    void deleteById(Integer foodId);
    
    // update food
    void update(Food food);
}

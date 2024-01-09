package vn.hdweb.team9.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.hdweb.team9.domain.dto.respon.RatingFoodDto;
import vn.hdweb.team9.domain.dto.respon.RatingRestaurantDto;
import vn.hdweb.team9.domain.entity.RatingFood;
import vn.hdweb.team9.domain.entity.RatingRestaurant;
import vn.hdweb.team9.service.RatingFoodService;
import vn.hdweb.team9.service.RatingRestaurantService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
@Slf4j
public class ClientController {
    private final RatingFoodService ratingFoodService;
    private final RatingRestaurantService ratingRestaurantService;


    @GetMapping(value = {"", "/","/home","/index","/trang-chu","index.html","home.html","trang-chu.html"})
    public String home() {


        return "client/index";
    }

    @GetMapping("/category_demo")
    public String category_demo() {
        return "client/category_page";
//        return "client/food_page";

    }

    @GetMapping("/restaurant_demo")
    public String restaurant_demo() {
        return "client/restaurant_page";
    }

    @GetMapping("/restaurant_demo/{restaurantId}")
    public String restaurant_demo_detail(@PathVariable("restaurantId") Long restaurantId, Model model) {
        List<RatingRestaurantDto> ratingList = ratingRestaurantService.getRatingsByRestaurantId(restaurantId);
        Double ratingValue = ratingRestaurantService.calculateAverageRatingByRestaurantId(restaurantId);
        long countRating=ratingRestaurantService.countRating(restaurantId);

        model.addAttribute("restaurantId", restaurantId);
        model.addAttribute("ratingList", ratingList);
        model.addAttribute("ratingValue", ratingValue);
        model.addAttribute("countRating", countRating);

        return "client/restaurant_page";
    }


    @GetMapping("/food_demo")
    public String food_demo() {
        return "client/food_page";
    }


//    ratingFood detail by id
    @GetMapping("/food_demo/{foodId}")
    public String food_demo_detail(@PathVariable("foodId") Long foodId, Model model) {
        List<RatingFoodDto> ratingList = ratingFoodService.getRatingsByFoodId(foodId);
        Double ratingValue = ratingFoodService.calculateAverageRatingByFoodId(foodId);
        long countRating=ratingFoodService.countRating(foodId);
        log.info("Check rating list"+ ratingList);
        log.info("Check rating value"+ ratingValue);
        log.info("Check count" + countRating);

        model.addAttribute("foodId", foodId);
        model.addAttribute("ratingList", ratingList);
        model.addAttribute("ratingValue", ratingValue);
        model.addAttribute("countRating", countRating);
        return "client/food_page";
    }

    @GetMapping("/post_demo")
    public String post_demo() {
        return "client/post_page";
    }

    @RequestMapping("/404")
    public String notFound() {
        return "client/404";
    }
}

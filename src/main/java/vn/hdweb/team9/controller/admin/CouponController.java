package vn.hdweb.team9.controller.admin;

import jakarta.validation.Valid;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.hdweb.team9.domain.dto.CouponForm;
import vn.hdweb.team9.domain.dto.CouponSearch;
import vn.hdweb.team9.domain.entity.Coupon;
import vn.hdweb.team9.domain.entity.CouponStatus;
import vn.hdweb.team9.domain.entity.Order;
import vn.hdweb.team9.service.CouponService;

import java.time.DateTimeException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/coupon")
public class CouponController {
    private final CouponService couponService;

    @Autowired
    public CouponController(CouponService couponService) { this.couponService = couponService; }

    @GetMapping("/new")
    public String createCoupon(Model model) {
        CouponForm couponForm = new CouponForm();
        model.addAttribute("couponForm", couponForm);
        return "admin/coupon/createCoupon";
    }


    @PostMapping("/new")
    public String createCoupon(@Valid CouponForm couponForm,
                               BindingResult result,
                               RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "admin/coupon/createCoupon";
        }

        try {
            Coupon coupon = Coupon.parseNew(couponForm);
            couponService.create(coupon);
            return "redirect:/admin/coupon/list";
        } catch (IllegalStateException e) {
            // Handle the exception for duplicate coupon code
            result.rejectValue("couponCode", "duplicate.couponCode", "This coupon code has already existed!");
            redirectAttributes.addFlashAttribute("couponForm", couponForm);
            return "admin/coupon/createCoupon";
        } catch (DateTimeException e) {
            // Handle the exception for invalid start and end dates
            result.rejectValue("startDate", "invalid.dateRange", "Start date cannot be after or equal to end date");
            redirectAttributes.addFlashAttribute("couponForm", couponForm);
            return "admin/coupon/createCoupon";
        }
    }

    @GetMapping("/list")
    public String couponList(@ModelAttribute("couponSearch")CouponSearch couponSearch, Model model) {
        List<Coupon> coupons = couponService.findCoupons(couponSearch);
        model.addAttribute("coupons", coupons);
        return "admin/coupon/couponList";
    }

    @PostMapping("/delete/{id}")
    public String deleteCoupon(@PathVariable("id") Long id) {
        couponService.deleteOne(id);
        return "redirect:/admin/coupon/list";
    }

    @GetMapping("/update/{id}")
    public String updateCoupon(@PathVariable("id") Long id, Model model) {
        Optional<Coupon> coupon = couponService.findOne(id);

        if (coupon.isEmpty()) {
            throw new IllegalStateException("This coupon does not exist");
        }

        Coupon getCoupon = coupon.get();
        CouponForm couponForm = CouponForm.parseNew(getCoupon);

        model.addAttribute("couponForm", couponForm);
        return "admin/coupon/updateCoupon";
    }

    @PostMapping("/update/{id}")
    public String updateCoupon(CouponForm couponForm,
                               BindingResult result,
                               RedirectAttributes redirectAttributes) {
        try {
        couponService.update(couponForm);
        return "redirect:/admin/coupon/list";
        } catch (DateTimeException e) {
            // Handle the exception for invalid start and end dates
            result.rejectValue("startDate", "invalid.dateRange", "Start date cannot be after or equal to end date");
            redirectAttributes.addFlashAttribute("couponForm", couponForm);
            return "admin/coupon/updateCoupon";
        }
    }



}




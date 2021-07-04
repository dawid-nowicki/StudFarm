package com.company.StudFarm.controllers;


import com.company.StudFarm.entities.Horse;
import com.company.StudFarm.entities.Rental;
import com.company.StudFarm.entities.User;
import com.company.StudFarm.services.HorseService;
import com.company.StudFarm.services.RentalService;
import com.company.StudFarm.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

@Controller
public class UserController {
   private RentalService rentalService;
   private HorseService horseService;
   private UserService userService;

    @Autowired
    public UserController(RentalService rentalService, HorseService horseService, UserService userService) {
        this.horseService = horseService;
        this.rentalService = rentalService;
        this.userService = userService;
    }

    @GetMapping("rental")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    public String GetUserRentals(HttpServletRequest servletRequest, Model model){
        List<Rental> rentals = rentalService.GetAllByEmail(servletRequest.getUserPrincipal().getName());
        model.addAttribute("rentals", rentals);
        return "rentals";
    }

    @GetMapping("riding")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    public String GetRidingPage(Model model){
        List<Horse> horses = horseService.GetAll();
        model.addAttribute("horses", horses);
        List<List<Object>> datesAll = new LinkedList<>();
        for (Horse horse : horses){
            List<Object> horseDates= new LinkedList<>();
            horseDates.add(horse.getName());
            horseDates.add(rentalService.GetFreeDatesByHorse(horse));
            datesAll.add(horseDates);
        }
        model.addAttribute("dates", datesAll);
        model.addAttribute("rental", new Rental());
        return "riding";
    }

    @PostMapping("reserve")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    public String Reserve(@ModelAttribute("rental") Rental rental, HttpServletRequest servletRequest){
        if(rentalService.GetByDateAndHorse(rental.getDate(),rental.getHorse())!=null){
            return "redirect:/riding?error=true";
        }
        Calendar calendar = Calendar.getInstance();
        Date today = new Date(calendar.getTime().getTime());
        calendar.add(Calendar.DAY_OF_YEAR,31);
        Date lastDay = new Date(calendar.getTime().getTime());
        if (rental.getHorse() == null || rental.getDate().before(today) || rental.getDate().after(lastDay)){
            return "redirect:/riding?validation_error=true";
        }
        rental.setCustomer((User)userService.loadUserByUsername(servletRequest.getUserPrincipal().getName()));
        if(rental.equals(rentalService.AddRental(rental))){
            return "redirect:/riding?reserved=true";
        }else {
            return "redirect:/riding?error=true";
        }
    }
}

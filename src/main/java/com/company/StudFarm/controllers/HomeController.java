package com.company.StudFarm.controllers;

import com.company.StudFarm.entities.Horse;
import com.company.StudFarm.entities.Stable;
import com.company.StudFarm.entities.User;
import com.company.StudFarm.security.UserRole;
import com.company.StudFarm.services.HorseService;
import com.company.StudFarm.services.RentalService;
import com.company.StudFarm.services.StableService;
import com.company.StudFarm.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    private final UserService userService;
    private final HorseService horseService;
    private final StableService stableService;
    private final RentalService rentalService;

    @Autowired
    public HomeController(UserService userService, StableService stableService, HorseService horseService, RentalService rentalService) {
        this.userService = userService;
        this.horseService = horseService;
        this.stableService = stableService;
        this.rentalService = rentalService;

    }

    @RequestMapping("/index")
    public String getHomePage() {
        return "index";
    }

    @GetMapping("/stables")
    public String getStablesPage(Model model){
        List<Stable> stables = stableService.GetAll();
        model.addAttribute("stables", stables);
        return "stables";
    }

    @GetMapping("/horses")
    public String getHorsesPage(Model model){
        List<Horse> horses = horseService.GetAll();
        model.addAttribute("horses", horses);
        return "horses";
    }

    @GetMapping("/login")
    public String GetLoginPage() {
        return "login";
    }

    @GetMapping("/singup")
    public String GetRegisterPage(Model model){
        User client = new User();
        model.addAttribute("client", client);
        return "singup";
    }

    @PostMapping("/register")
    public String Register(@ModelAttribute("client") User client){
        client.setRole(UserRole.ROLE_CLIENT.name());
        if(userService.CreateUser(client) == client) {
            return "redirect:/login?registerd=true";
        }else {
            return "redirect:/singup?error=true";
        }
    }

}

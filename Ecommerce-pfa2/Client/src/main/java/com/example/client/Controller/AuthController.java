package com.example.client.Controller;

import com.example.library.dto.CustomerDto;
import com.example.library.entities.Customer;
import com.example.library.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class AuthController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("customerDto", new CustomerDto());
        return "register";
    }


    @PostMapping("/do-register")
    public String processRegister(@Valid @ModelAttribute("customerDto") CustomerDto customerDto,
                                  BindingResult result, Model model) {

        try {
            if (result.hasErrors()) {
                model.addAttribute("customer",customerDto);
                return "register";

            }

            Customer customer = customerService.findByUsername(customerDto.getUsername());
            if (customer != null) {
                model.addAttribute("username", "Username have been registered");
                model.addAttribute("customerDto", customerDto);
                return "register";
            }if (customerDto.getPassword().equals(customerDto.getRepeatPassword())){
                customerDto.setPassword(passwordEncoder.encode(customerDto.getPassword()));
                    customerService.save(customerDto);
                    model.addAttribute("success", "Register Successfull");
                    model.addAttribute("customerDto", customerDto);
            }else{
                model.addAttribute("customerDto", customerDto);
                model.addAttribute("password", "Password is not the same");

                return "register";
            }



        }catch (Exception e ){
            model.addAttribute("errors","Server have run some problems");

        }
        return "register";
    }
}

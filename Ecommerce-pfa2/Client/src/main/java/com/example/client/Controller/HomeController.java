package com.example.client.Controller;

import com.example.library.dto.ProductDto;
import com.example.library.entities.Category;
import com.example.library.entities.Customer;
import com.example.library.entities.Product;
import com.example.library.entities.ShoppingCart;
import com.example.library.service.CategoryService;
import com.example.library.service.CustomerService;
import com.example.library.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CustomerService customerService;
    @RequestMapping(value = {"/index", "/"}, method = RequestMethod.GET)
    public String home(Model model, Principal principal, HttpSession httpSession){
        if(principal != null){
           httpSession.setAttribute("username", principal.getName());
            Customer customer = customerService.findByUsername(principal.getName());
            ShoppingCart cart = customer.getShoppingCart();
            httpSession.setAttribute("totalItems", cart.getTotalItems());

        }else {
            httpSession.removeAttribute("username");
        }
        return "shop";}
    @GetMapping("/home")
    public String index(Model model){
        List<Category> categories=categoryService.findAll();
        List<ProductDto> productsDto=productService.findAll();
        model.addAttribute("categories",categories);
        model.addAttribute("products",productsDto);
        return "index";
    }
    @GetMapping("/shop-kids")
    public String shopKids(Model model){
        List<Category> categories=categoryService.findAll();
        List<ProductDto> productsDto=productService.findAll();
        model.addAttribute("categories",categories);
        model.addAttribute("products",productsDto);
        return "shop-enfant";
    }
    @GetMapping("/shop-Women")
    public String shopWomen(Model model){
        List<Category> categories=categoryService.findAll();
        List<ProductDto> productsDto=productService.findAll();
        model.addAttribute("categories",categories);
        model.addAttribute("products",productsDto);
        return "shop-femme";
    }
    @GetMapping("/about")
    public String aboutPage() {
        return "about";
    }
    @GetMapping("/shop1")
    public String shop(Model model) {
        List<Category> categories=categoryService.findAll();
        List<ProductDto> productsDto=productService.findAll();
        model.addAttribute("categories",categories);
        model.addAttribute("products",productsDto);
        return "shop";
    }
    @GetMapping("/blog")
    public String blog() {
        return "blog";
    }

}

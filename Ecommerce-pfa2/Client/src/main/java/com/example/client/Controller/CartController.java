package com.example.client.Controller;

import com.example.client.config.CustomerServiceConfig;
import com.example.library.entities.Customer;
import com.example.library.entities.Product;
import com.example.library.entities.ShoppingCart;
import com.example.library.service.CustomerService;
import com.example.library.service.ProductService;
import com.example.library.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelExtensionsKt;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class CartController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ShoppingCartService cartService;
    @Autowired
    private ProductService productService;
    @GetMapping("/shoppingCart")
    public String cart(Model model, Principal principal, HttpSession httpSession){
       if (principal == null){
            return "redirect:/register";
        }
        String username = principal.getName();
        Customer customer = customerService.findByUsername(username);
        ShoppingCart shoppingCart = customer.getShoppingCart();
        if (shoppingCart == null){
            model.addAttribute("check", "No Item in cart");
        }

        httpSession.setAttribute("totalItems", shoppingCart.getTotalItems());
        model.addAttribute("subTotal", shoppingCart.getTotalPrices());
        model.addAttribute("shoppingCart", shoppingCart);

        return "shoppingCart";
    }
    @PostMapping("/add-to-cart")
    public String addItemToCart(
            @RequestParam("id") Long productId,
            @RequestParam(value = "quantity", required = false, defaultValue = "1") int quantity,
            Principal principal,
            HttpServletRequest request){
        if(principal == null){
           return "redirect:/login";

        }
        Product product = productService.getProductByID(productId);
        String username = principal.getName();
        Customer customer = customerService.findByUsername(username);
        ShoppingCart cart = cartService.addItemToCart(product, quantity, customer);
        return "redirect:" + request.getHeader("Referer");
    }
    @RequestMapping (value = "/update-cart", method = RequestMethod.POST, params = "action=update")
  public String updateCart(@RequestParam("quantity") int quantity,@RequestParam("id") Long productId, Model model,
                           Principal principal){
        if (principal == null){
            return "redirect:/login";
        }else {
            String username = principal.getName();
            Customer customer = customerService.findByUsername(username);
            Product product = productService.getProductByID(productId);
            ShoppingCart cart = cartService.updateItemInCart(product, quantity, customer);
            model.addAttribute("shoppingCart", cart);
            return "redirect:/shoppingCart";

        }

    }
    @RequestMapping(value = "/update-cart", method = RequestMethod.POST,  params = "action=delete" )
    public String deleteItemFromCart(@RequestParam("id") Long productId, Model model,
                                     Principal principal){
        if (principal == null){
            return "redirect:/login";
        }else {
            String username = principal.getName();
            Customer customer = customerService.findByUsername(username);
            Product product = productService.getProductByID(productId);
            ShoppingCart cart = cartService.deleteItemFromCart(product, customer);
            model.addAttribute("shoppingCart", cart);
            return "redirect:/shoppingCart";
        }
    }
}

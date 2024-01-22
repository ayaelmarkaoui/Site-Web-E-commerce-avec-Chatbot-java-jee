package com.example.admin.controller;

import com.example.library.dto.ProductDto;
import com.example.library.entities.Category;
import com.example.library.entities.Product;
import com.example.library.service.CategoryService;
import com.example.library.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;
    @GetMapping("/products")
    public String products(Model model){
        List<ProductDto> productDtoList= productService.findAll();
        model.addAttribute("title","Manage Product");
        model.addAttribute("products",productDtoList);
        model.addAttribute("size",productDtoList.size());

        return "products";
    }
    @GetMapping("/add-product")
    public String addProduct(Model model){
        List<Category> categories = categoryService.findAllByActivated();
        model.addAttribute("categories",categories);
        model.addAttribute("product",new ProductDto());
        return "add-product";
    }
    @PostMapping("/save-product")
    public String saveProduct(@ModelAttribute("product")ProductDto productDto,
                              @RequestParam("imageProduct") MultipartFile imageProduct,
                              RedirectAttributes attributes){
        try {
            productService.save(imageProduct, productDto);
            attributes.addFlashAttribute("success", "Add successfully!");
        }catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("error", "Failed to add!");
        }
        return "redirect:/products";
    }
    @GetMapping("/update-product/{id}")// @PathVariable pour extraire des valeurs de variables de modèle de chemin (path variables) dans les URL
    public String updateProductForm(@PathVariable("id") Long id, Model model , Principal principal){

        model.addAttribute("title","Update Product");
        List<Category> categories = categoryService.findAllByActivated();
        ProductDto productDto =productService.getById(id);
        model.addAttribute("categories",categories);
        model.addAttribute("productDto",productDto);
        return "update-product";

    }
    @PostMapping("/update-product/{id}")
    public String processUpdate(@PathVariable("id") Long id,@ModelAttribute("productDto") ProductDto productDto, @RequestParam("imageProduct")MultipartFile imageProduct,RedirectAttributes attributes){
        try{
            productService.update(imageProduct,productDto);
            attributes.addFlashAttribute("success", "Updated successfully!");
        }catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("error", "Failed to update!");
        }

        return "redirect:/products";
    }
    @RequestMapping(value = "/delete-product", method = {RequestMethod.DELETE,RequestMethod.GET})
    public String delete(Long id,RedirectAttributes attributes){
        try {
            productService.deleteById(id);
            attributes.addFlashAttribute("success","deleted Succefully");

        }catch (Exception e)
        {
            e.printStackTrace();
            attributes.addFlashAttribute("Failed","Deleted Failed");

        }

        return "redirect:/products";
    }
    @RequestMapping(value = "/enable-product",method = {RequestMethod.PUT,RequestMethod.GET})
    public String enable(Long id,RedirectAttributes attributes){
        try {
            productService.enabledById(id);
            attributes.addFlashAttribute("success","enabled Succefully");

        }catch (Exception e)
        {
            e.printStackTrace();
            attributes.addFlashAttribute("Failed","enabled Failed");

        }

        return "redirect:/products";

    }



}

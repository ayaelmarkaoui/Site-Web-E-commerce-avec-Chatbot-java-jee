package com.example.admin.controller;

import com.example.library.entities.Category;
import com.example.library.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @GetMapping("/categories")
    public String categories(Model model, Principal principal)//principale est utilisé pour obtenir des informations sur l'utilisateur actuellement authentifié dans le système.
    {
//        if (principal ==null) {
//            return "redirect:/login";redirection vers la page login il fait l'authentification et apres fait le traitement
//        }
        List<Category> categoryList=categoryService.findAll();
        model.addAttribute("title","categories");
        model.addAttribute("categories",categoryList);
        model.addAttribute("size",categoryList.size());
        model.addAttribute("categoryNew",new Category());
        return "categories";
    }
    @PostMapping("/add-category")
    public String add(@ModelAttribute("categoryNew") Category category, RedirectAttributes attributes){
        try {
            categoryService.save(category);
            attributes.addFlashAttribute("success","Added successful");

        }catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("failed","Failed");
        }
        return "redirect:/categories";
    }
    @RequestMapping(value = "/findById", method = {RequestMethod.PUT, RequestMethod.GET})
    @ResponseBody
    public Category findById(Long id){

        return categoryService.findById(id);
    }
    @GetMapping("/update-category")
    public String update(Category category,RedirectAttributes redirectAttributes){
        try {
            categoryService.update(category);
            redirectAttributes.addFlashAttribute("success","Updated Succefully");
        }catch (Exception e)
        {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("Failed","Updated Failed");

        }
        return "redirect:/categories";
    }
    @RequestMapping(value = "/delete-category", method = {RequestMethod.DELETE,RequestMethod.GET})
    public String delete(Long id,RedirectAttributes attributes){
        try {
            categoryService.deleteById(id);
            attributes.addFlashAttribute("success","deleted Succefully");

        }catch (Exception e)
        {
            e.printStackTrace();
            attributes.addFlashAttribute("Failed","Deleted Failed");

        }

        return "redirect:/categories";
    }
    @RequestMapping(value = "/enable-category",method = {RequestMethod.PUT,RequestMethod.GET})
    public String enable(Long id,RedirectAttributes attributes){
        try {
            categoryService.enabledById(id);
            attributes.addFlashAttribute("success","enabled Succefully");

        }catch (Exception e)
        {
            e.printStackTrace();
            attributes.addFlashAttribute("Failed","enabled Failed");

        }

        return "redirect:/categories";

    }


}

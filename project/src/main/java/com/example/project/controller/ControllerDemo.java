package com.example.project.controller;

import com.example.project.model.ModelComputer;
import com.example.project.model.User;
import com.example.project.repository.ComputerRepository;
import com.example.project.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/projectOne")
public class ControllerDemo {

    @Autowired
    private ComputerRepository computerRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String getPage(Model model) {
        return "/project/main";
    }

    @GetMapping("/pokupka")
    public String getPokupka(Model model) {
        List<ModelComputer> listComputers = computerRepository.findAll();
        model.addAttribute("user", new User());
        model.addAttribute("computers", listComputers);
        return "/project/pokupka";
    }

    @GetMapping("pokupkaVivod/{id}")
    public String pokupkaViv(Model model,
        @PathVariable int id) {
        model.addAttribute("computers", computerRepository.getById(id));
        return "/project/pokupkaVivod";
    }

    @GetMapping("/addNout")
    public String getAddnout(Model model) {
        model.addAttribute("computer", new ModelComputer());
        return "/project/addNout";
    }

    @GetMapping("/newUser")
    public String getNewUser(Model model) {
        model.addAttribute("user", new User());
        return "/project/newUser";
    }

    @GetMapping("/user")
    public String getUser(Model model) {
        model.addAttribute("user", new User());
        return "/project/user";
    }

    @GetMapping("/addNewUser")
    public String addUser(Model model,
                          @ModelAttribute("user") User user) {
        userRepository.save(user);
        return "redirect:/projectOne/user";
    }

    @GetMapping("/noutTable")
    public String noutTable(Model model,
        Authentication authentication){
        User user = getUser(authentication.getName());
        List<ModelComputer> listComputers = computerRepository.findAll();
        model.addAttribute("user", new User());
        model.addAttribute("computers", user.getComputers());
        return "/project/noutTable";
    }


    @PostMapping("/addKomp")
    public String addComp(
            @ModelAttribute("computer") ModelComputer computer,
            @RequestParam("photo") MultipartFile multipartFile,
            Authentication authentication
    ) {
        computer.setImg(multipartFile.getOriginalFilename());
        writeFile(multipartFile);
        User user = getUser(authentication.getName());
        user.addToList(computer);
        computer.setUser(user);
        computerRepository.save(computer);
        userRepository.save(user);
        System.out.println(user.getLogin());
        return "redirect:/projectOne/noutTable";
    }

    private void writeFile(MultipartFile multipartFile) {
        try {
            multipartFile.transferTo(
                    new File("C:\\Users\\Ramazan\\Downloads\\project\\src\\main\\resources\\img\\" + multipartFile.getOriginalFilename())
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public User getUser(String username) {
        List<User> accounts = userRepository.findAll();
        for (User user : accounts) {
            if (user.getLogin().equals(username)) return user;
        }
        return null;
    }

    @GetMapping("deleteNout/{id}")
    public String deleteNout(
            @PathVariable int id){
                computerRepository.deleteById(id);
                return "redirect:/projectOne/noutTable";
    }

















   /* @GetMapping("/pokupka/{id}")
    public String pokupka(
            Model model,
            @PathVariable int id) {

        model.addAttribute("pokupka", computerRepository.getById(id));

        return "/pokupkaVivod";
    }
    @GetMapping("/pageToAddNout")
    public String pageToAddNout(Model model){

        return "/pageToAddNout";
    }*/

}

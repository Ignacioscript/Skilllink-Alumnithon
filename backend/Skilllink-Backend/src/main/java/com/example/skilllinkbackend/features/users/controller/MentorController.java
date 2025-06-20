package com.example.skilllinkbackend.features.users.controller;

import com.example.skilllinkbackend.features.users.dto.MentorRequest;
import com.example.skilllinkbackend.features.users.dto.MentorResponse;
import com.example.skilllinkbackend.features.users.service.MentorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mentors")
public class MentorController {


    private final MentorService mentorService;

    @GetMapping
    public String getAllMentors(Model model) {
        List<MentorResponse> mentors = mentorService.getAllMentors();
        model.addAttribute("mentors", mentors);
        return "mentors";
    }

    @GetMapping("/{id}")
    public String findMentorBYId(@PathVariable("id") Long id, Model model) {
        MentorResponse mentor = mentorService.getMentorById(id);
        model.addAttribute("mentor", mentor);
        return "show-mentor";
    }

    @GetMapping("/add")
    public String showNewMentorForm(Model model) {
        model.addAttribute("mentorRequest", new MentorRequest());
        return "mentor-form";
    }

    @PostMapping("/add")
    public String saveMentor(@ModelAttribute("mentorRequest") MentorRequest request) {
        mentorService.createOrLinkMentor(request);
        return "redirect:/mentors";
    }

    @PostMapping("/delete/{id}")
    public String deleteMentor(@PathVariable("id") Long id) {
        mentorService.deleteMentorById(id);
        return "redirect:/mentors";
    }

    @GetMapping("/edit/{id}")
    public String editMentor(@PathVariable("id") Long id, @ModelAttribute("mentorRequest") MentorRequest request) {
        mentorService.updateMentor(id, request);
        return "redirect:/mentors";
    }





}

package com.example.skilllinkbackend.features.users.controller;

import com.example.skilllinkbackend.features.users.dto.MentorRequest;
import com.example.skilllinkbackend.features.users.dto.MentorResponse;
import com.example.skilllinkbackend.features.users.service.MentorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mentors")
@RequiredArgsConstructor
public class MentorRestController {

    private final MentorService mentorService;

    @GetMapping("/test")
    public String testMentorAccess() {
        return "Welcome, mentor";
    }


    @GetMapping
    public ResponseEntity<List<MentorResponse>> findAll() {
        return ResponseEntity.ok(mentorService.getAllMentors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MentorResponse> getMentorById(@PathVariable Long id) {
        return ResponseEntity.ok(mentorService.getMentorById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<MentorResponse> createMentor(@RequestBody MentorRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mentorService.createOrLinkMentor(request));
    }



    @PutMapping("/{id}")
    public ResponseEntity<MentorResponse> updateMentor(@PathVariable("id") Long id, @RequestBody MentorRequest request ) {
        return ResponseEntity.ok(mentorService.updateMentor(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMentorById(@PathVariable("id") Long id) {
        mentorService.deleteMentorById(id);
        return ResponseEntity.noContent().build();
    }



}

package com.example.languages.controller;

import com.example.languages.model.Language;
import com.example.languages.repository.LanguageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LanguageController {

    private final LanguageRepository languageRepository;

    @GetMapping("/")
    public String getHello() {
        return "<h1>Languages API</h1> <br> Yuri March";
    }

    @GetMapping("/language")
    public List<Language> getLanguages() {
        return languageRepository.findAll();
    }

    @PostMapping("/language")
    public Language createLanguage(@RequestBody Language language) {
        return languageRepository.save(language);
    }

    @DeleteMapping("/language/{id}")
    public void deleteLanguage(@PathVariable String id) {
        languageRepository.deleteById(id);
    }
}



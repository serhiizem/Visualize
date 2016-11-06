package com.algorithms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GenerationController {

    @GetMapping(value = "/generateArray")
    public String getGenerationPage() {
        return "array-generation";
    }
}

package com.algorithms.controller;

import com.algorithms.entity.GenerationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

@Controller
public class GenerationController {

    private static final Logger log = LoggerFactory.getLogger(GenerationController.class);

    @GetMapping(value = "/showGenerationPage")
    public ModelAndView getGenerationPage(ModelAndView mav) {
        mav.addObject(new GenerationRequest());
        mav.setViewName("array-generation");
        return mav;
    }

    @PostMapping(value = "/generateArray")
    public ModelAndView getGeneratedArray(@ModelAttribute(value = "generationRequest")
                                                      GenerationRequest generationRequest,
                                          ModelAndView mav) {

        mav.addObject("generatedArray", result);
        mav.setViewName("array-generation");

        return mav;
    }
}

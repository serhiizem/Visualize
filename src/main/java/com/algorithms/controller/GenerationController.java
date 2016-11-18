package com.algorithms.controller;

import com.algorithms.entity.GenerationRequest;
import com.algorithms.entity.GenerationType;
import com.algorithms.util.Range;
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
        mav.addObject(new Range());
        mav.setViewName("array-generation");
        return mav;
    }

    @PostMapping(value = "/generateArray")
    public ModelAndView getGeneratedArray(@ModelAttribute(value = "generationRequest")
                                          GenerationRequest generationRequest,
                                          ModelAndView mav) {

        log.info("getGenerationType(): {}", generationRequest.getGenerationType());
        log.info("getArraySize(): {}", generationRequest.getRange().getArraySize());
        log.info("getMinValue(): {}", generationRequest.getRange().getMinValue());
        log.info("getMaxValue(): {}", generationRequest.getRange().getMaxValue());

        mav.addObject("generatedArray", null);
        mav.setViewName("array-generation");

        return mav;
    }
}

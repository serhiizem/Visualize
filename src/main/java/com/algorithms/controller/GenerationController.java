package com.algorithms.controller;

import com.algorithms.entity.GenerationRequest;
import com.algorithms.entity.GenerationType;
import com.algorithms.generation.GenerationStrategy;
import com.algorithms.entity.Range;
import com.algorithms.util.factories.GenerationFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GenerationController {

    private static final Logger log = LoggerFactory.getLogger(GenerationController.class);

    private GenerationFactory generationFactory;

    @Autowired
    public GenerationController(GenerationFactory generationFactory) {
        this.generationFactory = generationFactory;
    }

    @GetMapping(value = "/showGenerationPage")
    public ModelAndView getGenerationPage(ModelAndView mav) {
        mav.addObject(new GenerationRequest());
        mav.addObject(new Range());
        mav.setViewName("array-generation");
        return mav;
    }

    @GetMapping(value = "/generateArray")
    public String redirectToGenerationPage() {
        return "redirect:/showGenerationPage";
    }

    @PostMapping(value = "/generateXls")
    public String generateXls(@RequestBody Range range) {
        System.out.println(range.toString());
        return "redirect:/showGenerationPage";
    }

    @PostMapping(value = "/generateArray")
    public ModelAndView getGeneratedArray(@ModelAttribute(value = "generationRequest")
                                          GenerationRequest generationRequest,
                                          ModelAndView mav) {
        GenerationStrategy generationStrategy = this.getGenerationAlgorithm(generationRequest);
        Range range = generationRequest.getRange();
        Comparable[] generatedArray = this.generateArrayFromRange(range, generationStrategy);

        mav.addObject("generatedArray", generatedArray);
        mav.setViewName("array-generation");
        return mav;
    }

    private GenerationStrategy getGenerationAlgorithm(GenerationRequest generationRequest) {
        GenerationType generationType = generationRequest.getGenerationType();
        return generationFactory.getGenerationAlgorithm(generationType);
    }

    private Comparable[] generateArrayFromRange(Range range, GenerationStrategy generationStrategy) {
        GenerationService generationService = new GenerationService(range, generationStrategy);
        return generationService.generateArray();
    }
}

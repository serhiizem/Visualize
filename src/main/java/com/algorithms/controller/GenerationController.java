package com.algorithms.controller;

import com.algorithms.util.GenerationRequest;
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
        int arraySize = generationRequest.getArraySize();
        int minValue = generationRequest.getMinValue();
        int maxValue = generationRequest.getMaxValue();
        log.info("generationRequest.getGenerationType(): {}", generationRequest.getGenerationType());

        Integer[] helper = new Integer[maxValue - minValue];
        Integer[] result = new Integer[arraySize];

        if(maxValue - minValue < arraySize) {
            throw new RequestedArraySizeException("Your array will not contain duplicate " +
                    "values if it has size less or equal to the difference between " +
                    "its max and min values");
        }

        int count = 0;
        for(int i = minValue; i < maxValue; i++) {
            helper[count++] = i;
        }
        helper = this.shuffle(helper);

        System.arraycopy(helper, 0, result, 0, result.length);
        Arrays.sort(result);

        mav.addObject("generatedArray", result);
        mav.setViewName("array-generation");

        return mav;
    }

    private Integer[] shuffle(Integer[] helper) {
        int n = helper.length;
        for (int i = 0; i < n; i++) {
            // choose index uniformly in [i, n-1]
            int r = i + (int) (Math.random() * (n - i));
            int swap = helper[r];
            helper[r] = helper[i];
            helper[i] = swap;
        }
        return helper;
    }
}

package com.algorithms.controller;

import com.algorithms.entity.GenerationRequest;
import com.algorithms.entity.GenerationType;
import com.algorithms.entity.Range;
import com.algorithms.generation.GenerationStrategy;
import com.algorithms.service.GenerationService;
import com.algorithms.service.XlsService;
import com.algorithms.util.factories.GenerationFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * Manages requests related to array generation
 *
 * @author  Zemlianiy
 * @version 1.0
 * @since
 */
@Controller
public class GenerationController {

    private static final Logger log = LoggerFactory.getLogger(GenerationController.class);

    private GenerationFactory generationFactory;
    private XlsService xlsService;

    @Autowired
    public GenerationController(GenerationFactory generationFactory,
                                XlsService xlsService) {
        this.generationFactory = generationFactory;
        this.xlsService = xlsService;
    }

    /**
     * Populates {@code ModelAndView} object with the instances
     * required for the further interaction at view page
     */
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

    /**
     * Generates xls report concerning speed of the various
     * algorithms implemented in the project
     */
    @PostMapping(value = "/generateXls")
    public String generateXls() {
        try {
            xlsService.generateStatistics();
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException | IOException e) {
            e.printStackTrace();
        }
        return "redirect:/showGenerationPage";
    }

    /**
     * Generates an array of type based on the data stored in the
     * fields of {@link GenerationRequest} object.
     *
     * @param generationRequest object which is
     *        being extracted from the model
     */
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

    /**
     * Helper method used to obtain required implementation of {@link GenerationStrategy}
     * class by its name requested from the view.
     *
     * @param generationRequest object that is used to fetch the name of the
     *                          generation algorithm
     */
    private GenerationStrategy getGenerationAlgorithm(GenerationRequest generationRequest) {
        GenerationType generationType = generationRequest.getGenerationType();
        return generationFactory.getGenerationAlgorithm(generationType);
    }

    private Comparable[] generateArrayFromRange(Range range, GenerationStrategy generationStrategy) {
        GenerationService generationService = new GenerationService(range, generationStrategy);
        return generationService.generateArray();
    }
}

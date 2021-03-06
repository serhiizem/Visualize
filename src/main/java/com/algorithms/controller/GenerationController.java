package com.algorithms.controller;

import com.algorithms.entity.GenerationRequest;
import com.algorithms.entity.GenerationType;
import com.algorithms.entity.Range;
import com.algorithms.generation.GenerationStrategy;
import com.algorithms.service.GenerationService;
import com.algorithms.service.StatisticsService;
import com.algorithms.util.factories.GenerationFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.algorithms.validation.GenerationRequestValidator;

/**
 * Manages requests related to array generation
 *
 * @author  Zemlianiy
 * @version 1.0
 * @since   1.0
 */
@Controller
public class GenerationController {

    private GenerationFactory generationFactory;
    private StatisticsService xlsService;
    private GenerationRequestValidator validator;

    @Autowired
    public GenerationController(GenerationFactory generationFactory,
                                StatisticsService xlsService,
                                GenerationRequestValidator validator) {
        this.generationFactory = generationFactory;
        this.xlsService = xlsService;
        this.validator = validator;
    }

    /**
     * Populates {@code ModelAndView} object with instances
     * required for the further interaction at a view page
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

    @GetMapping(value = "/")
    public String redirectToMainPageOnRawEndpoint() {
        return "redirect:/showGenerationPage";
    }

    /**
     * Generates xls report regarding speed of the various
     * algorithms implemented in the project
     */
    @PostMapping(value = "/generateXls")
    public String generateXls() {
        xlsService.writeReport();
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
                                          @Validated GenerationRequest generationRequest,
                                          BindingResult result,
                                          ModelAndView mav) {
        Assert.notNull(generationRequest.getGenerationType());
        validator.validate(generationRequest, result);
        if(result.hasErrors()) {
            return this.getErrorModelAndView();
        }
        GenerationStrategy generationStrategy = this.getGenerationAlgorithm(generationRequest);
        Range range = generationRequest.getRange();
        Comparable[] generatedArray = this.generateArrayFromRange(range, generationStrategy);

        mav.addObject("generatedArray", generatedArray);
        mav.setViewName("array-generation");
        return mav;
    }

    private ModelAndView getErrorModelAndView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("array-generation");
        return modelAndView;
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

    private Comparable[] generateArrayFromRange(Range range,
                                                GenerationStrategy generationStrategy) {
        GenerationService generationService = new GenerationService(range, generationStrategy);
        return generationService.generateArray();
    }
}

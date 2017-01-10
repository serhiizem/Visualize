package com.algorithms.entity;

/**
 * <p>This class is used to produce objects that encapsulate information about
 * the requested generation strategy.
 * This class enables you to keep an instance of {@link Range} class as well as a
 * {@link GenerationType} information as part of a single entity.
 *
 * Typically, client sends information regarding generation process via specific
 * form on a view. This information can be extracted as follows:
 * <pre>
 *     var range = {
 "arraySize" : document.getElementById('array-size').value,
 "minValue"  : document.getElementById('array-min-value').value,
 "maxValue"  : document.getElementById('array-max-value').value
 };
 * </pre>
 * The job of wrapping separate objects into the {@link GenerationRequest} object can be
 * delegated to Spring {@link org.springframework.http.converter.HttpMessageConverter}
 */
public class GenerationRequest {

    private Range range;
    private GenerationType generationType;

    public GenerationRequest() {
    }

    public GenerationRequest(final Range range,
                             final GenerationType generationType) {
        this.range = range;
        this.generationType = generationType;
    }

    public Range getRange() {
        return range;
    }

    public void setRange(Range range) {
        this.range = range;
    }

    public GenerationType getGenerationType() {
        return generationType;
    }

    public void setGenerationType(GenerationType generationType) {
        this.generationType = generationType;
    }

    @Override
    public String toString() {
        return "GenerationRequest{" +
                "range=" + range +
                ", generationType=" + generationType +
                '}';
    }
}

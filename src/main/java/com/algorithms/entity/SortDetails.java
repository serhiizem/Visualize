package com.algorithms.entity;

/**
 * <p>This class is used to produce objects that encapsulate information about
 * the requested sort. You can use it to wrap data to be sorted as well as an
 * information regarding algorithm in a single object. To do so from
 * an html view you can use the following javascript code:
 * <pre>
 *     JSON.stringify({'array': array, 'sortType': $scope.sortType.value})
 * </pre>
 * The rest of the job can be performed by frameworks. For example, Spring
 * framework uses {@link org.springframework.http.converter.HttpMessageConverter}
 * to do this kind of conversion</p>
 */
public class SortDetails {

    private Integer[] array;
    private String sortType;

    public SortDetails() {
    }

    public SortDetails(Integer[] array, String sortType) {
        this.array = array;
        this.sortType = sortType;
    }

    public Integer[] getArray() {
        return array;
    }

    public void setArray(Integer[] array) {
        this.array = array;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }
}

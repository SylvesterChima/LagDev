package com.example.sylvester.lagdev.model;

import java.util.List;

/**
 * Created by Sylvester on 28/08/2017.
 */

public class mDevs {
    private int total_count;
    private boolean incomplete_results;
    private List<developers> items;

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public boolean isIncomplete_results() {
        return incomplete_results;
    }

    public void setIncomplete_results(boolean incomplete_results) {
        this.incomplete_results = incomplete_results;
    }

    public List<developers> getItems() {
        return items;
    }

    public void setItems(List<developers> items) {
        this.items = items;
    }
}

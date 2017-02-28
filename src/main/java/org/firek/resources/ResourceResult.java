package org.firek.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResourceResult {

    @JsonProperty("description")
    private final String description;


    public ResourceResult(String description) {
        this.description = description;
    }
}

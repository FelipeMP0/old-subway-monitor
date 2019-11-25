package com.subwaymonitor.models.metro;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StatusMetro {

    @JsonProperty("ListLineStatus")
    private List<MetroLineStatus> listLineStatus;

    public List<MetroLineStatus> getListLineStatus() {
        return listLineStatus;
    }

    public void setListLineStatus(List<MetroLineStatus> listLineStatus) {
        this.listLineStatus = listLineStatus;
    }

}

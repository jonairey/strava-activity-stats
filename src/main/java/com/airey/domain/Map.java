package com.airey.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Map {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("summary_polyline")
    @Expose
    private Object summaryPolyline;
    @SerializedName("resource_state")
    @Expose
    private Long resourceState;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getSummaryPolyline() {
        return summaryPolyline;
    }

    public void setSummaryPolyline(Object summaryPolyline) {
        this.summaryPolyline = summaryPolyline;
    }

    public Long getResourceState() {
        return resourceState;
    }

    public void setResourceState(Long resourceState) {
        this.resourceState = resourceState;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("summaryPolyline", summaryPolyline).append("resourceState", resourceState).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(summaryPolyline).append(resourceState).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Map) == false) {
            return false;
        }
        Map rhs = ((Map) other);
        return new EqualsBuilder().append(id, rhs.id).append(summaryPolyline, rhs.summaryPolyline).append(resourceState, rhs.resourceState).isEquals();
    }

}

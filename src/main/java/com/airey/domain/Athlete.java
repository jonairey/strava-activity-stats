package com.airey.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Athlete {
    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("resource_state")
    @Expose
    private Long resourceState;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getResourceState() {
        return resourceState;
    }

    public void setResourceState(Long resourceState) {
        this.resourceState = resourceState;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("resourceState", resourceState).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(resourceState).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Athlete) == false) {
            return false;
        }
        Athlete rhs = ((Athlete) other);
        return new EqualsBuilder().append(id, rhs.id).append(resourceState, rhs.resourceState).isEquals();
    }

}

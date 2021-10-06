package com.example.backendbookmanage.model.custom;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GeneralResponseCustom<T> {
    @JsonProperty("status")
    private ResponseStatus status;

    @JsonProperty("data")
    private T data;

    @JsonProperty("paging")
    private Paging paging;

    @Override
    public String toString() {
        return "{" + "status=" + status +
                ", data=" + data +
                '}';
    }
}

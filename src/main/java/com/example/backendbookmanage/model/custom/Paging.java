package com.example.backendbookmanage.model.custom;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Paging {

    private static final long serialVersionUID = 2405172041950251837L;

    @JsonProperty("pageSize")
    private Integer pageSize;

    @JsonProperty("totalElement")
    private Integer totalElement;

    @JsonProperty("totalPage")
    private Integer totalPage;

    @JsonProperty("currentPage")
    private Integer currentPage;

    @JsonProperty("nextPageURL")
    private String nextPageURL;

    @JsonProperty("previousPageURL")
    private String previousPageURL;

    public static Paging getPagingInfo(Integer totalItem, Integer size, Integer page) {
        Paging paging = new Paging();
        if (totalItem != null && size != null && size != 0) {
            Integer totalPage;
            if (totalItem % size == 0) {
                totalPage = totalItem / size;
            } else {
                totalPage = totalItem / size + 1;
            }
            paging.setTotalPage(totalPage);
        }
        paging.setTotalElement(totalItem);
        paging.setCurrentPage(page);
        paging.setPageSize(size);
        return paging;
    }
}

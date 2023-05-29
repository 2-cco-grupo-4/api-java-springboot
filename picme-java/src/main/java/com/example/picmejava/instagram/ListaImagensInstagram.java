package com.example.picmejava.instagram;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.NoArgsConstructor;

import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@NoArgsConstructor
public class ListaImagensInstagram {

    private List<DataInstagram> data;
    private PagingInstagram paging;

    public List<DataInstagram> getData() {
        return data;
    }

    public void setData(List<DataInstagram> data) {
        this.data = data;
    }

    public PagingInstagram getPaging() {
        return paging;
    }

    public void setPaging(PagingInstagram paging) {
        this.paging = paging;
    }

}

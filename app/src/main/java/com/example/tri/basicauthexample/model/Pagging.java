package com.example.tri.basicauthexample.model;

import java.util.List;

/**
 * Created by tri on 5/22/16.
 */

/**
 *
 * Beberapa response menggunakan fasilitas PAGGING Spring
 * Kelas ini untuk menangani response tersebut ex : Pagging<Anggota>
 */
public class Pagging<T> {
    private List<T> content;
    private Boolean last;
    private Integer totalPages;
    private Integer totalElements;
    private Integer numberOfElements;
    private Object sort;
    private Boolean first;
    private Integer size;
    private Integer number;

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public Boolean getLast() {
        return last;
    }

    public void setLast(Boolean last) {
        this.last = last;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Integer totalElements) {
        this.totalElements = totalElements;
    }

    public Integer getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(Integer numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public Object getSort() {
        return sort;
    }

    public void setSort(Object sort) {
        this.sort = sort;
    }

    public Boolean getFirst() {
        return first;
    }

    public void setFirst(Boolean first) {
        this.first = first;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}

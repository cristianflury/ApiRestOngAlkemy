package org.alkemy.somosmas.service;

import org.alkemy.somosmas.model.Slide;

import java.util.List;

public interface SlideService {

    Slide update(Long id, Slide slide, String fileType) throws Exception;

    Slide findById(Long id);

    void delete(Long id) throws Exception;

    Slide create(Slide slide, String fileType) throws Exception;
    List <Slide> findAll();
}
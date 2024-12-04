package com.prueba.controller.service;

import com.prueba.modelo.Repository.VolumetriaRepository;
import com.prueba.modelo.VO.Volumetria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VolumetriaService {

    @Autowired
    private VolumetriaRepository volumetriaRepository;

    public Optional<Volumetria> findById(int id) {
        return volumetriaRepository.findById(id);
    }


    public Volumetria save(Volumetria volumetria) {
        return volumetriaRepository.save(volumetria);
    }


}

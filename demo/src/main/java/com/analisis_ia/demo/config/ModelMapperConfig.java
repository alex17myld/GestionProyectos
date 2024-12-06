package com.analisis_ia.demo.config;

import com.analisis_ia.demo.models.*;

import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        
        // Definir el mapeo personalizado de Proyecto a ProyectoDTO
        mapper.addMappings(new PropertyMap<Proyecto, ProyectoDTO>() {
            @Override
            protected void configure() {
                // Mapear entidades relacionadas a sus IDs
                map().setCertificacionRequerida(source.getCertificacionRequerida().getId());
                map().setEntregable(source.getEntregable().getId());
                map().setExperienciaRequerida(source.getExperienciaRequerida().getId());
                map().setFacturacionAnual(source.getFacturacionAnual().getId());
                map().setFortalezaTecnologica(source.getFortalezaTecnologica().getId());
                map().setLugarTrabajo(source.getLugarTrabajo().getId());
                map().setPrecioHora(source.getPrecioHora().getId());
                map().setTitulacionEmpleado(source.getTitulacionEmpleado().getId());
                map().setVolumetria(source.getVolumetria().getId());
                
                // Mapear la colección de tecnologías a sus IDs
                using(ctx -> {
                    Set<Tecnologia> tecnologias = source.getTecnologias();
                    return tecnologias.stream().map(Tecnologia::getId).collect(Collectors.toSet());
                }).map(source.getTecnologias()).setTecnologias(null);
            }
        });
        
        return mapper;
    }
}

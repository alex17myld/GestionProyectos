package com.analisis_ia.demo.services;

import com.analisis_ia.demo.models.Proyecto;
import com.analisis_ia.demo.models.ProyectoDTO;
import com.analisis_ia.demo.models.Tecnologia;
import com.analisis_ia.demo.repositories.ProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Set;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProyectoDTOServiceImpl implements ProyectoDTOService {

    @Autowired
    private ProyectoRepository proyectoRepository;

    @Override
    public List<ProyectoDTO> getAllProyectos() {
        List<Proyecto> proyectos = proyectoRepository.findAll();
        return proyectos.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private ProyectoDTO convertToDTO(Proyecto proyecto) {
        ProyectoDTO dto = new ProyectoDTO();
        dto.setDuracion(proyecto.getDuracion());
        dto.setPresupuesto(proyecto.getPresupuesto() != null ? proyecto.getPresupuesto() : 0.0);
        dto.setNombreProyecto(proyecto.getNombreProyecto() != null ? proyecto.getNombreProyecto() : "NULO");
        dto.setCliente(proyecto.getCliente() != null ? proyecto.getCliente() : "NULO");
        dto.setFacturacionAnual(proyecto.getFacturacionAnual() != null ? proyecto.getFacturacionAnual().getId() : 0);
        dto.setFortalezaTecnologica(proyecto.getFortalezaTecnologica() != null ? proyecto.getFortalezaTecnologica().getId() : 0);
        dto.setExperienciaRequerida(proyecto.getExperienciaRequerida() != null ? proyecto.getExperienciaRequerida().getId() : 0);
        dto.setLugarTrabajo(proyecto.getLugarTrabajo() != null ? proyecto.getLugarTrabajo().getId() : 0);
        dto.setNumeroPerfilesRequeridos(proyecto.getNumeroPerfilesRequeridos() != 0 ? proyecto.getNumeroPerfilesRequeridos() : 0);
        dto.setPrecioHora(proyecto.getPrecioHora() != null ? proyecto.getPrecioHora().getId() : 0);
        dto.setVolumetria(proyecto.getVolumetria() != null ? proyecto.getVolumetria().getId() : 0);
        dto.setTitulacionEmpleado(proyecto.getTitulacionEmpleado() != null ? proyecto.getTitulacionEmpleado().getId() : 0);
        dto.setCertificacionRequerida(proyecto.getCertificacionRequerida() != null ? proyecto.getCertificacionRequerida().getId() : 0);
        dto.setEntregable(proyecto.getEntregable() != null ? proyecto.getEntregable().getId() : 0);
        dto.setPorcentajeExito(proyecto.getPorcentajeExito() != null ? proyecto.getPorcentajeExito() : 0.0);
    
        // Manejo seguro del conjunto de tecnologías
        dto.setTecnologias(
            proyecto.getTecnologias() != null
                ? proyecto.getTecnologias().stream()
                      .filter(tecnologia -> tecnologia != null && tecnologia.getId() != null)
                      .map(tecnologia -> tecnologia.getId().intValue())
                      .collect(Collectors.toSet())
                : Set.of(null) 
        );
    
        return dto;
    }
    
    
    
}
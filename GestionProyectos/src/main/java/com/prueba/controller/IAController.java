package com.prueba.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.prueba.controller.service.FacturacionService;
import com.prueba.controller.service.ProyectosService;
import com.prueba.controller.service.ResultadoService;
import com.prueba.controller.service.VolumetriaService;
import com.prueba.modelo.VO.FacturacionAnual;
import com.prueba.modelo.VO.Proyecto;
import com.prueba.modelo.VO.Resultado;
import com.prueba.modelo.VO.Volumetria;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
public class IAController {

    @Autowired
    private ProyectosService proyectoService;

    @Autowired
    private ResultadoService resultadoService;

    @Autowired
    private VolumetriaService volumetriaService;

    @Autowired
    private FacturacionService facturacionService;

    @PostMapping("/IA")
    public ResponseEntity<?> enviarSolicitudIA(@RequestBody Proyecto proyecto) {
        try {
            System.out.println("Inicio de la solicitud para el proyecto.");

            // Cargar entidades relacionadas si el proyecto tiene ID
            if (proyecto.getId() != null) {
                proyecto = cargarEntidadesRelacionadas(proyecto);
            }

            // Guardar el proyecto en la base de datos (sin el porcentaje de éxito)
            Proyecto proyectoGuardado = proyectoService.save(proyecto);
            System.out.println("Proyecto guardado con ID: " + proyectoGuardado.getId());

            // Recuperar el proyecto completo con sus relaciones
            Proyecto proyectoCompleto = proyectoService.findById(proyectoGuardado.getId())
                    .orElseThrow(() -> new RuntimeException("No se pudo recuperar el proyecto recién guardado."));

            // Construir JSON para enviar al microservicio de IA
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode json = mapper.createObjectNode();
            json.put("duracion", proyectoCompleto.getDuracion());
            json.put("presupuesto", proyectoCompleto.getPresupuesto());
            json.put("facturacion_anual", proyectoCompleto.getFacturacionAnual().getNombre());
            json.put("fortaleza_tecnologica", proyectoCompleto.getFortalezaTecnologica().getNombre());
            json.put("experiencia_requerida", proyectoCompleto.getExperienciaRequerida().getNombre());
            json.put("lugar_trabajo", proyectoCompleto.getLugarTrabajo().getNombre());
            json.put("numero_perfiles_requeridos", proyectoCompleto.getNumeroPerfilesRequeridos());
            json.put("precio_hora", proyectoCompleto.getPrecioHora().getNombre());
            json.put("volumetria", proyectoCompleto.getVolumetria().getId());

            System.out.println("JSON enviado al microservicio de IA: " + json.toPrettyString());

            // Llamada al microservicio de IA
            String iaEndpointUrl = "http://192.168.40.83:8000/predict";
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(json.toString(), headers);

            ResponseEntity<String> iaResponse = restTemplate.postForEntity(iaEndpointUrl, request, String.class);
            if (!iaResponse.getStatusCode().is2xxSuccessful()) {
                throw new RuntimeException("Error en la llamada a la IA: " + iaResponse.getBody());
            }

            // Procesar respuesta de la IA
            JsonNode rootNode = mapper.readTree(iaResponse.getBody());
            if (!rootNode.has("prob_exito")) {
                throw new RuntimeException("Respuesta de la IA no contiene 'prob_exito'.");
            }

            String porcentajeTexto = rootNode.get("prob_exito").asText();
            double valorDouble = Double.parseDouble(porcentajeTexto.replace("%", ""));
            System.out.println("Porcentaje de éxito obtenido de la IA: " + porcentajeTexto);

            // Actualizar el porcentaje de éxito en el proyecto
            proyectoCompleto.setPorcentajeExito(valorDouble);
            Proyecto proyectoActualizado = proyectoService.save(proyectoCompleto);
            System.out.println("Proyecto actualizado con el porcentaje de éxito: " + valorDouble);

            // Guardar el resultado en la tabla de resultados
            Resultado resultado = new Resultado();
            resultado.setProyecto(proyectoActualizado);
            resultado.setResultado(valorDouble);
            resultado.setFechaResultado(Date.valueOf(LocalDate.now()));
            resultadoService.save(resultado);

            System.out.println("Resultado guardado correctamente en la base de datos.");

            // Retornar el proyecto actualizado
            return ResponseEntity.ok(proyectoActualizado);

        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error en el procesamiento de la solicitud.");
        }
    }

    private Proyecto cargarEntidadesRelacionadas(Proyecto proyecto) {
        if (proyecto.getVolumetria() != null && proyecto.getVolumetria().getId() != null) {
            Volumetria volumetria = volumetriaService.findById(proyecto.getVolumetria().getId())
                    .orElseThrow(() -> new RuntimeException("Volumetria no encontrada con ID: " + proyecto.getVolumetria().getId()));
            proyecto.setVolumetria(volumetria);
        }

        if (proyecto.getFacturacionAnual() != null && proyecto.getFacturacionAnual().getId() != null) {
            FacturacionAnual facturacionAnual = facturacionService.findById(proyecto.getFacturacionAnual().getId())
                    .orElseThrow(() -> new RuntimeException("Facturación Anual no encontrada con ID: " + proyecto.getFacturacionAnual().getId()));
            proyecto.setFacturacionAnual(facturacionAnual);
        }

        // Repetir para otras relaciones según sea necesario
        return proyecto;
    }
}

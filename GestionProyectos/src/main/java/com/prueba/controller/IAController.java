package com.prueba.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.prueba.controller.service.ProyectosService;
import com.prueba.controller.service.ResultadoService;
import com.prueba.modelo.VO.Proyecto;
import com.prueba.modelo.VO.Resultado;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpHeaders;

import java.time.LocalDate;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class IAController {

    @Autowired
    private ProyectosController proyectoController;
    @Autowired
    private ResultadoService resultadoService;
    @Autowired
    private ProyectosService proyectoService;


    @GetMapping("/IA")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }

    @PostMapping("/IA")
    ResponseEntity<?> enviarSolicitudIA(@RequestBody Proyecto proyecto) {
        try {
            System.out.println("Inicio de la solicitud para el proyecto con ID: " + proyecto.getId());
    
            // Recuperar el proyecto
            EntityModel<Proyecto> proyectoGuardado = proyectoController.one(proyecto.getId());
            Proyecto proyectoRecuperado = proyectoGuardado.getContent();
    
            // Construir JSON para enviar a la IA
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode json = mapper.createObjectNode();
            json.put("duracion", proyectoRecuperado.getDuracion());
            json.put("presupuesto", proyectoRecuperado.getPresupuesto());
            json.put("facturacion_anual", proyectoRecuperado.getFacturacionAnual().getNombre());
            json.put("fortaleza_tecnologica", proyectoRecuperado.getFortalezaTecnologica().getNombre());
            json.put("experiencia_requerida", proyectoRecuperado.getExperienciaRequerida().getNombre());
            json.put("lugar_trabajo", proyectoRecuperado.getLugarTrabajo().getNombre());
            json.put("numero_perfiles_requeridos", proyectoRecuperado.getNumeroPerfilesRequeridos());
            json.put("precio_hora", proyectoRecuperado.getPrecioHora().getNombre());
            json.put("volumetria", proyectoRecuperado.getVolumetria().getId());
    
            System.out.println("JSON enviado al microservicio de IA: " + json.toPrettyString());
    
            // Llamada a la IA
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
            JsonNode rootNode = new ObjectMapper().readTree(iaResponse.getBody());
            if (!rootNode.has("prob_exito")) {
                throw new RuntimeException("Respuesta de la IA no contiene 'prob_exito'.");
            }
    
            String porcentajeTexto = rootNode.get("prob_exito").asText();
            double valorDouble = Double.parseDouble(porcentajeTexto.replace("%", ""));
    
            System.out.println("Porcentaje de éxito obtenido de la IA: " + porcentajeTexto);
    
            // Asignar el porcentaje de éxito al proyecto
            proyectoRecuperado.setPorcentajeExito(valorDouble);
    
            // Guardar el proyecto actualizado en la base de datos
            proyectoService.save(proyectoRecuperado);
    
            System.out.println("Proyecto actualizado con el porcentaje de éxito: " + valorDouble);
    
            // Guardar el resultado en la tabla de resultados
            Resultado resultado = new Resultado();
            resultado.setProyecto(proyectoRecuperado);
            resultado.setResultado(valorDouble);
            resultado.setFechaResultado(Date.valueOf(LocalDate.now()));
            resultadoService.save(resultado);
    
            System.out.println("Resultado guardado correctamente en la base de datos.");
    
            // Retornar el proyecto actualizado
            return ResponseEntity.ok(proyectoRecuperado);
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error en el procesamiento de la solicitud.");
        }
    }
    

    

    

}

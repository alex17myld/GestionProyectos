package com.prueba.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
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

    @GetMapping("/IA")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }

    @PostMapping("/IA")
    ResponseEntity<?> enviarSolicitudIA(@RequestBody Proyecto proyecto) {
        // TODO: process POST request
        try {
            EntityModel<Proyecto> proyectoGuardado = proyectoController.one(proyecto.getId());

            Proyecto proyectoRecuperado = proyectoGuardado.getContent(); // Obtener el proyecto del EntityModel
            // Convertir el proyecto recuperado a JSON
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

            // System.out.println("JSON enviado al microservicio: " + json);

            // URL del microservicio de IA
            String iaEndpointUrl = "http://192.168.40.83:8000/predict/";
            RestTemplate restTemplate = new RestTemplate();

            // Crear los encabezados para la solicitud
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            System.out.println(json.toString());
            // Crear la solicitud HttpEntity con el cuerpo JSON y los encabezados
            HttpEntity<String> request = new HttpEntity<>(json.toString(), headers);

            // Enviar la solicitud al microservicio y obtener la respuesta
            ResponseEntity<String> iaResponse = restTemplate.postForEntity(iaEndpointUrl, request, String.class);

            System.out.println("Respuesta del microservicio de IA: " + iaResponse.getBody());
            String porcentajeJson = iaResponse.getBody();
            try {
                // Crear un ObjectMapper
                ObjectMapper mapperjson = new ObjectMapper();

                // Parsear el JSON directamente como JsonNode
                JsonNode rootNode = mapperjson.readTree(porcentajeJson);

                // Extraer el valor de "prob_exito"
                String porcentaje = rootNode.get("prob_exito").asText();

                // Convertir a double
                double valorDouble = Double.parseDouble(porcentaje.replace("%", ""));
                // proyectoGuardado.getContent().setPorcentajeExito(valorDouble);

                LocalDate fechaHoy = LocalDate.now();

                // Convertir LocalDate a java.sql.Date
                Date sqlFecha = Date.valueOf(fechaHoy);

                Resultado resultado = new Resultado();
                resultado.setProyecto(proyectoRecuperado);
                resultado.setResultado(valorDouble);
                resultado.setFechaResultado(sqlFecha);

                resultadoService.save(resultado);

            } catch (Exception e) {
                e.printStackTrace();
            }

            // Retornar el modelo de datos usando HATEOAS (ajusta según tu implementación)
            return ResponseEntity.created(proyectoGuardado.getRequiredLink(IanaLinkRelations.SELF).toUri())
                    .body(proyectoGuardado);

        } catch (Exception e) {
            // Manejo de cualquier otra excepción general
            System.err.println("Error inesperado: " + e.getMessage());
            e.printStackTrace();
            return null; // O lanzar una excepción personalizada si es necesario
        }
    }
}

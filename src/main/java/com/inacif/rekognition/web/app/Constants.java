package com.inacif.rekognition.web.app;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;

public class Constants {
	
	public static String domain = "http://localhost:4200";
	public static String timeZone = "America/Guatemala";
	public static String bucketName = "inacif-rekognition-bucket";
	
	public static final Map<HttpStatus, String> statusMessages = new HashMap<>();
	static {
		statusMessages.put(HttpStatus.OK, "OPERATION_SUCCESSFUL");
		statusMessages.put(HttpStatus.NOT_FOUND, "NOT_FOUND");
		statusMessages.put(HttpStatus.BAD_REQUEST, "BAD_REQUEST");
		statusMessages.put(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_ERROR");
	}
	
	public static String getMessage(HttpStatus status) {
        return statusMessages.getOrDefault(status, "INTERNAL_ERROR");
    }
	
	public static String emailConfirmationTemplate = "<!DOCTYPE html>\r\n"
			+ "<html lang=\"es\">\r\n"
			+ "<head>\r\n"
			+ "    <meta charset=\"UTF-8\">\r\n"
			+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
			+ "    <title>Procesamiento de Solicitud de Búsqueda</title>\r\n"
			+ "</head>\r\n"
			+ "<body style=\"font-family: Arial, sans-serif; text-align: center;\">\r\n"
			+ "    <img src=\"https://www.inacif.gob.gt/uipconsulta/img/logo_inacifgt.png\" alt=\"Logo de la Empresa\" width=\"200\">\r\n"
			+ "    <h1>Solicitud de Búsqueda de Persona Desaparecida</h1>\r\n"
			+ "    <p>Estimado {applicantName},</p>\r\n"
			+ "    <p>Gracias por contactarnos para solicitar la búsqueda de una persona desaparecida. Estamos comprometidos en ayudarte en este momento difícil.</p>\r\n"
			+ "    <p>Para procesar tu solicitud, por favor haz clic en el siguiente enlace:</p>\r\n"
			+ "    <a href=\"{confirmationUrl}\" style=\"text-decoration: none; background-color: #007BFF; color: white; padding: 10px 20px; border-radius: 5px;\">Procesar Solicitud</a>\r\n"
			+ "    <p>Una vez que hayas hecho clic en el enlace, nuestro equipo comenzará a trabajar en tu solicitud y te mantendremos informado sobre cualquier avance.</p>\r\n"
			+ "    <p>Gracias por confiar en nosotros para ayudarte en este asunto.</p>\r\n"
			+ "    <p>Atentamente,</p>\r\n"
			+ "    <p>Instituto Nacional de Ciencias Forénses</p>\r\n"
			+ "</body>\r\n"
			+ "</html>";
	
}

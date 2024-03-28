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
			+ "    <img src=\"https://www.inacif.gob.gt/uipconsulta/img/logo_inacifgt.png\" alt=\"Logo INACIF\" width=\"200\">\r\n"
			+ "    <h1>Solicitud de Búsqueda de Persona Desaparecida</h1>\r\n"
			+ "    <p>Estimado(a) {applicantName},</p>\r\n"
			+ "    <p>Gracias por contactarnos para solicitar la búsqueda de una persona desaparecida. Estamos comprometidos en ayudarte en este momento difícil.</p>\r\n"
			+ "    <p>Para procesar tu solicitud, por favor haz clic en el siguiente enlace:</p>\r\n"
			+ "    <a href=\"{confirmationUrl}\" style=\"text-decoration: none; background-color: #007BFF; color: white; padding: 10px 20px; border-radius: 5px;\">Procesar Solicitud</a>\r\n"
			+ "    <p>Una vez que hayas hecho clic en el enlace, nuestro equipo comenzará a trabajar en tu solicitud y te mantendremos informado sobre cualquier avance.</p>\r\n"
			+ "    <p>Gracias por confiar en nosotros para ayudarte en este asunto.</p>\r\n"
			+ "    <p>Atentamente,</p>\r\n"
			+ "    <p>Instituto Nacional de Ciencias Forénses</p>\r\n"
			+ "</body>\r\n"
			+ "</html>";
	
	public static String requestConfirmationTemplateString = "<!DOCTYPE html>\n"
			+ "<html lang=\"es\">\n"
			+ "<head>\n"
			+ "    <meta charset=\"UTF-8\">\n"
			+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
			+ "    <title>Resultados solicitud por desaparición de personas</title>\n"
			+ "</head>\n"
			+ "<body style=\"font-family: Arial, sans-serif; text-align: center;\">\r\n"
			+ "    <img src=\"https://www.inacif.gob.gt/uipconsulta/img/logo_inacifgt.png\" alt=\"Logo INACIF\" width=\"200\">\r\n"
			+ "    <p>Estimado/a :applicant,</p>\n"
			+ "\n"
			+ "    <p>Espero que este correo le encuentre bien.</p>\n"
			+ "\n"
			+ "    <p>Queremos informarle que hemos completado el procesamiento de su solicitud con éxito y hemos obtenido resultados que requerimos discutir con usted en persona. Reconocemos que la naturaleza de estos resultados puede ser sensible y difícil de asimilar, pero estamos aquí para ofrecerle nuestro apoyo y guía en este proceso.</p>\n"
			+ "\n"
			+ "    <p>Le invitamos cordialmente a que se acerque a nuestras instalaciones para revisar los hallazgos obtenidos. Estaremos disponibles para discutir los detalles con usted y proporcionarle la orientación necesaria. Queremos asegurarnos de que reciba la información de manera clara y comprensiva, y que pueda tomar las decisiones que considere apropiadas con respecto a los resultados.</p>\n"
			+ "\n"
			+ "    <p>Para facilitar su visita y agilizar el proceso, le proporcionamos un código de solicitud que deberá presentar al llegar a nuestra institución. Este código servirá como referencia para acceder a los detalles de su solicitud y asegurar una atención eficiente por parte de nuestro equipo.</p>\n"
			+ "\n"
			+ "    <p>Su código de solicitud es: <br> </p>"
			+ "\n"
			+ "	   <h3>:requestId</h3>"
			+ "\n"
			+ "    <p>Por favor, no dude en contactarnos a nuestro número <b>:phone</b> si tiene alguna pregunta adicional o si necesita más información antes de su visita. Estamos aquí para ayudarle en todo lo que necesite.</p>\n"
			+ "\n"
			+ "    <p>Le recordamos que nuestras instalaciones están ubicadas en:</p>\n"
			+ "\n"
			+ "    <p><b>:address</b></p>\n"
			+ "\n"
			+ "    <p>Esperamos poder brindarle el apoyo necesario en este momento difícil.</p>\n"
			+ "\n"
			+ "    <p>Saludos cordiales,</p>\n"
			+ "\n"
			+ "</body>\n"
			+ "</html>";
	
}

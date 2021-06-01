package br.edu.ifpb.projeto.vacinacao.email;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.projeto.vacinacao.model.Usuario;
import br.edu.ifpb.projeto.vacinacao.model.Vacinacao;

@RestController
public class RegistrationController {

	private Logger logger = LoggerFactory.getLogger(RegistrationController.class);
	
	@Autowired
	private NotificationService notificationService;
	
	@GetMapping("/envio-notificacoes")
	public String envioNotificacoes() {
	
		Usuario usuario = new Usuario();
		Vacinacao vacinacao = new Vacinacao();
		LocalDate data1 = LocalDate.now();
		LocalDate data2 = LocalDate.now();
		
		usuario.setNome("Karoline Lima");
		usuario.setEmail("sistemadevacinacao@gmail.com");
		usuario.setSenhaVacina("123");
		vacinacao.setLocalVacinacao("Posto de vacinação");
		vacinacao.setPrimeiraDose(data1);
		vacinacao.setSegundaDose(data2);
		
		try {
			notificationService.sendNotification(usuario,vacinacao);
		}catch(MailException e) {
			logger.info("Error sending email: " + e.getMessage());
		}
		return "Notificação enviadas!";
	}
}

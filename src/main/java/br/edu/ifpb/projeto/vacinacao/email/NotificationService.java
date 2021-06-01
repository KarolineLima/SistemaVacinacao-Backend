package br.edu.ifpb.projeto.vacinacao.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import br.edu.ifpb.projeto.vacinacao.model.Usuario;
import br.edu.ifpb.projeto.vacinacao.model.Vacinacao;

@Service
public class NotificationService {

	private JavaMailSender javaMailSender;
	
	@Autowired
	public NotificationService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	
	public void sendNotification(Usuario usuario, Vacinacao vacinacao) throws MailException {
		
		SimpleMailMessage mail =  new SimpleMailMessage();
		mail.setTo(usuario.getEmail());
		mail.setFrom("sistemadevacinacao@gmail.com");
		mail.setSubject("Sua vacinação foi agendada!");
		
		String message = "Olá, " + usuario.getNome()
				+ "! \n Sua vacinação foi agendada."
				+ "\n"
				+ "\n 	Informações da vacinação: " 
				+ "\n	- Data da 1ª dose: " + vacinacao.getPrimeiraDose()
				+ "\n	- Data da 2ª dose: " + vacinacao.getSegundaDose()
				+ "\n	- Local: " + vacinacao.getLocalVacinacao() 
				+ "\n	- Senha da vacinação: " + usuario.getSenhaVacina();
		
		mail.setText(message);
		
		javaMailSender.send(mail);
	}
}

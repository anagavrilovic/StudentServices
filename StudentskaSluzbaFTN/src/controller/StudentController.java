package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import model.BazaStudenti;
import model.Student;
import model.Student.Status;
import view.TabbedPane;

public class StudentController {
	
	public static StudentController instance = null;
	
	public static StudentController getInstance() {
		
		if (instance == null) {
			instance = new StudentController();
		}
		return instance;
	}
	
	private StudentController() {}
	
	public String dodajStudenta(String ime, String prezime, String datRodj, String adresa, String brTel, String email, 
			String brIndeksa, String godUpisa, int trGodStudija, Status status) {
		
		// validacija za ime
		if (ime == null) {
			return "Unesite ime studenta!";
		}
		ime = ime.trim();
		if (ime.isEmpty()) { 
			return "Unesite ime studenta!";
		}
		if(!Pattern.matches("[a-zA-Z]+", ime)) {
			return "Ime se mora sastojati isključivo od slova!";
		}

		// validacija za prezime
		if (prezime == null) {
			return "Unesite prezime studenta!";
		}
		prezime = prezime.trim();
		if (prezime.isEmpty()) {
			return "Unesite prezime studenta!";
		}
		
		if(!Pattern.matches("[a-zA-Z]+", prezime)) {
			return "Prezime se mora sastojati isključivo od slova!";
		}

		// validacija za datum
		if (datRodj == null) {
			return "Unesite datum rodjenja studenta!";
		}
		datRodj = datRodj.trim();
		if (datRodj.isEmpty()) {
			return "Unesite datum rodjenja studenta!";
		}
		
		Date date;
		try {
			date = new SimpleDateFormat("dd.MM.yyyy.").parse(datRodj);
		} catch (ParseException e) {
			return "Datum mora biti u formatu dd.MM.yyyy.";
		}
		
		// validacija za adresu
		if (adresa == null) {
			return "Unesite adresu studenta!";
		}
		adresa = adresa.trim();
		if (adresa.isEmpty()) {
			return "Unesite adresu studenta!";
		}
		
		// validacija za broj telefona
		if (brTel == null) {
			return "Unesite broj telefona studenta!";
		}
		brTel = brTel.trim();
		if (brTel.isEmpty()) {
			return "Unesite broj telefona studenta!";
		}
		
		// validacija za email
		if (email == null) {
			return "Unesite e-mail adresu studenta!";
		}
		email = email.trim();
		if (email.isEmpty()) {
			return "Unesite e-mail adresu studenta!";
		}
		 
		if(!Pattern.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-z]{2,}", email)) {
			return "Neispravna e-mail adresa!";
		}
		
		// validacija za broj indeksa
		if (brIndeksa == null) {
			return "Unesite broj indeksa studenta!";
		}
		brIndeksa = brIndeksa.trim();
		if (brIndeksa.isEmpty()) {
			return "Unesite broj indeksa studenta!";
		}
		
		if(!BazaStudenti.getInstance().validirajStudenta(brIndeksa))
			return "Već postoji student sa ovim brojem indeksa!";  
		
		// validacija za godinu upisa
		if (godUpisa == null) {
			return "Unesite godinu upisa studenta!";
		}
		godUpisa = godUpisa.trim();
		if (godUpisa.isEmpty()) {
			return "Unesite godinu upisa studenta!";
		}
		int god = Integer.parseInt(godUpisa);
		
		Student student = new Student();
		student.setIme(ime);
		student.setPrezime(prezime);
		student.setDatumRodjenja(date);
		student.setBrojIndeksa(brIndeksa);
		student.setAdresaStanovanja(adresa);
		student.setEmail(email);
		student.setKontaktTelefon(brTel);
		student.setGodinaUpisa(god);
		student.setTrGodStudija(trGodStudija);
		student.setStatus(status);
		
		BazaStudenti.getInstance().dodajStudenta(student);
		TabbedPane.getInstance().azurirajPrikaz("Dodavanje studenta", -1);

		return "Model uspešno ažuriran!";
		}
	
}
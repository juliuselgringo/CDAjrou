package fr.juliuselgringo.sparadrap.model;

import fr.juliuselgringo.sparadrap.DAO.ContenirCRUD;
import fr.juliuselgringo.sparadrap.DAO.CustomerDAO;
import fr.juliuselgringo.sparadrap.DAO.DoctorDAO;
import fr.juliuselgringo.sparadrap.DAO.DrugDAO;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import fr.juliuselgringo.sparadrap.ExceptionTracking.InputException;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import fr.juliuselgringo.sparadrap.utility.Display;

import java.io.File;
import java.io.IOException;

/**
 * classe ordonnance
 */
public class Prescription {

    private Integer prescriptionId;
    private LocalDate prescriptionDate;
    private Integer doctorId;
    private Integer customerId;
    private List<Contenir> drugsQuantityPrescriptionsList = new ArrayList<>();

    /**
     * numéro d'achat
     */

    private String pathPdf;

    /**
     * CONSTRUCTOR
     * @param prescriptionDateIn String
     * @param doctorId Integer
     * @param customerId Integer
     * @throws InputException String
     */
    public Prescription(String prescriptionDateIn, Integer doctorId, Integer customerId) throws InputException {
        this.setPrescriptionDate(prescriptionDateIn);
        this.setDoctorId(doctorId);
        this.setCustomerId(customerId);
    }

    /**
     * CONSTRUCTOR
     * @param prescriptionId Integer
     * @param prescriptionDateIn String
     * @param doctorId Integer
     * @param customerId Integer
     * @throws InputException String
     */
    public Prescription(Integer prescriptionId, String prescriptionDateIn, Integer doctorId, Integer customerId) throws InputException {
        this.prescriptionId = prescriptionId;
        this.setPrescriptionDate(prescriptionDateIn);
        this.setDoctorId(doctorId);
        this.setCustomerId(customerId);
    }


    /**
     * CONSTRUCTOR
     */
    public Prescription(){};

    /**
     * getter prescriptionId
     * @return Integer
     */
    public Integer getPrescriptionId() {
        return prescriptionId;
    }

    /**
     * setter prescriptionId
     * @param prescriptionId Integer
     */
    public void setPrescriptionId(Integer prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    /**
     * GETTER prescriptionDate
     * @return LocalDate
     */
    public LocalDate getPrescriptionDate() {
        return this.prescriptionDate;
    }

    /**
     * SETTER prescriptionDate
     * @param prescriptionDateIn String
     * @throws InputException String
     */
    public void setPrescriptionDate(String prescriptionDateIn) throws InputException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate prescriptionDateInLD = null;
        try {
            prescriptionDateInLD = LocalDate.parse(prescriptionDateIn, formatter);

        }catch(DateTimeParseException dtpe){
            JOptionPane.showMessageDialog(null, "Date de prescription invalide.",
                    "Erreur", JOptionPane.ERROR_MESSAGE);
        }

        if (prescriptionDateInLD.isAfter(LocalDate.now())) {
            throw new InputException("La date de prescription ne peut être après la date d'aujourd'hui!");
        } else {
            this.prescriptionDate = prescriptionDateInLD;
        }

    }

    /**
     * GETTER doctorId
     * @return Integer
     */
    public Integer getDoctorId() {
        return this.doctorId;
    }

    /**
     * SETTER doctorId
     * @param doctorId Integer
     * @throws InputException String
     */
    public void setDoctorId(Integer doctorId) throws InputException {
        this.doctorId = doctorId;
    }

    /**
     * GETTER custormerId
     * @return Integer
     */
    public Integer getCustomerId() {
        return this.customerId;
    }

    /**
     * SETTER customerId
     * @param customerId Integer
     * @throws InputException String
     */
    public void setCustomerId(Integer customerId) throws InputException {
        this.customerId = customerId;
    }

    /**
     * GETTER getDrugsQuantityPrescriptionList
     * @return List
     */
    public List<Contenir> getDrugsQuantityPrescriptionList() {
        return this.drugsQuantityPrescriptionsList;
    }

    /**
     * SETTER drugsDrugsQuantityPrescritionList utilisé à l'enregistrement du PDF
     * @param drugsQuantityPrescriptionList List
     */
    public void setDrugsQuantityPrescriptionList(List<Contenir> drugsQuantityPrescriptionList) {
        this.drugsQuantityPrescriptionsList = drugsQuantityPrescriptionList;
    }

    /**
     * GETTER pathPdf
     * @return pathPdf String
     */
    public String getPathPdf(){
        return this.pathPdf;
    }

    /**
     * SETTER pathPdf
     * @param pathPdf String
     */
    public void setPathPdf(String pathPdf){
        this.pathPdf = pathPdf.trim();
    }

    /**
     * TO STRING
     * @return String
     */
    @Override
    public String toString() {
        DoctorDAO doctorDAO = new DoctorDAO();
        Doctor doctor = doctorDAO.getById(this.doctorId);
        CustomerDAO customerDAO = new CustomerDAO();
        Customer customer = customerDAO.getById(this.customerId);
        customerDAO.closeConnection();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return "Date: " + this.getPrescriptionDate().format(formatter) +
                "\nNom du medecin: " + doctor.getLastName() +
                "\nNom du client:  " + customer.getLastName() +
                "\nListe des médicament: " + this.getDrugsQuantityToString();
    }

    /**
     * TO STRING POUR L ENREGISTREMENT DES PRESCRIPTION EN PDF
     * @param purchaseId Integer
     * @return String
     */
    public String toStringForPdf(Integer purchaseId){
        DoctorDAO doctorDAO = new DoctorDAO();
        Doctor doctor = doctorDAO.getById(this.doctorId);
        CustomerDAO customerDAO = new CustomerDAO();
        Customer customer = customerDAO.getById(this.customerId);
        ContenirCRUD contenirDAO = new ContenirCRUD();
        this.setDrugsQuantityPrescriptionList(contenirDAO.selectAll(purchaseId));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return " // " + this.getPrescriptionDate().format(formatter) +
                " / Medecin: " + doctor.getLastName() +
                " / Client:  " + customer.getLastName() +
                " / Médicaments: " + this.getDrugsQuantityToString();
    }

    /**
     * L AFFICHAGE DE QUANTITE COMMANDE OU SORTIE DE STOCK
     * @return String
     */
    public String getDrugsQuantityToString() {
        String stringToReturn = "";
        DrugDAO drugDAO = new DrugDAO();
        for (Contenir contenir : this.getDrugsQuantityPrescriptionList()){
            stringToReturn += drugDAO.getById(contenir.getDrugId()) + "\n Quantite: " +  contenir.getQuantity() + "\n";
        }
        return stringToReturn;
    }

    /**
     * SAUVEGARDER UNE PRESCRIPTION EN PDF
     * @param purchaseId Integer
     * @throws IOException String
     */
    public void savePrescriptionAsPdf(Integer purchaseId) throws IOException {
        CustomerDAO customerDAO = new CustomerDAO();
        Customer customer = customerDAO.getById(this.getCustomerId());

        String pathHistoric = "historic/"
                + customer.getLastName() + this.prescriptionDate + ".pdf";
        try(PDDocument document = new PDDocument()){
            PDPage page = new PDPage();
            document.addPage(page);

            PDType0Font font = PDType0Font.load(document,
                    new File("fonts/arial.ttf"));

            try(PDPageContentStream contentStream = new PDPageContentStream(document, page)){

                contentStream.beginText();
                contentStream.setFont(font, 12);
                contentStream.newLineAtOffset(50, 700);

                List<String> lines = sliceTextForPdf(this.toStringForPdf(purchaseId),100);

                try {
                    for (String line : lines) {
                        contentStream.showText(line);
                        contentStream.newLineAtOffset(0, -20);
                    }
                } finally {
                    contentStream.endText();
                }

            }

            document.save(pathHistoric);
            this.setPathPdf(pathHistoric);
            JOptionPane.showMessageDialog(null,"PDF créé !");

        }catch(IOException ioe){
            System.err.println("erreur : " + ioe.getMessage());
        }
    }

    /**
     * DECOUPE LE TEXTE DES PRESCRIPTION EN LIGNE POUR L ENRGISTREMENT PDF
     * @param text String
     * @param lineMax int
     * @return List
     */
    public static List<String> sliceTextForPdf(String text, int lineMax){
        List<String> stringToReturn = new ArrayList<>();

        if(text == null || text.isEmpty()){
            return stringToReturn;
        }

        // Normaliser les retours à la ligne
        text = text.replace("\r","");
        // Séparer par paragraphe
        String[] paragraphs = text.split("\n");
        for(String paragraph : paragraphs){
            String trimmed = paragraph.trim();
            if(trimmed.isEmpty()){
                stringToReturn.add("");
                continue;
            }
            int index = 0;
            while(index < trimmed.length()){
                int endline = Math.min(trimmed.length(), index + lineMax);
                stringToReturn.add(trimmed.substring(index, endline));
                index = endline;
            }
        }
        return stringToReturn;
    }

    /**
     * OUVRE UNE PRESCRIPTION PDF
     */
    public void openPdfPrescription(){
        try {
            File pdfFile = new File("historic/"
                    + this.getCustomer().getLastName() + this.getPrescriptionDate() + ".pdf");
            if (pdfFile.exists()) {
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(pdfFile);
                } else {
                    Display.error("Desktop n'est pas supporté");
                }
            }
        } catch(NullPointerException npe){
                    Display.error("Le fichier n'existe pas");
                    JOptionPane.showMessageDialog(null, "Le fichier n'existe pas", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    /**
     * retourne le client correspondant à la prescription
     * @return Customer
     */
    public Customer getCustomer(){
        CustomerDAO customerDAO = new CustomerDAO();
        Customer customer = customerDAO.getById(this.getCustomerId());
        customerDAO.closeConnection();
        return customer;
    }

    /**
     * retourne le docteur correspondant à la prescription
     * @return Doctor
     */
    public Doctor getDoctor(){
        DoctorDAO doctorDAO = new DoctorDAO();
        Doctor doctor = doctorDAO.getById(this.getDoctorId());
        doctorDAO.closeConnection();
        return doctor;
    }
}

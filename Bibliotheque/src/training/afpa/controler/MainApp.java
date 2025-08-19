package training.afpa.controler;

import training.afpa.model.Person;
import training.afpa.model.Subscriber;

import java.time.LocalDate;

import static java.time.LocalTime.now;

public class MainApp {

    public static void main(String[] args) {

        Subscriber subscriber = new Subscriber("Sandro","Boucher","sandro.boucher@afpa.training");
        System.out.println(subscriber.toString());
    }
}

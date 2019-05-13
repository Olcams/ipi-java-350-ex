package com.ipiecoles.java.java350.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

public class EmployeTest {

    @Test
    public void getNombreAnneeAncienneteNow(){
        //Given
        Employe e = new Employe();
        e.setDateEmbauche(LocalDate.now());

        //When
        Integer anneeAnciennete = e.getNombreAnneeAnciennete();

        //Then
        Assertions.assertEquals(0, anneeAnciennete.intValue());
    }

    @Test
    public void getNombreAnneeAncienneteNminus2(){
        //Given
        Employe e = new Employe();
        e.setDateEmbauche(LocalDate.now().minusYears(2L));

        //When
        Integer anneeAnciennete = e.getNombreAnneeAnciennete();

        //Then
        Assertions.assertEquals(2, anneeAnciennete.intValue());
    }

    @Test
    public void getNombreAnneeAncienneteNull(){
        //Given
        Employe e = new Employe();
        e.setDateEmbauche(null);

        //When
        Integer anneeAnciennete = e.getNombreAnneeAnciennete();

        //Then
        Assertions.assertEquals(0, anneeAnciennete.intValue());
    }

    @Test
    public void getNombreAnneeAncienneteNplus2(){
        //Given
        Employe e = new Employe();
        e.setDateEmbauche(LocalDate.now().plusYears(2L));

        //When
        Integer anneeAnciennete = e.getNombreAnneeAnciennete();

        //Then
        Assertions.assertEquals(0, anneeAnciennete.intValue());
    }

    @ParameterizedTest
    @CsvSource({
            "1, 'T12345', 0, 1.0, 1000.0",
            "1, 'T12345', 2, 0.5, 600.0",
            "1, 'T12345', 2, 1.0, 1200.0",
            "2, 'T12345', 0, 1.0, 2300.0",
            "2, 'T12345', 1, 1.0, 2400.0",
            "1, 'M12345', 0, 1.0, 1700.0",
            "1, 'M12345', 5, 1.0, 2200.0",
            "2, 'M12345', 0, 1.0, 1700.0",
            "2, 'M12345', 8, 1.0, 2500.0"
    })
    public void getPrimeAnnuelle(Integer performance, String matricule, Long nbYearsAnciennete, Double tempsPartiel, Double primeAnnuelle){
        //Given
        Employe employe = new Employe("Doe", "John", matricule, LocalDate.now().minusYears(nbYearsAnciennete), Entreprise.SALAIRE_BASE, performance, tempsPartiel);

        //When
        Double prime = employe.getPrimeAnnuelle();

        //Then
        Assertions.assertEquals(primeAnnuelle, prime);

    }

    //Tests unitaires de la méthode augmenterSalaire d'Employe :
    @Test
    public void getSalaireAugmentePourcentageNul(){
        //Given
        Employe e = new Employe();
        e.setSalaire(1700.0);

        //When
        Double salaireAugmente = e.augmenterSalaire(0.0);

        //Then
        Assertions.assertEquals(1700.0,salaireAugmente.doubleValue());
    }

    @Test
    public void getSalaireAugmentePourcentageSuperieurA1(){
        //Given
        Employe e = new Employe();
        e.setSalaire(1700.0);

        //When
        Double salaireAugmente = e.augmenterSalaire(2.0);

        //Then
        Assertions.assertEquals(1700.0,salaireAugmente.doubleValue());
    }

    @Test
    public void getSalaireAugmentePourcentagePositif(){
        //Given
        Employe e = new Employe();
        e.setSalaire(1700.0);

        //When
        Double salaireAugmente = e.augmenterSalaire(0.1);

        //Then
        Assertions.assertEquals(1870.0,salaireAugmente.doubleValue());
    }

    //Tests paramétrés de la méthode getNbRtt d'Employe :
    @ParameterizedTest
    @CsvSource({
            //Calcul pour 2019 : 365 - 218 - 104 - 10 - 25 = 8
            "2019-03-31, 1, 8",
            "2019-03-31, 0.5, 4",
            "2021-06-06, 1.0, 10",
            "2021-06-06, 0.5, 5",
            "2022-06-06, 1, 10",
            "2032-06-06, 1, 11",
            "2032-02-29, 0.5, 6"
    })
    public void getNbRtt(LocalDate date, Double tempsPartiel, Integer nbRttOk){
        //Given
        Employe employe = new Employe("Doe", "John", "M00001", LocalDate.now(), Entreprise.SALAIRE_BASE, 1, tempsPartiel);

        //When
        Integer nbRttCalcule = employe.getNbRtt(date);

        //Then
        Assertions.assertEquals(nbRttOk,nbRttCalcule);

    }


}
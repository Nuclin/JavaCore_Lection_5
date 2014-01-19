package com.javacources.HH;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Yuriy on 22.12.13.
 */
public class JobsParser {
    public static void main(String[] args) throws MalformedURLException, SQLException {
        //version1
        URL url = new URL("http://jobs.dou.ua/vacancies/feeds/?search=Java");
        VacancyDAODB vacancyDAODB = new VacancyDAODB("jdbc:derby:/home/slava/DataBase-Vacancy");
        HHController controller = new HHController();
        String doc = null;
        try {
            doc = controller.getDocument(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (doc != null) {
            List<Vacancy> vacancies = controller.getVacancies(doc);
            for (Vacancy vacancy : vacancies) {
                vacancyDAODB.addVacancy(vacancy);
            }
        }
        System.out.println(vacancyDAODB.find("Java"));

    }
}

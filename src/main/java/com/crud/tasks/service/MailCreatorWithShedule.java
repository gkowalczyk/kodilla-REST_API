package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyDet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorWithShedule {

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;
    @Autowired
    private AdminConfig adminConfig;
    @Autowired
    private CompanyDet companyDet;

    public String buildTrelloCardEmail(String message) {

        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("application_functionality", functionality);
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://gkowalczyk.github.io");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_config", adminConfig); //obiekt do widoku
        context.setVariable("show_button", false);
        context.setVariable("is_friend",true);
        context.setVariable("company_details",
                companyDet.getCompanyName() + "," +
                        companyDet.getCompanyGoal() + "," +
                        companyDet.getCompanyEmail() + "," +
                        companyDet.getCompanyPhone());
        return templateEngine.process("created-trello-card-mail", context);
    }
}

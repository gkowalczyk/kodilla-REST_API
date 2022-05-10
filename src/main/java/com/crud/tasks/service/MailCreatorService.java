package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyDet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailCreatorService {

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;
    @Autowired
    private AdminConfig adminConfig;
    @Autowired
    private CompanyDet companyDet;

    public String buildTrelloCardEmail(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://gkowalczyk.github.io");
        context.setVariable("button", "Visit website");
        context.setVariable("final_info", adminConfig.getFinalInfo());
        context.setVariable("admin_name", adminConfig.getAdminName());
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



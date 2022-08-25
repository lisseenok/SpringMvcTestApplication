package ru.lisenok.springmvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.lisenok.springmvc.dao.PersonDAO;

@Controller
@RequestMapping("/test-batch-update")
public class BatchController {

    private final PersonDAO personDAO;

    @Autowired
    public BatchController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping
    public String index(){
        return "batch/index";
    }

    @GetMapping("/without") // requests without batch update
    public String withoutBatch(){
        personDAO.testMultipleUpdate();
        return "redirect:/people";
    }

    @GetMapping("/with") // requests with batch update
    public String withBatch(){
        personDAO.testBatchUpdate();
        return "redirect:/people";
    }
}

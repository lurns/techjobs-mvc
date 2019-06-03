package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results
    @RequestMapping(value = "results")
    public String searchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
        model.addAttribute("title","Search Results");
        model.addAttribute("columns", ListController.columnChoices);
        int counter = 0; //count jobs
        if (searchType.equals("all")) {
            ArrayList<HashMap<String, String>> jobs = JobData.findByValue(searchTerm);
            for (HashMap<String, String> job : jobs) {
                counter++;
            }
            model.addAttribute("jobs", jobs);
            model.addAttribute("counter", counter);
            model.addAttribute("lastTerm", searchTerm);
            return "search";
        } else {
            ArrayList<HashMap<String, String>> jobs = JobData.findByColumnAndValue(searchType, searchTerm);
            for (HashMap<String, String> job : jobs) {
                counter++;
            }
            model.addAttribute("jobs", jobs);
            model.addAttribute("counter", counter);
            model.addAttribute("lastTerm", searchTerm);
            return "search";
        }
    }

}

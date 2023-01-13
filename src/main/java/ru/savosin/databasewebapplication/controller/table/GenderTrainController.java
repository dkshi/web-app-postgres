package ru.savosin.databasewebapplication.controller.table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.savosin.databasewebapplication.service.csv.GenderTrainCsvService;
import ru.savosin.databasewebapplication.entity.GenderTrain;
import ru.savosin.databasewebapplication.service.interfaces.GenderTrainService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("tables")
public class GenderTrainController {
    private GenderTrainService genderTrainService;

    private GenderTrainCsvService genderTrainCsvService;


    @Autowired
    public GenderTrainController(GenderTrainService genderTrainService,
                                 GenderTrainCsvService genderTrainCsvService) {
        this.genderTrainService = genderTrainService;
        this.genderTrainCsvService = genderTrainCsvService;
    }

    @GetMapping("gender-train")
    public String showGenderTrain(Model model){
        model.addAttribute("genderTrains", genderTrainService.fetchAll());
        return "tables/gender-train";
    }

    @GetMapping("/exportGenderTrain")
    public void exportGenderTrain(HttpServletResponse response) throws Exception {
        genderTrainCsvService.exportCsvFile(response);
    }

    @PostMapping("gender-train")
    public String addGenderTrain(@RequestParam(value="customerId", required = false) Integer customerId,
                                 @RequestParam(value = "gender", required = false) String gender,
                                 @RequestParam(value="file", required = false) MultipartFile file,
                                 Model model) throws IOException {
        if(file != null)
            genderTrainCsvService.writeCsvFile(file);

        if(customerId != null) {
            genderTrainService.save(new GenderTrain(customerId, gender));
        }

        model.addAttribute("genderTrains", genderTrainService.fetchAll());
        return "tables/gender-train";
    }

}

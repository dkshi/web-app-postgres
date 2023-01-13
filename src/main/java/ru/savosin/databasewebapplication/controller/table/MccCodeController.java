package ru.savosin.databasewebapplication.controller.table;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.savosin.databasewebapplication.service.csv.MccCodeCsvService;
import ru.savosin.databasewebapplication.entity.MccCode;
import ru.savosin.databasewebapplication.service.interfaces.MccCodeService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("tables")
public class MccCodeController {
    private MccCodeService mccCodeService;

    private MccCodeCsvService mccCodeCsvService;

    @Autowired
    public MccCodeController(MccCodeService mccCodeService, MccCodeCsvService mccCodeCsvService) {
        this.mccCodeCsvService = mccCodeCsvService;
        this.mccCodeService = mccCodeService;
    }

    @GetMapping("tr-mcc-codes")
    public String showMccCode(Model model){
        model.addAttribute("mccCodes", mccCodeService.fetchAll());
        return "tables/tr-mcc-codes";
    }

    @GetMapping("/exportMccCodes")
    public void exportGenderTrain(HttpServletResponse response) throws Exception {
        mccCodeCsvService.exportCsvFile(response);
    }

    @PostMapping("tr-mcc-codes")
    public String addMccCode(@RequestParam(value = "mccCodeId", required = false) Integer mccCodeId,
                             @RequestParam(value = "mccDescription", required = false) String mccDescription,
                             @RequestParam(value = "file", required = false) MultipartFile file,
                             Model model) throws IOException {
        if(file != null)
            mccCodeCsvService.writeCsvFile(file);

        if (mccCodeId != null)
            mccCodeService.save(new MccCode(mccCodeId, mccDescription));
        model.addAttribute("mccCodes", mccCodeService.fetchAll());
        return "tables/tr-mcc-codes";
    }
}

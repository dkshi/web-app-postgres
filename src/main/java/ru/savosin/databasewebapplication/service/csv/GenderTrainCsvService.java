package ru.savosin.databasewebapplication.service.csv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.savosin.databasewebapplication.entity.GenderTrain;
import ru.savosin.databasewebapplication.service.interfaces.CsvService;
import ru.savosin.databasewebapplication.service.interfaces.GenderTrainService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@Service
public class GenderTrainCsvService extends CsvConfig implements CsvService {
    private GenderTrainService genderTrainService;

    @Autowired
    public GenderTrainCsvService(GenderTrainService genderTrainService) {
        this.genderTrainService = genderTrainService;
    }

    @Override
    public void writeCsvFile(MultipartFile file) throws IOException {
        getRecords(file).forEach(record -> {
            GenderTrain genderTrain = new GenderTrain();
            genderTrain.setCustomerId(Integer.parseInt(record.getString("customer_id")));
            genderTrain.setGender(record.getString("gender"));
            genderTrainService.save(genderTrain);
        });
        closeInputStream();
    }
    @Override
    public void exportCsvFile(HttpServletResponse response) throws Exception{
        String fileName = "gender_train.csv";

        List<GenderTrain> genderTrains = genderTrainService.fetchAll();

        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"" );

        PrintWriter writer = response.getWriter();

        String[] header = new String[]{"customer_id", "gender"};
        writer.println(String.join(",", header));
        for(GenderTrain genderTrain : genderTrains){
            writer.println(genderTrain.toString());
        }
    }


}

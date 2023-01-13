package ru.savosin.databasewebapplication.service.csv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.savosin.databasewebapplication.entity.MccCode;
import ru.savosin.databasewebapplication.service.interfaces.CsvService;
import ru.savosin.databasewebapplication.service.interfaces.MccCodeService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Service
public class MccCodeCsvService extends CsvConfig implements CsvService {
    private MccCodeService mccCodeService;

    @Autowired
    public MccCodeCsvService(MccCodeService mccCodeService) {
        this.mccCodeService = mccCodeService;
    }

    @Override
    public void writeCsvFile(MultipartFile file) throws IOException {
        getRecords(file).forEach(record -> {
            MccCode mccCode = new MccCode();
            mccCode.setMccCodeId(Integer.parseInt(record.getString("mcc_code")));
            mccCode.setMccDescription(record.getString("mcc_description"));
            mccCodeService.save(mccCode);
        });
        closeInputStream();
    }
    @Override
    public void exportCsvFile(HttpServletResponse response) throws Exception{
        String fileName = "tr_mcc_codes.csv";

        List<MccCode> mccCodes = mccCodeService.fetchAll();

        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"" );

        PrintWriter writer = response.getWriter();

        String[] header = new String[]{"mcc_code", "mcc_description"};
        writer.println(String.join(",", header));
        for(MccCode mccCode : mccCodes){
            writer.println(mccCode.toString());
        }
    }
}

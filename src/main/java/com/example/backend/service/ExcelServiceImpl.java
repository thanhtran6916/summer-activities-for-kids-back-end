package com.example.backend.service;

import com.example.backend.dto.StudentDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExcelServiceImpl implements ExcelService {

    @Override
    public String genStudentsPass(MultipartFile fileExcel) {
        try {
            // lấy danh sách học sinh trong file excel
            List<StudentDTO> studentDTOSInput = getStudentsExcel(fileExcel);
            System.out.println(studentDTOSInput);
            // lọc học sinh đạt

            // trả ra file excel danh sách học sinh đạt
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return null;
    }

    private List<StudentDTO> getStudentsExcel(MultipartFile fileExcel) throws IOException {
        InputStream inputStream = fileExcel.getInputStream();
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheet("DS ĐT đăng ký cho con em");
        Iterator<Row> rows = sheet.iterator();
        List<StudentDTO> studentDTOS = new ArrayList<>();
        int rowNumber = 0;
        while (rows.hasNext()) {
            Row currentRow = rows.next();

            // skip header
            if (rowNumber == 0 || rowNumber == 1) {
                rowNumber++;
                continue;
            }
            Iterator<Cell> cellsInRow = currentRow.iterator();
            StudentDTO studentDTO = new StudentDTO();
            int cellIdx = 0;
            while (cellsInRow.hasNext()) {
                Cell currentCell = cellsInRow.next();
                switch (cellIdx) {
                    case 0:
                        studentDTO.setId((int) currentCell.getNumericCellValue());
                        break;
                    case 1: {
                        studentDTO.setCreatedDate(currentCell.getDateCellValue());
                        break;
                    }
                    case 2:
                        studentDTO.setName(currentCell.getStringCellValue());
                        break;
                    case 3:
                        studentDTO.setDharmaName(currentCell.getStringCellValue());
                        break;
                    case 4:
                        studentDTO.setGender(currentCell.getStringCellValue());
                        break;
                    case 5:
                        studentDTO.setYearBirth((int) currentCell.getNumericCellValue());
                        break;
                    case 6:
                        studentDTO.setRelativeName(currentCell.getStringCellValue());
                        break;
                    case 7:
                        studentDTO.setRelativePhone(currentCell.getStringCellValue());
                        break;
                    case 8:
                        studentDTO.setAddress(currentCell.getStringCellValue());
                        break;
                    case 9:
                        studentDTO.setNumberOfParticipation((int) currentCell.getNumericCellValue());
                        break;
                    case 10:
                        studentDTO.setHealthCondition(currentCell.getStringCellValue());
                        break;
                    case 11:
                        studentDTO.setConduct(currentCell.getStringCellValue());
                        break;
                    case 12:
                        studentDTO.setSummerCourseName(currentCell.getStringCellValue());
                        break;
                    case 13:
                        studentDTO.setIntroducePerson(currentCell.getStringCellValue());
                        break;
                    case 14:
                        studentDTO.setIntroducePersonPhone(currentCell.getStringCellValue());
                        break;
                    default:
                        break;
                }
                cellIdx++;
            }
            studentDTOS.add(studentDTO);
        }
        return studentDTOS;
    }
}

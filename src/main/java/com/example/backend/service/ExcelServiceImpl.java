package com.example.backend.service;

import com.example.backend.dto.StudentDTO;
import com.example.backend.mapper.StudentMapper;
import com.example.backend.util.Constant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExcelServiceImpl implements ExcelService {

    private final StudentService studentService;
    private final StudentMapper studentMapper;

    @Override
    public String genStudentsPass(MultipartFile fileExcel) {
        String result = "";
        try {
            // lấy danh sách học sinh trong file excel
            List<StudentDTO> studentDTOSInput = getInputStudentsExcel(fileExcel);
            // lọc học sinh đạt
            List<StudentDTO> studentDTOSPass = filterStudentPass(studentDTOSInput);

            // trả ra file excel danh sách học sinh đạt
            result = getExcelNameStudentPass(studentDTOSPass);
        } catch (Exception e) {
            log.info(e.getMessage());
            return result;
        }
        return result;
    }

    private String getExcelNameStudentPass(List<StudentDTO> studentDTOSPass) {
        return null;
    }

    private List<StudentDTO> filterStudentPass(List<StudentDTO> studentDTOSInput) {
        List<StudentDTO> studentDTOSPass = new ArrayList<>();
        for (StudentDTO studentDTO : studentDTOSInput) {
            boolean isValidAge = validateAge(studentDTO);
            if (!isValidAge) {
                studentService.updateStudent(studentDTO);
                continue;
            }
            boolean isValidNumberOfParticipation = validateNumberOfParticipation(studentDTO);
            if (!isValidNumberOfParticipation) {
                studentService.updateStudent(studentDTO);
                continue;
            }
            boolean isValidConduct = validateConduct(studentDTO);
            if (!isValidConduct) {
                studentService.updateStudent(studentDTO);
                continue;
            }
            boolean isValidAcademyAbility = validateAcademicAbility(studentDTO);
            if (!isValidAcademyAbility) {
                studentService.updateStudent(studentDTO);
                continue;
            }

            studentDTO.setStatus(Constant.STATUS.PASS);
            studentService.updateStudent(studentDTO);
            studentDTOSPass.add(studentDTO);
        }
        return studentDTOSPass;
    }

    private boolean validateAcademicAbility(StudentDTO studentDTO) {
        if (!Constant.RULE_STUDENT.HOC_LUC_GIOI.equalsIgnoreCase(studentDTO.getAcademicAbility())
                || !Constant.RULE_STUDENT.HOC_LUC_KHA.equalsIgnoreCase(studentDTO.getAcademicAbility())) {
            studentDTO.setStatus(Constant.STATUS.REJECT);
            studentDTO.setReasonReject(Constant.REASON_REJECT.ACADEMIC_NOT_VALID);
            return false;
        }
        return true;
    }

    private boolean validateConduct(StudentDTO studentDTO) {
        if (!Constant.RULE_STUDENT.HANH_KIEM_TOT.equalsIgnoreCase(studentDTO.getConduct())
                || !Constant.RULE_STUDENT.HANH_KIEM_KHA.equalsIgnoreCase(studentDTO.getConduct())) {
            studentDTO.setStatus(Constant.STATUS.REJECT);
            studentDTO.setReasonReject(Constant.REASON_REJECT.CONDUCT_NOT_VALID);
            return false;
        }
        return true;
    }

    private boolean validateNumberOfParticipation(StudentDTO studentDTO) {
        if (studentDTO.getNumberOfParticipation() >= 1) {
            studentDTO.setStatus(Constant.STATUS.REJECT);
            studentDTO.setReasonReject(Constant.REASON_REJECT.PARTICIPATION_NOT_VALID);
            return false;
        }
        return true;
    }

    private boolean validateAge(StudentDTO studentDTO) {
        LocalDate localDate = LocalDate.now();
        if (localDate.getYear() - studentDTO.getYearBirth() < 10
                || localDate.getYear() - studentDTO.getYearBirth() > 16) {
            studentDTO.setStatus(Constant.STATUS.REJECT);
            studentDTO.setReasonReject(Constant.REASON_REJECT.AGE_NOT_VALID);
            return false;
        }
        return true;
    }

    private List<StudentDTO> getInputStudentsExcel(MultipartFile fileExcel) throws IOException {
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

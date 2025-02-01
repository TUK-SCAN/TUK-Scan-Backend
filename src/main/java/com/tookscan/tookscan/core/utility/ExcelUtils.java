package com.tookscan.tookscan.core.utility;

import com.tookscan.tookscan.core.exception.error.ErrorCode;
import com.tookscan.tookscan.core.exception.type.CommonException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.web.multipart.MultipartFile;

public class ExcelUtils {

    private ExcelUtils() {
    }

    @FunctionalInterface
    public interface ExcelRowMapper<T> {
        T mapRow(Row row, int rowNum);
    }

    public static <T> List<T> parseExcel(MultipartFile file,
                                         int startRowIndex,
                                         ExcelRowMapper<T> rowMapper) {
        try {
            List<T> result = new ArrayList<>();
            try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
                Sheet sheet = workbook.getSheetAt(0);
                int rowCount = sheet.getPhysicalNumberOfRows();

                for (int i = startRowIndex; i < rowCount; i++) {
                    Row row = sheet.getRow(i);
                    if (row == null) {
                        continue;
                    }
                    T obj = rowMapper.mapRow(row, i);
                    result.add(obj);
                }
            }
            return result;
        } catch (IOException e) {
            throw new CommonException(ErrorCode.INTERNAL_SERVER_ERROR, "파일을 읽는 중 오류가 발생했습니다.");
        }

    }

    // 예시: 셀을 안전하게 문자열로 꺼내는 함수
    public static String getStringCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        cell.setCellType(org.apache.poi.ss.usermodel.CellType.STRING);
        return cell.getStringCellValue().trim();
    }

    // 필요하면 숫자, 날짜 파싱 등도 추가
    // ...
}
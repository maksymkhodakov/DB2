package com.lab.lab2.services;

import com.lab.lab2.domain.DTO.DownloadFileDTO;

import java.util.List;

public interface ExcelWriter {
    <T> DownloadFileDTO writeToExcel(String fileName, String contentType, List<T> data);
    String generateExcelFileName(Integer depth);
}

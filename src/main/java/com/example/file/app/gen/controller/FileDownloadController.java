package com.example.file.app.gen.controller;

import com.example.file.app.gen.entity.GenFile;
import com.example.file.app.gen.service.GenFileService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

@Controller
@RequestMapping("/download")
@RequiredArgsConstructor
public class FileDownloadController {
    private final GenFileService genFileService;

    @SneakyThrows
    @GetMapping("/gen/{id}")
    public void download(HttpServletResponse response, @PathVariable Long id) {
        GenFile genFile = genFileService.getById(id).get();

        String path = genFile.getFilePath();

        File file = new File(path);
        response.setHeader("Content-Disposition", "attachment;filename=" + genFile.getOriginFileName());

        FileInputStream fileInputStream = new FileInputStream(path);
        OutputStream out = response.getOutputStream();

        int read = 0;
        byte[] buffer = new byte[1024];
        while ((read = fileInputStream.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
    }
}

package com.devfuner.study.exceldownload.sample;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class SampleController {


    private void setModel(Model model) {
        List<SampleData> list = Arrays.asList(
                new SampleData("김호균", 7000, 10000),
                new SampleData("진요한", 8000, 9000),
                new SampleData("정다영", 9000, 8000)
        );
        model.addAttribute("list", list);
    }

    @GetMapping("/sample")
    public String sample(Model model) {

        setModel(model);

        return "sample";
    }

    @GetMapping(value = "/excel/download", produces = "application/vnd.ms-excel")
    public String download(Model model) {

        List<String> header = Arrays.asList("이름", "밥값1", "밥값2");
        model.addAttribute("header", header);
        model.addAttribute("downloadFileName", "excel_download");
        setModel(model);

        return "excelView";
    }
}

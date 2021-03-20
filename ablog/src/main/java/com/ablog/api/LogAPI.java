package com.ablog.api;

import com.ablog.pojo.Article;
import com.ablog.pojo.Log;
import com.ablog.service.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogAPI {

    private static final Logger logger = LoggerFactory.getLogger(LogAPI.class);

    @Autowired
    LogService logService;

    @GetMapping("/api/log/list/{currentPage}/{pageSize}")
    public Page<Log> LogList(@PathVariable("currentPage")int currentPage, @PathVariable("pageSize")int pageSize){

        Pageable pageable = new PageRequest(currentPage - 1,pageSize, Sort.Direction.DESC,"time");
        Page<Log> logPage = logService.logList(pageable);
        return logPage;

    }

}

package com.ablog.service;

import com.ablog.dao.LogDAO;
import com.ablog.pojo.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LogService {

    private static final Logger logger = LoggerFactory.getLogger(LogService.class);

    @Autowired
    LogDAO logDAO;

    public void log(String ip,String module,String action){

        Log log = new Log(new Date(),action,ip,module);
        logDAO.save(log);

    }

    public Page<Log> logList(Pageable pageable) {

        return logDAO.findAll(pageable);

    }
}

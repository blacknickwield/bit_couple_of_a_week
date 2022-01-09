package com.gjf.bit_couple_of_a_week.handler;

import com.gjf.bit_couple_of_a_week.exception.CoupleDaoException;
import com.gjf.bit_couple_of_a_week.exception.PostDaoException;
import com.gjf.bit_couple_of_a_week.exception.UserDaoException;
import com.gjf.bit_couple_of_a_week.util.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@ControllerAdvice(annotations = {Controller.class, RestController.class})
@Slf4j
public class GlobalExceptionHandler {
    private Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

    public GlobalExceptionHandler() {
    }

    @ExceptionHandler(UserDaoException.class)
    @ResponseBody
    public ResponseResult error(UserDaoException e) {
        logger.error(e.getMessage());
        return new ResponseResult()
                .code(500)
                .message(e.getMessage());
    }

    @ExceptionHandler(CoupleDaoException.class)
    @ResponseBody
    public ResponseResult error(CoupleDaoException e) {
        logger.error(e.getMessage());
        return new ResponseResult()
                .code(500)
                .message(e.getMessage());
    }

    @ExceptionHandler(PostDaoException.class)
    @ResponseBody
    public ResponseResult error(PostDaoException e) {
        logger.error(e.getMessage());
        return new ResponseResult()
                .code(500)
                .message(e.getMessage());
    }
}

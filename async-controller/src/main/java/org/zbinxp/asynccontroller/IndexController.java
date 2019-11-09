package org.zbinxp.asynccontroller;

import java.util.concurrent.ForkJoinPool;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import lombok.extern.slf4j.Slf4j;

/**
 * IndexController
 */
@RestController
@Slf4j
public class IndexController {
    
    @GetMapping("/sync")
    public ResponseEntity<?> handleRequestSync(){
        log.info("handle request sync start");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            //ignore
        }
        log.info("handle request sync end");
        return ResponseEntity.ok("ok");
    }
    
    @GetMapping("/async")
    public DeferredResult<ResponseEntity<?>> handleRequestAsync(){
        log.info("handle request async start");
        DeferredResult<ResponseEntity<?>> output = new DeferredResult<>();

        //verify timeout case
        output.onTimeout(()->{
            log.info("timeout occurs");
            output.setResult(ResponseEntity.status(HttpStatus.BAD_REQUEST).body("timeout occurs"));
        });
        ForkJoinPool.commonPool().submit(()->{
            log.info("async handler start");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                //ignore
            }
            log.info("async handler end");
            output.setResult(ResponseEntity.ok("ok"));
        });

        log.info("handle request async end");

        return output;
    }
}
package spring.advanced.app.v3;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import spring.advanced.trace.TraceId;
import spring.advanced.trace.TraceStatus;
import spring.advanced.trace.hellotrace.HelloTraceV2;
import spring.advanced.trace.logtrace.LogTrace;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV3 {

    private final LogTrace trace;

    public void save(String itemId){

        TraceStatus status = null;

        try {
            status = trace.begin("OrderRepository.request()");
            //저장로직
            if(itemId.equals("ex")){
                throw new IllegalStateException("예외 발생!");
            }
            sleep(1000);
            trace.end(status);
        }catch(Exception e){
            trace.exception(status, e);
            throw e; // 예외를 다시 밖으로 던져주어야 한다.
        }
    }

    private  void sleep(int mills){
        try{
            Thread.sleep(mills);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}

package spring.advanced.app.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import spring.advanced.trace.TraceStatus;
import spring.advanced.trace.hellotrace.HelloTraceV1;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV1 {

    private final HelloTraceV1 trace;

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

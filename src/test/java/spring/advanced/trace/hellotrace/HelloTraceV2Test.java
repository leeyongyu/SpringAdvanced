package spring.advanced.trace.hellotrace;

import org.junit.jupiter.api.Test;
import spring.advanced.trace.TraceStatus;

import static org.junit.jupiter.api.Assertions.*;

class HelloTraceV2Test {

    @Test
    void begin_end() {
        HelloTraceV2 trace = new HelloTraceV2();
        TraceStatus status = trace.begin("hello");
        TraceStatus status2 = trace.beginSync(status.getTraceId(), "hello2");


        trace.end(status2);
        trace.end(status);
    }

    @Test
    void begin_exception() {
        HelloTraceV2 trace = new HelloTraceV2();
        TraceStatus status = trace.begin("hello");
        TraceStatus status2 = trace.beginSync(status.getTraceId(),"hello2");

        trace.exception(status2, new IllegalStateException());
        trace.exception(status, new IllegalStateException());
    }
}
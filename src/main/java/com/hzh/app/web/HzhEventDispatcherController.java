package com.hzh.app.web;

import com.hzh.app.hzhevent.EventTypeEnums;
import com.hzh.app.hzhevent.HzhEvent;
import com.hzh.app.hzhevent.HzhEventDispatcher;
import com.hzh.app.hzhevent.source.CreditEventSource;
import com.hzh.app.hzhevent.source.LoanEventSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HzhEventDispatcherController {

    @Autowired
    private HzhEventDispatcher hzhEventDispatcher;

    @GetMapping("/credit")
    public String credit() {

        CreditEventSource eventSource = new CreditEventSource();
        eventSource.setId(100);
        eventSource.setName("何志洪");
        eventSource.setMoney(Integer.MAX_VALUE);

        HzhEvent<CreditEventSource> hzhEvent = new HzhEvent<>();
        hzhEvent.setEventType(EventTypeEnums.Credit);
        hzhEvent.setEventSource(eventSource);

        hzhEventDispatcher.dispatcherAsync(hzhEvent);

        return "credit execute event success";
    }

    @GetMapping("/loan")
    public String loan() {

        LoanEventSource eventSource = new LoanEventSource();
        eventSource.setLoanMoney(Integer.MAX_VALUE);

        HzhEvent<LoanEventSource> hzhEvent = new HzhEvent<>();
        hzhEvent.setEventType(EventTypeEnums.Loan);
        hzhEvent.setEventSource(eventSource);

        hzhEventDispatcher.dispatcherSync(hzhEvent);

        return "loan execute event success";
    }

}

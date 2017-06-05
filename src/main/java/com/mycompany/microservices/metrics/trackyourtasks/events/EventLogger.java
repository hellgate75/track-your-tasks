package com.mycompany.microservices.metrics.trackyourtasks.events;

import com.mycompany.microservices.metrics.trackyourtasks.exceptions.IRestExeption;
import org.springframework.stereotype.Component;

/**
 * Event Logger defined to action to external resources
 * @author Fabrizio Torelli (hellgate75@gmail.com)
 */
@Component
public class EventLogger {

    public boolean logException(IRestExeption exception) {
        /* Here we should define policies and target to report
        * Exceptions based on interface information, now it's very poor
        * and we do not provide all required metrics and neither
        * any external service, this was not defined in the MVP,
        * and definitely due to unknown final architecture */
        //TODO: Implement LOGGER into Db/Queue/console/service the exception according to logging policy and ms produce goals (has Dashboard, Centralized Logs, QA Stats, Security Tools, etc...)
        return true;
    }

    public boolean logEvent(Event event) {
        /* Here we should define policies and target to report
        * Events based on class information, now it's very poor
        * and we do not provide all required metrics and neither
        * any external service, this was not defined in the MVP,
        * and definitely due to unknown final architecture */
        //TODO: Implement LOGGER into Db/Queue/console/service the exception according to logging policy and ms produce goals (has Dashboard, Centralized Logs, QA Stats, Security Tools, etc...)
        return true;
    }

}

package com.lsla.bank.parameter;

import static com.lsla.bank.parameter.ParameterName.*;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ParameterService {
    private static Map<ParameterName, Object> parameterMaps;
    static {
        parameterMaps = new HashMap<>();
        parameterMaps.put(AMOUNT_MIN, 1000);
        parameterMaps.put(AMOUNT_MAX, 10000);
        parameterMaps.put(LOAN_PERCENT_COST, 10);
        parameterMaps.put(TERM_DAY_MIN, 5);
        parameterMaps.put(TERM_DAY_MAX, 10);
        parameterMaps.put(TERM_EXTEND, 3);
        parameterMaps.put(REJECT_TIME_FROM, "00:00");
        parameterMaps.put(REJECT_TIME_TO, "06:00");
        parameterMaps.put(REJECT_TIME_PATTERN, "HH:mm");
    }

    public String getParameterAsString(final ParameterName parameterName) {
        return (String) getParameter(parameterName);
    }

    public int getParameterAsInt(final ParameterName parameterName) {
        return (Integer) getParameter(parameterName);
    }

    public Object getParameter(final ParameterName parameterName) {
        return parameterMaps.get(parameterName);
    }
}

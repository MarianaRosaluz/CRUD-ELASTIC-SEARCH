package br.rosaluz.banking.system.utils;

import lombok.var;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;

public class IndexUtil {

    private final static String INDEX_USERS_FORMAT = "users-%s-to-%s";
    private final static String ALIAS_USERS_FORMAT = "users-%s";


    public static String getIndexPerWeek(){
        final var today = LocalDate.now();
        final var startWeek = today.with(WeekFields.SUNDAY_START.dayOfWeek(), 1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        final var endWeek = today.with(WeekFields.SUNDAY_START.dayOfWeek(), 7).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return String.format(INDEX_USERS_FORMAT, startWeek, endWeek).toLowerCase();
    }

    public static String getAlias(){
        return ALIAS_USERS_FORMAT;
    }
}

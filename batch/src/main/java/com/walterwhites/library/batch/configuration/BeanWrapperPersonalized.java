package com.walterwhites.library.batch.configuration;

import jdk.internal.jline.internal.Log;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.validation.DataBinder;

import java.beans.PropertyEditorSupport;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BeanWrapperPersonalized<T> extends BeanWrapperFieldSetMapper<T> {

    @Override
    protected void initBinder(DataBinder binder) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd H:m");
        binder.registerCustomEditor(LocalDateTime.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                Log.info("loulou");
                Log.info(text);
                if (!text.isEmpty()) {
                    setValue(LocalDateTime.parse(text, formatter));
                } else {
                    setValue(null);
                }
            }

            @Override
            public String getAsText() throws IllegalArgumentException {
                Object date = getValue();
                if (date != null) {
                    return formatter.format((LocalDateTime) getValue());
                } else {
                    return "";
                }
            }
        });
    }
}
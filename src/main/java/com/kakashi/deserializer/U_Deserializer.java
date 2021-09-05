package com.kakashi.deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakashi.model.user_data;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class U_Deserializer implements Deserializer<user_data> {
    @Override public void close() {
    }
    @Override public void configure(Map<String, ?> arg0, boolean arg1) {
    }
    @Override
    public user_data deserialize(String arg0, byte[] arg1) {
        ObjectMapper mapper = new ObjectMapper();
        user_data userdata = null;
        try {
            userdata = mapper.readValue(arg1, user_data.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userdata;
    }
}
package com.uid4oe.java.advice;


import com.google.protobuf.Timestamp;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Data
@Builder
@Entity(name = "advice_java")
@NoArgsConstructor
@AllArgsConstructor
public class AdviceModel {
    @Id
    String user_id;
    String advice;
    LocalDateTime created_at;


    static Timestamp localDateToTimestamp(LocalDateTime date) {
        var instant = date.toInstant(ZoneOffset.UTC);
        return Timestamp.newBuilder()
                .setSeconds(instant.getEpochSecond())
                .setNanos(instant.getNano())
                .build();
    }
}

package com.yysoft.mydigitallink.gs1;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document(collection = "gs1_links")
public class Gs1DigitalLink {
    @Id
    private String id;
    private String gtin;      // 상품식별코드
    private String lot;       // 로트번호
    private String exp;       // 유효기간
    private String targetUrl; // 리디렉션 URL
    private String createdAt;
} 
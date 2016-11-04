package com.core.bean;

import javax.persistence.*;

/**
 * Created by sdyang on 2016/10/11.
 */
@Entity
@Table(name = "einvoice_log")
public class EinvoiceLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pk_log; // 主键

    @Lob
    private String content;//报文信息

    @Column
    private String logtype;//日志类型


}

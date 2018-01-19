package com.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by manel on 19/01/18.
 */
@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class Currency {
    private int Id;
    private String Name;
    private String Symbol;
    private String Algorithm;
    private float WithdrawFee;
    private float MinWithdraw;
    private float MinBaseTrade;
    private boolean IsTipEnabled;
    private float MinTip;
    private int DepositConfirmations;
    private String Status;
    private String StatusMessage;
    private String ListingStatus;
}

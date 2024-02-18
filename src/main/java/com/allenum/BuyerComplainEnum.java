package com.allenum;

public enum BuyerComplainEnum  {
    UNDELIVERED_PRODUCT("買家未收到商品"),
    PAYMENT_ISSUE("買家支付問題"),
    FALSE_ADVERTISEMENT_SCAM("虛假廣告或詐騙"),
    CUSTOMER_SERVICE_ISSUE("買家帳戶問題"),
    PRIVACY_SECURITY_ISSUE("買家隱私和安全問題");

    private final String reason;

    BuyerComplainEnum(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }
}

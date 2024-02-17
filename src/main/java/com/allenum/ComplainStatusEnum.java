package com.allenum;

public enum ComplainStatusEnum  {
    PENDING("待處理"),
    PROCESSING("處理中"),
    AWAITING_FEEDBACK("等待回饋"),
    RESOLVED("已解決"),
    CLOSED("已關閉"), // 遭過客服回復時間三天
    REOPENED("重新開啟"),
    REQUEST_DENIED("請求拒絕"),
    UNDER_REVIEW("待審核");

    private final String status;

    ComplainStatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
